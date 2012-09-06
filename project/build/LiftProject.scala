import sbt._

class LiftProject(info: ProjectInfo) extends DefaultWebProject(info) {
  val liftVersion = property[Version]

  // uncomment the following if you want to use the snapshot repo
  //  val scalatoolsSnapshot = ScalaToolsSnapshots

  // If you're using JRebel for Lift development, uncomment
  // this line
  // override def scanDirectories = Nil

  override def compileOptions = super.compileOptions ++ compileOptions("-encoding","ISO-8859-1")
  
  lazy val JavaNet = "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"
  
  override def libraryDependencies = Set(
	// "org.scala-tools.sbt" %% "sbt_2.9.1" % "0.11.2",
    "net.liftweb" %% "lift-webkit" % liftVersion.value.toString % "compile",
    "net.liftweb" %% "lift-mapper" % liftVersion.value.toString % "compile",
	"net.liftweb" %% "lift-record" % "2.4-M4", 
	"net.liftweb" %% "lift-squeryl-record" % "2.4-M4",
	"org.squeryl" %% "squeryl" % "0.9.4",
	"org.mortbay.jetty" % "jetty" % "6.1.26" % "test",
    "junit" % "junit" % "4.7" % "test",
    "ch.qos.logback" % "logback-classic" % "0.9.26",
    "org.scala-tools.testing" %% "specs" % "1.6.6" % "test",
	"org.scalatest" % "scalatest_2.8.1" % "1.5.1",
    "com.h2database" % "h2" % "1.2.147",
    // use slf4s for logging
    "com.weiglewilczek.slf4s" %% "slf4s" % "1.0.7"
  ) ++ super.libraryDependencies
}
