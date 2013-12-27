package ca.stevenskelton.sparkoverflow

import scala.xml.{ NodeSeq, MetaData }
import java.io.File
import scala.io.{ BufferedSource, Source }

object User extends StackTable[User] {

  val file = new File("data/Users.xml")
  assert(file.exists)

  override def parseXml(x: scala.xml.Elem): User = User(
    getInt(x \ "@Id"),
    getInt(x \ "@Reputation"),
    getDate(x \ "@CreationDate"),
    (x \ "@DisplayName").text,
    getDate(x \ "@LastAccessDate"),
    (x \ "@WebsiteUrl").text,
    (x \ "@Location").text,
    (x \ "@AboutMe").text,
    getInt(x \ "@Views"),
    getInt(x \ "@UpVotes"),
    getInt(x \ "@DownVotes"),
    (x \ "@EmailHash").text,
    getInt(x \ "@Age"))
}

// <row Id="1" Reputation="5828" CreationDate="2009-04-30T07:08:27.067" DisplayName="Jeff Atwood" LastAccessDate="2013-08-15T00:13:25.560" WebsiteUrl="http://www.codinghorror.com/blog/" Location="El Cerrito, CA" AboutMe="&lt;p&gt;&lt;a href=&quot;http://www.codinghorror.com/blog/archives/001169.html&quot; rel=&quot;nofollow&quot;&gt;Stack Overflow Valued Associate #00001&lt;/a&gt;&lt;/p&gt;&#xA;&#xA;&lt;p&gt;Wondering how our software development process works? &lt;a href=&quot;http://www.youtube.com/watch?v=08xQLGWTSag&quot; rel=&quot;nofollow&quot;&gt;Take a look!&lt;/a&gt;&lt;/p&gt;&#xA;&#xA;&lt;p&gt;Find me &lt;a href=&quot;http://twitter.com/codinghorror&quot; rel=&quot;nofollow&quot;&gt;on twitter&lt;/a&gt;, or &lt;a href=&quot;http://www.codinghorror.com/blog&quot; rel=&quot;nofollow&quot;&gt;read my blog&lt;/a&gt;. Don't say I didn't warn you &lt;em&gt;because I totally did&lt;/em&gt;.&lt;/p&gt;&#xA;&#xA;&lt;p&gt;However, &lt;a href=&quot;http://www.codinghorror.com/blog/2012/02/farewell-stack-exchange.html&quot; rel=&quot;nofollow&quot;&gt;I no longer work at Stack Exchange, Inc&lt;/a&gt;. I'll miss you all. Well, &lt;em&gt;some&lt;/em&gt; of you, anyway. :)&lt;/p&gt;&#xA;" 
// Views="6020" UpVotes="2130" DownVotes="32" EmailHash="51d623f33f8b83095db84ff35e15dbe8" Age="43" />
case class User(
  id: Int,
  reputation: Int,
  creationDate: Long,
  displayName: String,
  lastAccessDate: Long,
  websiteUrl: String,
  location: String,
  aboutMe: String,
  views: Int,
  upVotes: Int,
  downVotes: Int,
  emailHash: String,
  age: Int) 