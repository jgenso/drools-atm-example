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
mainClass in (Compile, run) := Some("my.scalafx.ScalaFXHelloWorld")

// Fork a new JVM for 'run' and 'test:run' to avoid JavaFX double initialization problems
fork := true
