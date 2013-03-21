grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.7
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.release.scm.enabled = false

grails.project.dependency.resolution = {

    def spockVersion = "0.6"
    def gebGrailsVersion = "1.0.0-SNAPSHOT"
    def seleniumVersion = "2.25.0"

    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'

    repositories {
        inherits true
        grailsHome()

        mavenLocal()
        mavenRepo "http://maven.sensis.com.au:8081/nexus/content/groups/public"
        mavenRepo "http://maven.sensis.com.au:8081/nexus/content/repositories/sensis"
    }
    dependencies {

        test ("org.gebish:geb-spock:$gebGrailsVersion")
        test ("org.gebish:geb-junit4:$gebGrailsVersion")

        test ("junit:junit:4.10")
        test ("org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion") {
            excludes 'httpclient'
        }

        test ("org.seleniumhq.selenium:selenium-support:$seleniumVersion")

	    build 'org.codehaus.groovy.modules.http-builder:http-builder:0.6'
    }

    plugins {


        test ":geb:1.0.0.sensis.a"
        test ":spock:${spockVersion}"

        build ":tomcat:$grailsVersion",
		      ":rest-client-builder:1.0.3",
              ":release:2.2.0", {
            export = false
        }

    }
}
