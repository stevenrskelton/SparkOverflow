package ca.stevenskelton.sparkoverflow

import org.specs2._
import org.specs2.matcher._
import java.io.File
import scala.io.Source
import scala.xml.pull._
import scala.io.Source
import scala.xml._

class UsersSpec extends org.specs2.mutable.SpecificationWithJUnit {

  val data = """<?xml version="1.0" encoding="utf-8"?>
<users>
  <row Id="1" Reputation="5828" CreationDate="2009-04-30T07:08:27.067" DisplayName="Jeff Atwood" LastAccessDate="2013-08-15T00:13:25.560" WebsiteUrl="http://www.codinghorror.com/blog/" Location="El Cerrito, CA" AboutMe="&lt;p&gt;&lt;a href=&quot;http://www.codinghorror.com/blog/archives/001169.html&quot; rel=&quot;nofollow&quot;&gt;Stack Overflow Valued Associate #00001&lt;/a&gt;&lt;/p&gt;&#xA;&#xA;&lt;p&gt;Wondering how our software development process works? &lt;a href=&quot;http://www.youtube.com/watch?v=08xQLGWTSag&quot; rel=&quot;nofollow&quot;&gt;Take a look!&lt;/a&gt;&lt;/p&gt;&#xA;&#xA;&lt;p&gt;Find me &lt;a href=&quot;http://twitter.com/codinghorror&quot; rel=&quot;nofollow&quot;&gt;on twitter&lt;/a&gt;, or &lt;a href=&quot;http://www.codinghorror.com/blog&quot; rel=&quot;nofollow&quot;&gt;read my blog&lt;/a&gt;. Don't say I didn't warn you &lt;em&gt;because I totally did&lt;/em&gt;.&lt;/p&gt;&#xA;&#xA;&lt;p&gt;However, &lt;a href=&quot;http://www.codinghorror.com/blog/2012/02/farewell-stack-exchange.html&quot; rel=&quot;nofollow&quot;&gt;I no longer work at Stack Exchange, Inc&lt;/a&gt;. I'll miss you all. Well, &lt;em&gt;some&lt;/em&gt; of you, anyway. :)&lt;/p&gt;&#xA;" Views="6020" UpVotes="2130" DownVotes="32" EmailHash="51d623f33f8b83095db84ff35e15dbe8" Age="43" />
</users>"""

  "file exists" in {
    User.file.exists must beTrue
  }

  "parse lines from source" in {

    skipped

    val linesSource = Source.fromFile(User.file, "UTF-8")
    val users = try {
      val lines = linesSource.getLines
      lines.flatMap(User.parse).size
    } finally {
      linesSource.close
    }
    users === 137017
  }

  "parse Users from string" in {
    val lines = Source.fromString(data).getLines.toSeq
    val users = lines.flatMap(User.parse)

    val user = users.head
    user.id === 1
    user.reputation === 5828
    user.creationDate === Post.dateFormat.parse("2009-04-30T07:08:27.067").getTime
    user.displayName === "Jeff Atwood"
    user.lastAccessDate === Post.dateFormat.parse("2013-08-15T00:13:25.560").getTime
    user.websiteUrl === "http://www.codinghorror.com/blog/"
    user.location === "El Cerrito, CA"
    user.aboutMe !== ""
    user.views === 6020
    user.upVotes === 2130
    user.downVotes === 32
    user.emailHash === "51d623f33f8b83095db84ff35e15dbe8"
    user.age === 43
  }

}