inThisBuild(
  List(
    organization := "com.carbonhealth",
    homepage := Some(url("https://github.com/scalameta/sbt-scalafmt")),
    licenses := Seq(
      "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
    ),
    developers := List(
      Developer(
        "poslegm",
        "Mikhail Chugunkov",
        "poslegm@gmail.com",
        url("https://chugunkov.website/")
      ),
      Developer(
        "olafurpg",
        "Ólafur Páll Geirsson",
        "olafurpg@gmail.com",
        url("https://geirsson.com")
      ),
      Developer(
        "tanishiking",
        "Rikito Taniguchi",
        "rikiriki1238@gmail.com",
        url("https://github.com/tanishiking/")
      )
    ),
    resolvers ++= Seq(
      Resolver.sonatypeRepo("releases"),
      Resolver.sonatypeRepo("snapshots"),
      Resolver.bintrayIvyRepo("sbt", "sbt-plugin-releases")
    ),
    scalaVersion := "2.12.8",
    publishArtifact in packageDoc := sys.env.contains("CI"),
    publishArtifact in packageSrc := sys.env.contains("CI")
  )
)
onLoadMessage := s"Welcome to sbt-scalafmt ${version.value}"

moduleName := "sbt-scalafmt"

libraryDependencies += "com.carbonhealth" %% "scalafmt-cli" % "2.0.0-RC8+10-2074d01c+20190702-1926-SNAPSHOT"

enablePlugins(ScriptedPlugin)

sbtPlugin := true

scriptedBufferLog := false

scriptedLaunchOpts += s"-Dplugin.version=${version.value}"

// For some reason, it doesn't work if this is defined in globalSettings in PublishPlugin.
inScope(Global)(
  Seq(
    PgpKeys.pgpPassphrase := sys.env.get("PGP_PASSPHRASE").map(_.toCharArray())
  )
)
