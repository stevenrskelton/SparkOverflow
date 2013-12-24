package ca.stevenskelton.sparkoverflow

import scala.xml.{ NodeSeq, MetaData }
import java.io.File
import scala.io.{ BufferedSource, Source }

object Vote extends StackTable[Vote] {

  val file = new File("data/Votes.xml")
  assert(file.exists)

  override def parseXml(x: scala.xml.Elem): Vote = Vote(
    getInt(x \ "@Id"),
    getInt(x \ "@PostId"),
    getInt(x \ "@VoteTypeId"),
    getInt(x \ "@UserId"),
    getDate(x \ "@CreationDate"))
}

// <row Id="1264793" PostId="486481" VoteTypeId="5" UserId="175880" CreationDate="2013-05-30T00:00:00.000" />
case class Vote(
  id: Int,
  postId: Int,
  voteTypeId: Int,
  userId: Int,
  creationDate: Long) 