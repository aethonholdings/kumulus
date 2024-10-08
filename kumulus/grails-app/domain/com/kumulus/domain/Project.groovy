package com.kumulus.domain

class Project {

    static final String STATUS_ACTIVE = "A"
    static final String STATUS_CLOSED = "C"
    
    String company
    Company client
    String projectName
    String status
    String comment
    String literal
    String path
    String ownerId
    Date created
    Date closed

    static hasMany = [nodes: Node, lineItems: LineItem, documents: Document, tasks: Task]

    static mapping = {
        id column: "project_id"
        version false
    }

    static constraints = {
        projectName nullable: true, maxSize: 50
        status nullable: true, maxSize: 10
        company nullable: false, maxSize: 50
        ownerId maxSize: 20
        comment nullable: true
        literal nullable: false
        path nullable: false
        closed nullable: true
    }
    
    def afterDelete() {
        try {
            File f = new File(path)
            if (f.deleteDir()) {
                    log.debug "file [${path}] deleted"
            } else {
                    log.error "could not delete file: ${f}"
            }
        } catch (Exception exp) {
            log.error "Error deleting file: ${exp.message}"
            log.error exp
        }
    }
    
    String owner() {
        return(company)
    }
    
    String status() {
        switch(status) {
            case STATUS_ACTIVE:
                return("Currently open")
            case STATUS_CLOSED:
                return("Project closed")
        }
    }
        
}
