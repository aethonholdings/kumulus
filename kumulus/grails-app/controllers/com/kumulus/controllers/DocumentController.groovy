package com.kumulus.controllers

import com.kumulus.domain.*
import grails.converters.*

class DocumentController {
    
    def taskService
    def permissionsService
    def structureService
    def filesystemService
        
    def merge() {
        def data = request.JSON
        Document mergedDocument
        def documents = []
        data?.documents.each {
            def document = Document.findById(it)  // NEED PERMISSIONS CHECKS HERE AGAINST THE TASKS
            documents.add(document)
        }
        mergedDocument = structureService.merge(documents)
        if(mergedDocument) taskService.createTask(mergedDocument, Task.OCR_DOCUMENT, Task.READY_FOR_BATCH_INSTANCE)
        def response = [done: true]
        render response as JSON
    }
            
    def update() {  
        def data = request.JSON
        def response = [done: false]
        def document = Document.findById(data?.id)
        structureService.update(document, data)
        render response as JSON
    }
    
    def index() {
        def file = UFile?.findById(params?.ufileId)
        def document = Document?.findById(params?.documentId)
        filesystemService.indexDocument(document, file)
        render "document indexed"
    }
    
}
