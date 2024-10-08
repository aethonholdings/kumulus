dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
//    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:mysql://"+ (System.getenv('KUMULUS_DB_HOST') ?: "localhost") +\
                  ":3306/"+(System.getenv('KUMULUS_DB_NAME') ?: "kumulus")+"?autoReconnect=true"
            username = "kumulus"
            password = System.getenv('KUMULUS_DB_PASS') ?:"password"
            properties {
                //run the evictor every 30 minutes and evict any connections older than 30 minutes.
                minEvictableIdleTimeMillis=1800000
                timeBetweenEvictionRunsMillis=1800000
                numTestsPerEvictionRun=3
                //test the connection while its idle, before borrow and return it
                testOnBorrow=true
                testWhileIdle=true
                testOnReturn=true
                validationQuery="SELECT 1"
            }
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://"+(System.getenv('KUMULUS_DB_HOST') ?:"kumulus.cokd1jwuhqlu.ap-southeast-1.rds.amazonaws.com")+\
                  ":3306/"+(System.getenv('KUMULUS_DB_NAME') ?: "kumulus_test")+"?autoReconnect=true"
            username = "kumulus"
            password = System.getenv('KUMULUS_DB_PASS') ?:"d7!8d826ddx1"
            properties {
                //run the evictor every 30 minutes and evict any connections older than 30 minutes.
                minEvictableIdleTimeMillis=1800000
                timeBetweenEvictionRunsMillis=1800000
                numTestsPerEvictionRun=3
                //test the connection while its idle, before borrow and return it
                testOnBorrow=true
                testWhileIdle=true
                testOnReturn=true
                validationQuery="SELECT 1"
            }
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://"+(System.getenv('KUMULUS_DB_HOST') ?:"kumulus.cokd1jwuhqlu.ap-southeast-1.rds.amazonaws.com")+\
                  ":3306/"+(System.getenv('KUMULUS_DB_NAME') ?: "kumulus")+"?autoReconnect=true"
            username = "kumulus"
            password = System.getenv('KUMULUS_DB_PASS') ?:"d7!8d826ddx1"
            properties {
                //run the evictor every 30 minutes and evict any connections older than 30 minutes.
                minEvictableIdleTimeMillis=1800000
                timeBetweenEvictionRunsMillis=1800000
                numTestsPerEvictionRun=3
                //test the connection while its idle, before borrow and return it
                testOnBorrow=true
                testWhileIdle=true
                testOnReturn=true
                validationQuery="SELECT 1"
            }
        }
    }
}
