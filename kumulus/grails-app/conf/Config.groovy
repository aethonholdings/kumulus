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
// grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.kumulus.domain.User'              // not needed because we are using LDAP
// grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.kumulus.domain.UserRole'       // not needed because we are using LDAP
// grails.plugin.springsecurity.authority.className = 'com.kumulus.domain.Role'                         // not needed because we are using LDAP
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/home'                                  // added by Konstantinos to configure login landing page
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        
        // public
	'/':                              ['permitAll'],
	'/**/js/**':                      ['permitAll'],
	'/**/css/**':                     ['permitAll'],
	'/**/images/**':                  ['permitAll'],
	'/**/favicon.ico':                ['permitAll'],

        // secured - workflow controllers
        '/home/**':                       ['isAuthenticated()'],
        '/access/**':                     ['ROLE_ADMIN', 'ROLE_VIEW'],
        '/capture/**':                    ['ROLE_ADMIN', 'ROLE_IMPORT'],
        '/structure/**':                  ['ROLE_ADMIN', 'ROLE_PROCESS'],
    
        // secured - domain controllers
        '/company/**':                    ['isAuthenticated()'],
        '/document/**':                   ['isAuthenticated()'],
        '/file/**':                       ['isAuthenticated()'],
        '/image/**':                      ['isAuthenticated()'],
        '/node/**':                       ['isAuthenticated()'],
        '/project/**':                    ['isAuthenticated()'],
        '/task/**':                       ['isAuthenticated()'],
        '/logistics/**':                  ['isAuthenticated()'],

        // secured - plugin controllers
        '/fileUploader/process/**':       ['ROLE_ADMIN', 'ROLE_IMPORT'],
        '/download/**':                   ['isAuthenticated()']
        
]

// scando controller - use basic authentication
grails.plugin.springsecurity.useBasicAuth = true
grails.plugin.springsecurity.basic.realmName = "kumulus"
grails.plugin.springsecurity.filterChain.chainMap = [
        '/scando/**': 'JOINED_FILTERS,-exceptionTranslationFilter',
        '/**': 'JOINED_FILTERS,-basicAuthenticationFilter,-basicExceptionTranslationFilter'
]

grails.plugin.springsecurity.providerNames = ['ldapAuthProvider', 'anonymousAuthenticationProvider', 'rememberMeAuthenticationProvider']
grails.plugin.springsecurity.logout.postOnly = false

// LDAP configuration ----------------------------

// server
grails.plugin.springsecurity.ldap.context.server = 'ldap://test.aethon.sg:389'
grails.plugin.springsecurity.ldap.context.managerDn = 'cn=admin,dc=aethon,dc=sg'
grails.plugin.springsecurity.ldap.context.managerPassword = 'secret'

// search
grails.plugin.springsecurity.ldap.user.base = 'ou=people,dc=aethon,dc=sg'
grails.plugin.springsecurity.ldap.search.base = 'dc=aethon,dc=sg'
grails.plugin.springsecurity.ldap.search.searchSubtree = true

// authorities
grails.plugin.springsecurity.ldap.authorities.groupRoleAttribute = 'cn'
grails.plugin.springsecurity.ldap.authorities.groupSearchBase = 'ou=groups,dc=aethon,dc=sg'

// Kumulus configuration --------------------------
grails.sitemesh.default.layout = 'home'
kumulus {
    dateFormat = "dd/MM/yy HH:mm:ss"
    roles = [
        'ROLE_ADMIN',
        'ROLE_IMPORT',
        'ROLE_PROCESS',
        'ROLE_REVIEW',
        'ROLE_SUPERVISE',
        'ROLE_VIEW'
    ]
}

environments {
    development {
        grails.logging.jul.usebridge = true
        grails.serverURL
        filesystem.root = "../data/filesystem"
        filesystem.staging = "${filesystem.root}/staging/"
        filesystem.main = "${filesystem.root}/main/"
        // plugins
        fileuploader {
            image {
                allowedExtensions = ["bmp", "png", "pdf", "tiff", "tif"]
                path = "${filesystem.root}/staging/"
            }
        }
        // grails.serverURL = "http://localhost:8080/"
    }
    
    test {
        // grails.serverURL = "http://test.llamrei.sg:8080"
        grails.logging.jul.usebridge = true
        grails.serverURL
        filesystem.root = "/home/tomcat/kumulus"
        filesystem.staging = "${filesystem.root}/staging/"
        filesystem.main = "${filesystem.root}/main/"
        // plugins
        fileuploader {
            image {
                allowedExtensions = ["bmp", "png", "pdf", "tiff", "tif"]
                path = "${filesystem.root}/staging/"
            }
        }
        
    }
    
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
        
    }
}

quartz {
    autoStartup = false
}

