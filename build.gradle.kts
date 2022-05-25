/*
plugins {
  `maven-publish`
  signing
}

var jJar: Task? = null
var sJar: Task? = null

tasks {
  val javadocJar by creating(Jar::class) {
	archiveClassifier.set("javadoc")
	from(javadoc)
  }
  jJar = javadocJar
  val sourcesJar by creating(Jar::class) {
	archiveClassifier.set("sources")
	from(sourceSets.main) */
/*.allSource*//*

  }
  sJar = sourcesJar
}
artifacts {
  archives(listOf(jJar!!, sJar!!))
}
signing {
  sign(configurations.archives.get())
}
group = "io.github.mgroth0"
val archivesBaseName = "klibexport"
version = "0.0.1"

uploadArchives {
  repositories {
	mavenDeployer {
	  beforeDeployment { MavenDeployment deployment -> signing.signPom(deployment) }

	  repository(url: "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/") {
	  authentication(userName: ossrhUsername, password: ossrhPassword)
	}

	  snapshotRepository(url: "https://s01.oss.sonatype.org/content/repositories/snapshots/") {
	  authentication(userName: ossrhUsername, password: ossrhPassword)
	}

	  pom.project {
		name 'Example Application'
		packaging 'jar'
		// optionally artifactId can be defined here
		description 'A application used as an example on how to set up
		pushing  its components to the Central Repository.'
		url 'http://www.example.com/example-application'

		scm {
		  connection 'scm:svn:http://foo.googlecode.com/svn/trunk/'
		  developerConnection 'scm:svn:https://foo.googlecode.com/svn/trunk/'
		  url 'http://foo.googlecode.com/svn/trunk/'
		}

		licenses {
		  license {
			name 'The Apache License, Version 2.0'
			url 'http://www.apache.org/licenses/LICENSE-2.0.txt'
		  }
		}

		developers {
		  developer {
			id 'manfred'
			name 'Manfred Moser'
			email 'manfred@sonatype.com'
		  }
		}
	  }
	}
  }
}
*/

dependencies {
  /*api(projects.klib)*/
  api(project(mapOf(
    "path" to ":k:klib",
    "configuration" to "jvmRuntimeElements")))
}

