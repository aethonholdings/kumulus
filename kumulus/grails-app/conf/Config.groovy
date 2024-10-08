// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = kumulus // change this to alter the default package name and Maven publishing destination

grails.gorm.failOnError = true

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml'],
    excel:          'application/vnd.ms-excel',
    rtf:            'application/rtf'
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}
 
grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error   'org.codehaus.groovy.grails.web.servlet',        // controllers
            'org.codehaus.groovy.grails.web.pages',          // GSP
            'org.codehaus.groovy.grails.web.sitemesh',       // layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping',        // URL mapping
            'org.codehaus.groovy.grails.commons',            // core / classloading
            'org.codehaus.groovy.grails.plugins',            // plugins
            'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
            'org.springframework',          
            'net.sf.ehcache.hibernate',
            'org.hibernate'
            
}

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.kumulus.domain.User'              
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.kumulus.domain.UserRole'       
grails.plugin.springsecurity.authority.className = 'com.kumulus.domain.Role'                         
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/home'

// permission definitions
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/**/js/**':                      ['permitAll'],
	'/**/css/**':                     ['permitAll'],
	'/**/images/**':                  ['permitAll'],
	'/**/favicon.ico':                ['permitAll'],
]
grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugin.springsecurity.interceptUrlMap = [    
        // authentication pages accessed by all
        '/login/**':                      ['permitAll'],
        '/logout/**':                     ['permitAll'],
    
        // key controller tools shared by all authenticated users
        '/':                              ['isAuthenticated()'],
        '/home/**':                       ['isAuthenticated()'],
        '/fileUploader/process/**':       ['isAuthenticated()'],
        '/download/**':                   ['isAuthenticated()'],

        // admin pages
        '/user/**':                       ['ROLE_ADMIN'],
        '/admin/**':                      ['ROLE_ADMIN'],
    
        // workflow pages
        '/customer/**':                   ['ROLE_ADMIN', 'ROLE_CUSTOMER'],
        '/capture/**':                    ['ROLE_ADMIN', 'ROLE_CAPTURE'],
        '/backOffice/**':                 ['ROLE_ADMIN', 'ROLE_BACK_OFFICE'],
        '/logistics/**':                  ['ROLE_ADMIN', 'ROLE_LOGISTICS'],

        // import
        '/scanDo/**':                     ['ROLE_ADMIN','ROLE_CAPTURE'],
        '/scanDo2/**':                    ['ROLE_ADMIN','ROLE_CAPTURE'],
       
        // domain base controllers
        '/company/**':                    ['isAuthenticated()'],
        '/document/**':                   ['isAuthenticated()'],
        '/file/**':                       ['isAuthenticated()'],
        '/image/**':                      ['isAuthenticated()'],
        '/node/**':                       ['isAuthenticated()'],
        '/task/**':                       ['isAuthenticated()'],
        '/barcode/**':                    ['isAuthenticated()'],
        '/shipment/**':                   ['isAuthenticated()'],
        '/product/**':                    ['isAuthenticated()'],
        '/project/**':                    ['isAuthenticated()']
]

// scando controller - use basic authentication
grails.plugin.springsecurity.useBasicAuth = true
grails.plugin.springsecurity.basic.realmName = "kumulus"
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.filterChain.chainMap = [
        '/scando2/**': 'JOINED_FILTERS,-exceptionTranslationFilter',
        '/**': 'JOINED_FILTERS,-basicAuthenticationFilter,-basicExceptionTranslationFilter'
]
grails.plugin.springsecurity.providerNames = [
        'daoAuthenticationProvider', 
        'anonymousAuthenticationProvider', 
        'rememberMeAuthenticationProvider'
]

// Kumulus configuration --------------------------
grails.sitemesh.default.layout = 'home'                 // default layout

grails.databinding.dateFormats = [
        'yyyy-MM-dd', 
        'yyyy-MM-dd HH:mm:ss.S', 
        "yyyy-MM-dd'T'hh:mm:ss'Z'"
    ]

kumulus {
    useABBYY = System.getenv('KUMULUS_STRESS') == null
}

environments {
    development {
        grails.logging.jul.usebridge = true
        filesystem.root = "../data/filesystem"
        filesystem.staging = "${filesystem.root}/staging/"
        filesystem.main = "${filesystem.root}/main/"
        // plugins
        fileuploader {
            image {
                allowedExtensions = ["bmp", "png", "pdf", "tiff", "tif", "jpg"]
                path = "${filesystem.root}/staging/"
            }
        }
    }
    
    test {
        grails.logging.jul.usebridge = true
        filesystem.root = "/home/tomcat/kumulus"
        filesystem.staging = "${filesystem.root}/staging/"
        filesystem.main = "${filesystem.root}/main/"
        // plugins
        fileuploader {
            image {
                allowedExtensions = ["bmp", "png", "pdf", "tiff", "tif", "jpg"]
                path = "${filesystem.root}/staging/"
            }
        }
    }
    
    production {
        grails.logging.jul.usebridge = true
        filesystem.root = "/home/tomcat/kumulus"
        filesystem.staging = "${filesystem.root}/staging/"
        filesystem.main = "${filesystem.root}/main/"
        // plugins
        fileuploader {
            image {
                allowedExtensions = ["bmp", "png", "pdf", "tiff", "tif", "jpg"]
                path = "${filesystem.root}/staging/"
            }
        }
    }
}

quartz {
    autoStartup = true
}

abbyy {
    applicationId = "Bucephalus"
    password = "A08p7kFT1HNA+3eurpn4Xb94"
}

smtp {
    server = "email-smtp.us-east-1.amazonaws.com"
    port = 587
    username = "AKIAJX6U3KZTUQQIUH6A"
    password = "AuUdC72aQaCsFHfM2GXHIZkmkQeXOiTxnxAp2CR7iwZg"
    from = "theodoros.balopoulos@aethon.sg"
    error_to = "theodoros.balopoulos@aethon.sg"
}


