package com.kumulus.controllers

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import com.kumulus.domain.*
import java.text.SimpleDateFormat
import grails.converters.*
import java.sql.Timestamp


class ShipmentController {
    def permissionsService
    def logisticsService
    def accessService
        
    def create(){
        def data = request.JSON
        Company company = Company.findById(data?.companyId)
        Date date = new Date(Timestamp.valueOf(data?.scheduledDate).getTime())
        if(!data?.id && company && date) {            
            logisticsService.createShipment(company, date.clearTime())
        }
    }
    
    def delete() {
        def data = request.JSON
        logisticsService.deleteShipment(Shipment.findById(data?.id))
    }
    
    def update() {
        def data = request.JSON
        Date date = new Date(Timestamp.valueOf(data?.scheduledDate).getTime())
        Shipment shipment = Shipment.findById(data.id)
        if(shipment && date) {
            shipment.scheduled = date
            shipment.save()
        }
    }
    
    def getByBarcode() {
        def data = request.JSON
        def response = [
            success: false,
            data: []
        ]
        def node=Node.findByBarcode(Barcode.findByText(request.JSON?.barcode))
        if(node && permissionsService.checkPermissions(node) && node?.shipment) {  
            response.success = true
            response.data = accessService.renderShipment(node.shipment)
        }
        render response as JSON
    }
    
    def getNodes() {
        def data = request.JSON
        def response = [
            success: false,
            data: []
        ]
        def shipment = Shipment.findById(data?.shipmentId) 
        if(shipment) {
            shipment.nodes.each { node ->
                if(permissionsService.checkPermissions(node)) response.data.add(accessService.renderNode(node))
            }
        }
        render response as JSON
    }
}
