package com.kumulus.taglibs

import com.kumulus.domain.*

class KumulusTagLib {
    
    def permissionsService
    def workflowService
    
    static defaultEncodeAs = 'text'
    //static encodeAsForTags = [tagName: 'raw']
        
    def userCompany = { attrs, body ->
        String company = permissionsService.company
        if(company) {
            out << company
        }
    }
    
    def pageTitle = { attrs, body ->
        if(attrs.text) {
            out << attrs.text
        }
    }
    
    def kumulusImg = { attrs ->
        
        if(attrs.image) {
            
            def imgHeight = new java.math.BigDecimal(attrs.image.height)
            def imgWidth = new java.math.BigDecimal(attrs.image.width)
            def imgRatio = imgHeight/imgWidth

            def height = new java.math.BigDecimal(attrs?.height)
            def width = new java.math.BigDecimal(attrs?.width)

            def outputHeight
            def outputWidth

            if(height/width>imgRatio) {
                // height is the constraint
                outputHeight = height
                outputWidth = Math.round(height/imgRatio)
            } else {
                // width is the constraint
                outputHeight = Math.round(width*imgRatio)
                outputWidth = width
            }

            out << "<img "
            attrs.each { key, value ->
                if(key!="image") out << key << "='${value}' "
            }
            out << "src='${request.contextPath}/image/get/${attrs.image.id}' "
            out << "/>"
        }
    }
    
    def taskDescription = { attrs, body ->
        switch(attrs?.task.type) {
            case(Task.TYPE_BUILD):
                out << "Build document"
                break
            case(Task.TYPE_OCR):
                out << "OCR document"
                break
            case(Task.TYPE_REVIEW):
                out << "Review document"
                break
        }
    }
    
    def taskQueue = { attrs ->
        
        if(attrs?.userId){
            def userTasks = workflowService.getTaskQueues(attrs?.userId)            

            out << "<h3>You have ${userTasks.count} tasks outstanding</h3>"
            out << "<ul>"
            out << "    <li>${userTasks.types.BUILD.count} scans to be assembled into documents</li>"
            out << "        <ul>"

            userTasks.types.BUILD.tasks.groupBy({ task -> task.project }).each {
                out << "<li>"
                out << it.key.projectName << " - "
                out << g.link(controller: "capture", action: "build", params: [projectId: it.key.id], "${it.value.size()}")
                out << "</li>"
            }
            
            out << "        </ul>"
            out << "    <li>${userTasks.types.PROCESS.count} documents to be processed - "
            out << g.link(controller: "structure", action: "process", "Perform next")
            out << "</li>"
            out << "    <li>${userTasks.types.VALIDATE.count} documents to be reviewed"
            out << "</ul>"            
        }
        
    }
    
}
