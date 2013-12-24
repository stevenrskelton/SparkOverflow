retrieveManaged := true

scalaVersion := "2.10.3"

resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/maven-releases"

libraryDependencies ++= Seq(
	//"org.apache.hadoop" % "hadoop-client" % "1.0.4",
	//"net.java.dev.jets3t" % "jets3t" % "0.7.1",
	//"org.apache.avro" % "avro" % "1.7.4",
	//"org.apache.avro" % "avro-ipc" % "1.7.4",
	//"org.apache.zookeeper" % "zookeeper" % "3.4.5",
	//"org.eclipse.jetty" % "jetty-server" % "7.6.8.v20121106",
	//"com.google.guava" % "guava" % "14.0.1",
	//"com.google.code.findbugs" % "jsr305" % "1.3.9",
	//"org.slf4j" % "slf4j-api" % "1.7.2",
	//"com.ning" % "compress-lzf" % "0.8.4",
	//"org.xerial.snappy" % "snappy-java" % "1.0.5",
	//"org.ow2.asm" % "asm" % "4.0",
	//"com.google.protobuf" % "protobuf-java" % "2.4.1",
	//"com.twitter" %% "chill" % "0.3.1",
	//"com.twitter" % "chill-java" % "0.3.1",
	//"com.typesafe.akka" %% "akka-actor" % "2.2.3",
	//"com.typesafe.akka" %% "akka-remote" % "2.2.3",
	//"com.typesafe.akka" %% "akka-slf4j" % "2.2.3",
	//"net.liftweb" %% "lift-json" % "2.5.1",
	//"it.unimi.dsi" % "fastutil" % "6.4.4",
	//"colt" % "colt" % "1.2.0",
	//"org.apache.mesos" % "mesos" % "0.13.0",
	//"log4j" % "log4j" % "1.2.17",
	//"com.codahale.metrics" % "metrics-core" % "3.0.0",
	//"com.codahale.metrics" % "metrics-jvm" % "3.0.0",
	//"com.codahale.metrics" % "metrics-json" % "3.0.0",
	//"com.codahale.metrics" % "metrics-ganglia" % "3.0.0",
	//"com.codahale.metrics" % "metrics-graphite" % "3.0.0",
	"org.specs2" %% "specs2" % "2.3.6" % "test",
	"junit" % "junit" % "4.11" % "test"
)
