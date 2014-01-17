package com.kumulus.controllers

import grails.plugin.springsecurity.annotation.Secured
import com.kumulus.domain.*

class HomeController {
    
    def springSecurityService
    def ledgerService
    def exportService
    def grailsApplication
    
    @Secured(['ROLE_CLASSIFY', 'ROLE_ADMIN', 'ROLE_VIEW', 'ROLE_COLLECT', 'ROLE_EXPORT', 'ROLE_VALIDATE', 'ROLE_IMPORT'])
    def index() { 
        render view:"index", layout:"home", model:[pageTitle: "Home"]
    }
    
    @Secured(['ROLE_COLLECT'])
    def collect() { 
        def projectList = Project.findAllByStatus("A")
        render view:"collect", layout:"home", model:[pageTitle: "Collect archive documents", projects: projectList]
    }
    
    @Secured(['ROLE_EXTRACT'])
    def extract() { 
        def projectList = Project.findAllByStatus("A")
        render view:"extract", layout:"home", model:[pageTitle: "Extract ledger", projects: projectList]
    }
    
    @Secured(['ROLE_REVIEW'])
    def review() { 
        def auth = springSecurityService.getAuthentication()
        String username = auth.getPrincipal().getUsername()
        def tasks = Task.findByUserId(username)
        render view:"review", layout:"home", model:[pageTitle: "Pending data review tasks", user: username, tasks: tasks]
    }
    
    @Secured(['ROLE_ACCESS'])
    def access() { 
        render view:"access", layout:"home", model:[pageTitle: "Access archive"]
    }
    
    @Secured(['ROLE_EXTRACT'])
    def download() {
        def project = Project.findById(params?.id)
        response.contentType = grailsApplication.config.grails.mime.types['csv']
        response.setHeader("Content-disposition", "attachment; filename=extract")
        def export = ledgerService.getCSV(project)
        if(export) exportService.export('csv', response.outputStream, export.ledger, export.fields, export.labels, [:], [:])
    }

}