package ca.stevenskelton.sparkoverflow

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import scala.Predef._
import org.apache.spark.rdd.RDD
import org.apache.log4j.{ LogManager, Level }

object Main extends App {
  import java.io.File

  val sparkHome = "C:/Program Files/Hadoop/spark-0.8.0-incubating/"

  val minSplits = 4

  //System.getenv("SPARK_HOME"),Seq(System.getenv("SPARK_EXAMPLES_JAR")))
  LogManager.getRootLogger().setLevel(Level.WARN)
  //System.setProperty("spark.worker.memory", "3g")
  System.setProperty("spark.executor.memory", "5g")
  //System.setProperty("spark.rdd.compress", "true")
  if (true) {
    System.setProperty("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    System.setProperty("spark.kryo.registrator", "ca.stevenskelton.sparkoverflow.KyroRegistrator")
  }

  val sc = new SparkContext("local", "Main",
    sparkHome, List("target/scala-2.10/sparkoverflow_2.10-0.1-SNAPSHOT.jar"))

  //LOAD DATA USING SPARK
  val jsonData = sc.textFile(Post.file.getAbsolutePath, minSplits)
  val objData = jsonData.flatMap(Post.parse)
  objData.cache

  //val jsonVoteData = sc.textFile(Vote.file.getAbsolutePath, minSplits)
  //val voteData = jsonVoteData.flatMap(Vote.parse)
  //voteData.cache

  var query: RDD[Post] = objData

  Console.println("Enter new command:")
  do {
  } while (readCommand)
  Console.println("Exit")

  def readCommand: Boolean = {
    val command = Console.readLine

    if (command == "?") Console.println("t=Topics, !=Execute")

    if (command.isEmpty) false
    else {
      command match {
        case "=" => //print
        case "~" => clear
        case c if c.startsWith("t:") => {
          val tags = c.drop(2).split(",").toSet
          println("Filter Tags: " + tags.mkString(","))
          setQuery(query.filter(_.tags.exists(tags.contains)))
        }
        case c if c.startsWith("d:") => {
          val s = c.drop(2).split(",")
          val p = s.map(i => Post.dateFormat.parse(i + "T00:00:00.000").getTime)
          println("Filter CreationDate: [" + s(0) + ":" + s(1) + "]")
          setQuery(query.filter(n => n.creationDate >= p(0) && n.creationDate < p(1)))
        }
        case "!t" => {
          val tags = query.flatMap(_.tags).countByValue
          println("Tags: " + tags.toSeq.sortBy(_._2 * -1).take(10).mkString(","))
        }
        case "!" => {
          //get count, print top 10 comment oids
          val s = query.count + " Posts"
          //println(s)
          println(s + "\n" + ("=" * s.length))
          val cs = query.take(10).map(_.id)
          cs.foreach(println)
        }
        case "@" => {
          println("Cache RDD")
          query.cache
        }
        case _ => "Unknown command."
      }
      true
    }
  }

  def setQuery(rdd: RDD[Post]) = {
    query = rdd
  }

  def clear = {
    //clear
    println("Clearing all filters.")
    setQuery(objData)
  }

  def printlnT(f: => String) = {
    val i = time(f)
    println(i._1 + "ms\n" + i._2)
  }

  def time[R](block: => R): (Long, R) = {
    val t0 = System.currentTimeMillis
    val result = block // call-by-name
    val t1 = System.currentTimeMillis
    ((t1 - t0), result)
  }
}