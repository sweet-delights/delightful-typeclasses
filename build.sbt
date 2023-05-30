import java.util.regex.Pattern
import sbt.Keys.scalacOptions

lazy val scala2_12 = "2.12.16"
lazy val scala2_13 = "2.13.8"
lazy val scala3 = "3.1.3"

name := "delightful-typeclasses"
organization := "org.sweet-delights"
homepage := Option(url("https://github.com/sweet-delights/delightful-typeclasses"))
licenses := List("GNU Lesser General Public License Version 3" -> url("https://www.gnu.org/licenses/lgpl-3.0.txt"))
description := "delightful-typeclasses is a Scala library with utility typeclasses"
scmInfo := Option(ScmInfo(url("https://github.com/sweet-delights/delightful-typeclasses"), "scm:git@github.com:sweet-delights/delightful-typeclasses.git"))
developers := List(
  Developer(
    id = "pgrandjean",
    name = "Patrick Grandjean",
    email = "pgrandjean.github.com@gmail.com",
    url = url("https://github.com/pgrandjean")
  )
)
scalaVersion := scala3
crossScalaVersions := Seq(scala2_12, scala2_13, scala3)
update / checksums := Nil
libraryDependencies ++= {
  scalaBinaryVersion.value match {
    case "3" =>
      Seq(
        "org.specs2" %% "specs2-core" % "4.16.1" % "test"
      )
    case _ =>
      Seq(
        "org.scala-lang" % "scala-reflect" % scalaVersion.value % Provided,
        "com.chuusai"    %% "shapeless"    % "2.3.10",
        "org.specs2"     %% "specs2-core"  % "4.16.1" % "test"
      )
  }
}
scalacOptions ++= {
  scalaBinaryVersion.value match {
    case "3" =>
      Seq(
        "-deprecation",
        "-Xtarget",
        "8",
        "-feature"
      )
    case _ =>
      Seq(
        "-deprecation",
        "-target:jvm-1.8",
        "-feature"
      )
  }
}
Compile / javacOptions ++= Seq(
  "-source",
  "1.8",
  "-target",
  "1.8"
)
ThisBuild / scalafmtOnCompile := true
publishMavenStyle := true
publishTo := Some {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    "snapshots" at nexus + "content/repositories/snapshots"
  else
    "releases" at nexus + "service/local/staging/deploy/maven2"
}
// sbt-release
import sbtrelease._
import ReleaseTransformations._
releaseCrossBuild := true
releaseVersion := { ver =>
  val bumpedVersion = Version(ver)
    .map { v =>
      suggestedBump.value match {
        case Version.Bump.Bugfix => v.withoutQualifier.string
        case _ => v.bump(suggestedBump.value).withoutQualifier.string
      }
    }
    .getOrElse(versionFormatError(ver))
  bumpedVersion
}
releaseNextVersion := { ver =>
  Version(ver).map(_.withoutQualifier.bump.string).getOrElse(versionFormatError(ver)) + "-SNAPSHOT"
}
releaseCommitMessage := s"[sbt-release] setting version to ${(ThisBuild / version).value}"
bugfixRegexes := List(s"${Pattern.quote("[patch]")}.*").map(_.r)
minorRegexes := List(s"${Pattern.quote("[minor]")}.*").map(_.r)
majorRegexes := List(s"${Pattern.quote("[major]")}.*").map(_.r)
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommandAndRemaining("+publishSigned"),
  setNextVersion,
  commitNextVersion,
  releaseStepCommand("sonatypeRelease"),
  pushChanges
)
