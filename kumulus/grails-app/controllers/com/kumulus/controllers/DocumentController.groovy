package com.kumulus.controllers

import com.kumulus.domain.*
import grails.converters.*

class DocumentController {
    
    def workflowService
    def permissionsService
    def captureService
    def structureService
    
    def merge() {
        def data = request.JSON
        def documents = []
        def tasks = []
        def response = [done: false]
        
        data?.tasks.each {
            // NEED TO CHECK PERMISSIONS HERE
            def task = Task.findById(it)
            tasks.add(task)
            documents.add(task.document)
        }
        def document = captureService.merge(documents)
        tasks.each { workflowService.completeTask(it) }
        workflowService.createTask(document, Task.OCR_DOCUMENT, permissionsService.getUsername())
        response.done = true
        render response as JSON
    }
            
    def update() {  
        def data = request.JSON
        def response = [done: false]
        def document = Document.findById(data?.id)
        structureService.update(document, data)
        render response as JSON
    }
}
