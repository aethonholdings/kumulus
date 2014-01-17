package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

class ExtractController {

    def springSecurityService
    def exportService
    def grailsApplication
    
    @Secured(['ROLE_EXTRACT'])
    def download() {
        response.contentType = grailsApplication.config.grails.mime.types['csv']
        response.setHeader("Content-disposition", "attachment; filename=extract")
        def project = Project.findById(params.id)
        def ledger = new ArrayList()
        if (project) {
            def nodes = Nodes?.findAllByProjectAndType(project, "D")
            nodes.each {node ->
                node.documents.each {document ->
                    document.lineItems.each { lineItem ->
                        def extract = [
                            id: lineItem?.id,
                            documentId: document.id,
                            company: lineItem?.document.company,
                            date: lineItem?.date,
                            description: lineItem?.description, 
                            currency: lineItem?.currency?.shortName,
                            quantity: lineItem?.quantity,
                            price: lineItem?.price, 
                            amount: lineItem?.amount
                        ]
                        ledger.add extract
                    }
                }
            }
            List fields = [
                "id", 
                "documentId",
                "company",
                "date",
                "description",
                "currency",
                "quantity",
                "price",
                "amount"
            ]
            Map labels = [
                id: "ID",
                documentId: "Document ID",
                company: "Company name",
                date: "Date",
                description: "Description",
                currency: "Currency",
                quantity: "Quantity",
                price: "Unit price",
                amount: "Amount"
            ]
            exportService.export('csv', response.outputStream, ledger, fields, labels, [:], [:])
        }
    }
    
}
