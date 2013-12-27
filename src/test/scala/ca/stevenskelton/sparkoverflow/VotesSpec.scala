package ca.stevenskelton.sparkoverflow

import org.specs2._
import org.specs2.matcher._
import java.io.File
import scala.io.Source
import scala.xml.pull._
import scala.io.Source
import scala.xml._

class VotesSpec extends org.specs2.mutable.SpecificationWithJUnit {

  val data = """<?xml version="1.0" encoding="utf-8"?>
<votes>
  <row Id="1264793" PostId="486481" VoteTypeId="5" UserId="175880" CreationDate="2013-05-30T00:00:00.000" />
</votes>"""

  "file exists" in {
    Vote.file.exists must beTrue
  }

  "parse lines from source" in {

    skipped

    val linesSource = Source.fromFile(Vote.file, "UTF-8")
    val votes = try {
      val lines = linesSource.getLines
      lines.flatMap(Vote.parse).size
    } finally {
      linesSource.close
    }
    votes === 1176723
  }

  "parse date" in {
    val dateValue = Vote.dateFormat.parse("2013-05-30T00:00:00.000").getTime
    import java.util.Calendar
    val cal = Calendar.getInstance
    cal.setTimeZone(java.util.TimeZone.getTimeZone("GMT"))
    cal.set(Calendar.MILLISECOND, 0)
    cal.set(2013, 4, 30, 0, 0, 0)

    dateValue === cal.getTimeInMillis
  }

  "parse Votes from string" in {
    val lines = Source.fromString(data).getLines.toSeq
    val votes = lines.flatMap(Vote.parse)

    val vote = votes.head
    vote.id === 1264793
    vote.postId === 486481
    vote.voteTypeId === 5
    vote.userId === 175880
    vote.creationDate === Post.dateFormat.parse("2013-05-30T00:00:00.000").getTime
  }

}