name := "DockerMeetupDemo"

version in ThisBuild := "1.0"

scalaVersion in ThisBuild := "2.11.7"

lazy val dockermeetupdemo = (project in file(".")).aggregate(frontend, backend)

lazy val shared = project in file("shared")

lazy val frontend =
  (project in file("frontend"))
    .enablePlugins(PlayScala)
    .settings(
      libraryDependencies += ws,
      routesGenerator := InjectedRoutesGenerator
    )

lazy val backend =
  (project in file("backend"))
    .enablePlugins(JavaAppPackaging)
    .settings(
      resolvers += "Oscar Releases" at "http://artifactory.info.ucl.ac.be/artifactory/libs-release/",
      libraryDependencies += "oscar" %% "oscar-cbls" % "3.0.0",
      libraryDependencies += "oscar" %% "oscar-cp" % "3.0.0",
      libraryDependencies += "com.github.finagle" %% "finch-core" % "0.9.0",
      libraryDependencies += "com.github.finagle" %% "finch-circe" % "0.9.0",
      libraryDependencies += "io.circe" %% "circe-generic" % "0.2.0"
    )
    .dependsOn(shared)
