package com.kumulus.controllers

import com.kumulus.domain.*
import grails.converters.*

class NodeController {

    def captureService
    def permissionsService
        
    def getRoot() {
        def project = Project.findById(params?.id)
        if(permissionsService.checkPermissions(project)) {
            def rootNode = captureService.renderRoot(Project?.findById(params?.id))   
            render rootNode as JSON
        }
    }    
    
    def getChildren() {
        def treeNodes = []
        def node = Node.findById(params?.id)   
        if (permissionsService.checkPermissions(node)) {
            def children = Node.findAll {
                (parent == node && type.isContainer == true)               // get all the non-page children nodes
            }
            children.each { treeNodes.add(captureService.renderNode(it)) }   
        }
        render treeNodes as JSON
    }
    
    def update() {
        def data = request.JSON
        def node = Node.findById(data?.id)
        if (permissionsService.checkPermissions(node)) {
            captureService.updateNode(node, data?.barcode, data?.name, data?.comment, data?.type, Node.STATUS_OPEN, Node.LOCATION_CLIENT)
            render node as JSON
        }
    }
    
    def insert() {
        def data = request.JSON
        def response = [done: false, message: "Error"]
        def project = Project.findById(data?.project)
        def parent
        if(data?.parentID && data.parentID != "ROOT") 
        parent = Node.findById(data?.parentID) else parent = null
        if(project && permissionsService.checkPermissions(project) && captureService.insertNode(parent, project, data?.barcode, data?.name, data?.comment, data?.type)) { 
            response.done = true
            response.message = "Success"
    
        }
        render response as JSON
    }
    
    def delete() {

        def data = request.JSON
        def response = [done: false]
        def node = Node.findById(data?.id)
        if(permissionsService.checkPermissions(node)) {
            captureService.deleteNode(data.id)
            response.done = true
        }
        render response as JSON
    }
    
    def list(){
        
        def nodes = []
        nodes = Node.findAll {
            (type == NodeType.findByName("Box") && status == Node.STATUS_CLOSED)
        }
        render nodes as JSON
    }

    def move(){
        def data = request.JSON
        def parent
        if(data?.targetId=="ROOT") parent = null else parent = Node.findById(data?.targetId)
        def child = Node.findById(data?.id)
        def response = [done: false]
        if(permissionsService.checkPermissions(parent) && permissionsService.checkPermissions(child)) {
            if(parent) child.parent = parent else child.parent = null
            child.save()
            response.done = true
        }
        render response as JSON
    }

    def test(){
        def data = request.JSON
        def response = [done: false]
        // handle this to send email request
        // need to create a logistics shipment instance
        render response as JSON
    }
    
    def searchByBarcode() {
        def response = []
        def node=Node.findByBarcode(Barcode.findByText(request.JSON?.barCode))
        if(node && permissionsService.checkPermissions(node)) {            
            response = captureService.renderNodeHierarchy(node)
        }
        render response as JSON
    }
    
    def checkBarcode() {
        def status= ''
        def data = request.JSON
        def barcode = Barcode.findByText(data?.barcode)
        def node = Node.findByBarcode(barcode)
        if(node || !barcode) status = 'true' else status = 'false'
        def response = [status : status]
        render response as JSON
    }
    
    def getDocuments() {
        def data = request.JSON
        def response = []
                
        if(data?.node) {
            def nodes = Node.findAll() { node -> 
                parent.id == data.node.toLong()
                page != null
                [order: "createDatetime", sort: "asc"]
            }
            nodes.page.groupBy({page -> page.document}).each { row ->
                def document = row.key
                def responseData = [
                    id: document.id, 
                    status: document.status(),
                    thumbnailImageId: document.pages[0].thumbnailImage.id
                ]
                response.add(responseData)
            }
        }
        render response as JSON
    }
    def containerToTransport(){
        def data = request.JSON
        def node = Node.findById(data?.id)
        if (permissionsService.checkPermissions(node)) {
            captureService.updateContainer(node, Node.STATUS_OPEN)
            render node as JSON
        }
    }
    
    def fetchFromStorage() {
         def response = [done: true]
         //fetch from storage
         render response as JSON
         println(response)
    }
}
