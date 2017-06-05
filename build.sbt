name := "drools-atm-example"

organization := "jgenso"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.1"

resolvers ++= Seq(
  "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases",
  "Spray IO Repository" at "http://repo.spray.io/",
  "JBoss Maven Repository" at "https://repository.jboss.org/"
)

libraryDependencies ++= Seq(
  "org.scalafx"   %% "scalafx"   % "8.0.102-R11",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test" //http://www.scalatest.org/download
)

libraryDependencies ++= Seq(
    "drools-compiler",
    "drools-core",
    "drools-jsr94",
    "drools-decisiontables",
    "knowledge-api",
    "drools-templates"
).map("org.drools" % _ % "6.5.0.Final")

libraryDependencies ++= Seq(
    "kie-api",
    "kie-internal"
).map("org.kie" % _ % "6.5.0.Final")

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.8" % "test"
)



shellPrompt := { state => System.getProperty("user.name") + "> " }

// set the main class for the main 'run' task
// change Compile to Test to set it for 'test:run'
mainClass in (Compile, run) := Some("code.run.AtmApp")
mainClass in assembly := Some("code.run.AtmApp")

// Fork a new JVM for 'run' and 'test:run' to avoid JavaFX double initialization problems
fork := true

//
// Configuration for sbt-native-packager / JDKPackagerPlugin
//

mergeStrategy in assembly := {
  case x if x.contains("XmlPullParserException") => MergeStrategy.first
  case x if x.contains("XmlPullParser") => MergeStrategy.first
  case x =>
    val oldStrategy = (mergeStrategy in assembly).value
    oldStrategy(x)
}

enablePlugins(JDKPackagerPlugin)

maintainer := "ScalaFX Organization (scalafx.org)"
packageSummary := "Collection of live ScalaFX examples"
packageDescription := "An application demonstrating ScalaFX code samples."

lazy val iconGlob = sys.props("os.name").toLowerCase match {
  case os if os.contains("mac") => "*.icns"
  case os if os.contains("win") => "*.ico"
  case _ => "*.png"
}

jdkAppIcon := (sourceDirectory.value ** iconGlob).getPaths.headOption.map(file)
jdkPackagerType := "installer"

antPackagerTasks in JDKPackager := (antPackagerTasks in JDKPackager).value orElse {
  for {
    f <- Some(file("/usr/lib/jvm/oracle-java8-jdk-amd64/lib/ant-javafx.jar")) if f.exists()
  } yield f
}