package com.kumulus.domain

class Node {

    static searchable = [only: ['name', 'barcode', 'comment', 'createDateTime']]
    
    static final String STATUS_OPEN = "0"
    static final String STATUS_CLOSED = "1"
    static final String STATUS_REOPENED = "2"
    
    static final String LOCATION_CLIENT = "My premises"
    static final String LOCATION_STORAGE = "In storage"
    
    String name
    String barcode
    String comment
    String internalComment
    String status
    String creatorId
    String lastUpdateId
    String location
    Date createDatetime
    Date lastUpdateDatetime
    Project project
    Node parent 
    NodeType type
    Page page

    static mapping = {
        id column: "node_id"
        parent column: "parent_node_id"
        version false
    }

    static constraints = {
        name nullable: true, maxSize: 45
        barcode nullable: true, maxSize: 45
        comment nullable: true, maxSize: 300
        internalComment nullable: true, maxSize: 300
        status maxSize: 2
        createDatetime nullable: true
        lastUpdateDatetime nullable: true
        page nullable: true
        location maxSize: 12
    }
    
    String owner() {
        return(project.company)
    }
    
}
