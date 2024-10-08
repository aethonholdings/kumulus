grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.server.port.http=8080
grails.project.war.file = "target/${appName}.war"

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()
        
        // mavenLocal()
        mavenCentral()
        
        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        mavenRepo "http://repository.codehaus.org"
        mavenRepo "http://repo.grails.org/grails/core"
        mavenRepo "http://www.tomgibara.com/maven/repo/"
        // mavenRepo "http://repo.spring.io/milestone/"                        
        // mavenRepo "http://download.java.net/maven/2/"
        // mavenRepo "http://repository.jboss.com/maven2/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        runtime 'mysql:mysql-connector-java:5.1.24'
        runtime "javax.mail:javax.mail-api:1.5.1"
        runtime "com.sun.jersey:jersey-core:1.18"
        runtime "com.sun.mail:javax.mail:1.5.1"
        runtime "net.coobird:thumbnailator:0.4.7"
        runtime "com.tomgibara.imageio:imageio-tiff:1.0"
    }

    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.47"

        // plugins for the compile step
        compile ":scaffolding:2.0.1"
        compile ':cache:1.1.1'
        compile ":spring-security-core:2.0-RC2"
        compile ":export:1.5"
        compile ":file-uploader:1.2.1"                                          // File upload and download
        compile ":tika-parser:1.3.0.1"                                          // Tika parser
        compile ":searchable:0.6.6"                                             // Lucene search engine
        compile ":quartz:1.0.1"                                                 // Schedules jobs
        compile ":quartz-monitor:0.3-RC3"        
        compile(":barcode4j:0.3") {                                             // barcode generation
            excludes "ant"
        }


        // plugins needed at runtime but not for compilation
        runtime ":hibernate:3.6.10.4" // or ":hibernate4:4.1.11.4"
        runtime ":database-migration:1.3.8"
        runtime ":jquery:1.10.2.2"
        runtime ":resources:1.2.1"
        
        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0.1"
        //runtime ":cached-resources:1.1"
        //runtime ":yui-minify-resources:0.1.5"
    }
}
