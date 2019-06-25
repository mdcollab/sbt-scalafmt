inThisBuild(
  List(
    organization := "org.scalameta",
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
      Resolver.bintrayIvyRepo("sbt", "sbt-plugin-releases")
    ),
    scalaVersion := "2.12.8",
    publishArtifact in packageDoc := sys.env.contains("CI"),
    publishArtifact in packageSrc := sys.env.contains("CI")
  )
)
onLoadMessage := s"Welcome to sbt-scalafmt ${version.value}"
skip in publish := true

moduleName := "sbt-scalafmt"

dependsOn(RootProject(uri("git://github.com/mdcollab/scalafmt.git#44d7712e79e987f54fd785fd1b0b9a9954fe70fa")))

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
