# Apache Spark Statistics
<http://stevenskelton.ca/>

Basic setup of an in-memory computation project using StackOverflow's data dump.

# Installation (Scala 2.10)

- Download Spark src from github [url], scala 2.10 branch
- Compile spark assembly
- > sbt assembly
- Create /lib directory in this project
- Copy spark-assembly-0.9.0-incubating-SNAPSHOT-hadoop1.0.4.jar from assembly\target\scala-2.10 to /lib

- compile, and run project
-> sbt package run

# Unit tests

Change VM arguments
-Xmx6096m