package ca.stevenskelton.sparkoverflow

import org.specs2._
import org.specs2.matcher._
import java.io.File
import scala.io.Source
import scala.xml.pull._
import scala.io.Source
import scala.xml._

class ParserPostsSpec extends org.specs2.mutable.SpecificationWithJUnit {

  val data = """<?xml version="1.0" encoding="utf-8"?>
<posts>
  <row Id="1" PostTypeId="1" AcceptedAnswerId="15" CreationDate="2010-07-19T19:12:12.510" Score="19" ViewCount="1033" Body="&lt;p&gt;How should I elicit prior distributions from experts when fitting a Bayesian model?&lt;/p&gt;&#xA;" OwnerUserId="8" LastActivityDate="2010-09-15T21:08:26.077" Title="Eliciting priors from experts" Tags="&lt;bayesian&gt;&lt;prior&gt;&lt;elicitation&gt;" AnswerCount="5" CommentCount="1" FavoriteCount="11" />
</posts>"""

  "file exists" in {
    Post.file.exists must beTrue
  }

  "parse lines from source" in {
    
    skipped
    
    val linesSource = Source.fromFile(Post.file, "UTF-8")
    val posts = try {
      val lines = linesSource.getLines
      lines.flatMap(Post.parse).size
    } finally {
      linesSource.close
    }
    posts === 58000
  }

  "parse date" in {
    val dateValue = Post.dateFormat.parse("2010-07-19T19:12:12.510").getTime
    import java.util.Calendar
    val cal = Calendar.getInstance
    cal.setTimeZone(java.util.TimeZone.getTimeZone("GMT"))
    cal.set(Calendar.MILLISECOND, 510)
    cal.set(2010, 6, 19, 19, 12, 12)

    dateValue === cal.getTimeInMillis
  }

  "parse Posts from string" in {
    val lines = Source.fromString(data).getLines.toSeq
    val posts = lines.flatMap(Post.parse)

    val post = posts.head
    post.id === 1
    post.postTypeId === 1
    post.acceptedAnswerId === 15
    post.creationDate === 1279566732510L
    post.score === 19
    post.viewCount === 1033
    post.body !== ""
    post.ownerUserId === 8
    post.lastActivityDate === Post.dateFormat.parse("2010-09-15T21:08:26.077").getTime
    post.title === "Eliciting priors from experts"
    post.tags.toSet must containTheSameElementsAs(Array("bayesian", "prior", "elicitation"))
    post.answerCount === 5
    post.commentCount === 1
    post.favoriteCount === 11
  }

}