organization := "reaktor"

name := "scct"

version := "0.2-SNAPSHOT"

scalaVersion := "2.9.2"

crossScalaVersions := Seq("2.9.2", "2.9.1-1", "2.9.1", "2.9.0-1", "2.9.0")

libraryDependencies <+= (scalaVersion) { v =>
  "org.scala-lang" % "scala-compiler" % v % "provided"
}

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.10" % "test",
  "org.mockito" % "mockito-all" % "1.9.5-rc1" % "test" withSources,
  "org.specs2" % "specs2" % "1.11" % "test" cross CrossVersion.binaryMapped {
    case "2.9.0-1" => "2.9.1"
    case "2.9.0" => "2.9.1"
    case x => x
  }
)

crossPaths := true

publishMavenStyle := true

publishTo <<= version {
    (v: String) =>
        val ansviaRepo = "http://scala.repo.ansvia.com/nexus"
        if (v.trim.endsWith("SNAPSHOT"))
            Some("snapshots" at ansviaRepo + "/content/repositories/snapshots")
        else
            Some("releases" at ansviaRepo + "/content/repositories/releases")
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials-ansvia")

resolvers += "scala-tools-releases" at "https://oss.sonatype.org/content/groups/scala-tools/"

