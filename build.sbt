import sbt.Keys.testFrameworks

ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "dev.ligature"
ThisBuild / organizationName := "Ligature"
ThisBuild / scalaVersion     := "3.0.0-M2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "ligature-formats",
    libraryDependencies += "dev.ligature" %% "ligature" % "0.1.0-SNAPSHOT",
    libraryDependencies += "org.typelevel" %% "cats-parse" % "0.1.0",
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.19" % Test,
    testFrameworks += new TestFramework("munit.Framework")
  )
