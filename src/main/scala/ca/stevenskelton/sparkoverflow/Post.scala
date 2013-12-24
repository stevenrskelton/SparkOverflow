package ca.stevenskelton.sparkoverflow

import scala.xml.{ NodeSeq, MetaData }
import java.io.File
import scala.io.{ BufferedSource, Source }

object Post extends StackTable[Post] {

  val file = new File("data/Posts.xml")
  assert(file.exists)

  override def parseXml(x: scala.xml.Elem): Post = Post(
    getInt(x \ "@Id"),
    getInt(x \ "@PostTypeId"),
    getInt(x \ "@AcceptedAnswerId"),
    getDate(x \ "@CreationDate"),
    getInt(x \ "@Score"),
    getInt(x \ "@ViewCount"),
    (x \ "@Body").text,
    getInt(x \ "@OwnerUserId"),
    getDate(x \ "@LastActivityDate"),
    (x \ "@Title").text,
    getTags(x \ "@Tags"),
    getInt(x \ "@AnswerCount"),
    getInt(x \ "@CommentCount"),
    getInt(x \ "@FavoriteCount"),
    getDate(x \ "@CommunityOwnedDate"))

  def getTags(x: scala.xml.NodeSeq): Array[String] = x.text match {
    case "" => Array()
    case s => s.drop(1).dropRight(1).split("><")
  }
}

// <row Id="1" PostTypeId="1" AcceptedAnswerId="15" CreationDate="2010-07-19T19:12:12.510" Score="19" ViewCount="1033" Body="&lt;p&gt;How should I elicit prior distributions from experts when fitting a Bayesian model?&lt;/p&gt;&#xA;" OwnerUserId="8" LastActivityDate="2010-09-15T21:08:26.077" Title="Eliciting priors from experts" Tags="&lt;bayesian&gt;&lt;prior&gt;&lt;elicitation&gt;" AnswerCount="5" CommentCount="1" FavoriteCount="11" />
case class Post(
  id: Int,
  postTypeId: Int,
  acceptedAnswerId: Int,
  creationDate: Long,
  score: Int,
  viewCount: Int,
  body: String,
  ownerUserId: Int,
  lastActivityDate: Long,
  title: String,
  tags: Array[String],
  answerCount: Int,
  commentCount: Int,
  favoriteCount: Int,
  communityOwnedDate: Long) 