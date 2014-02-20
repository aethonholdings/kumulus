package com.kumulus.domain

class Task {

    // type enum constants
    static final byte BUILD_DOCUMENT = 1
    static final byte OCR_DOCUMENT = 2
    static final byte REVIEW_DOCUMENT = 3
    
    // status enum constants
    static final long ERROR = -1
    static final long READY_FOR_UPLOAD = 1
    static final long READY_FOR_BATCH_INSTANCE = 2
    static final long CREATED = 3
    static final long READY_FOR_REVIEW = 4
    static final long READY_FOR_VALIDATION = 5
    static final long FINISHED = 6
    
    String userId
    Date created
    Document document
    String batchInstanceID                                                      // can be removed
    String batchInstanceUrlId                                                   // can be removed
    long status
    byte type
    
    static belongsTo = [workItem: WorkItem]
    
    static constraints = {
        batchInstanceID nullable: true
        batchInstanceUrlId nullable: true
    }
}
