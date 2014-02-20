package com.kumulus.services

import com.kumulus.domain.*
import org.grails.plugins.imagetools.ImageTool
import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils
import com.lucastex.grails.fileuploader.UFile

@Transactional
class FilesystemService {
    
    def grailsApplication
    def permissionsService  // push this to controllers
    def tikaService
    
    def generateLiteral() {
        String literal = System.currentTimeMillis()
        literal += RandomStringUtils.random(2, true, true).toUpperCase()
        return(literal)
    }
    
    def indexImage(parentNode, uFile, scanBatch) {
        if(parentNode && uFile && scanBatch) {
            // define the necessary paths and files
            def literal = generateLiteral()
            def userId = permissionsService.getUsername()
            Date timestamp = new Date()
            File stagingPath = new File(uFile.path.replace(uFile.name, ""))
            File targetPath = new File(grailsApplication.config.filesystem.main + "/" + parentNode.project.literal + "/pages/" + literal + "/")
            String tmpName = stagingPath.canonicalPath + "/" + literal
            def imageFiles = [
                scanImage: new File(tmpName + "-S.tiff"), 
                viewImage: new File(tmpName +"-V.jpg"),
                thumbnailImage: new File(tmpName +"-T.jpg")
            ]
                        
            // load the imported image to buffer and generate the image files
            def imageTool = new ImageTool()
            imageTool.load(uFile.path)            
            imageTool.writeResult(imageFiles.scanImage.getAbsolutePath(), "TIFF")
            imageTool.writeResult(imageFiles.viewImage.getAbsolutePath(), "JPEG")
            imageTool.thumbnail(300)
            imageTool.writeResult(imageFiles.thumbnailImage.getAbsolutePath(), "JPEG")   
            
            def documentType = DocumentType.findById(4)  // set the document type as unknown
            def document = new Document(
                status: Document.EDITABLE,
                type: documentType,
                company: null,
                date: null,
                literal: literal,
                file: null,
                project: parentNode.project,
                ocrTask: null
            )
            document.save()
            
            def task = new Task(
                userId: userId,
                created: timestamp,
                document: document,
                type: Task.BUILD_DOCUMENT,
                status: 0
            )
            task.save()

            def node = new Node(
                name: literal,
                type: NodeType.findById(3),                                     // node type is page
                parent: parentNode,
                project: parentNode.project,
                creatorId: userId,
                lastUpdateId: userId,
                createDatetime: timestamp,
                lastUpdateDatetime: timestamp,
                status: Node.STATUS_CLOSED
            )
            node.save()
            
            def page = new Page(
                number: 1,
                first: true,
                last: true,
                literal: literal,
                lineItems: [],
                node: node, 
                document: document,
                scanBatch: scanBatch
            )
                                    
            // now move the image files to the main filesystem and generate the relevant images and UFiles
            def images = [:]
            targetPath.mkdir()
            imageFiles.each() { key, value ->
                def targetFile = new File(targetPath.getAbsolutePath() + "/" + value.name)
                value.renameTo(targetFile)
                def file = new UFile(
                    size: targetFile.size(),
                    path: targetFile.getAbsolutePath(),
                    name: targetFile.name,
                    extension: value.name.tokenize('.')?.last(),
                    dateUploaded: timestamp,
                    downloads: 0
                )
                file.save()
                imageTool.load(file.path)
                def image = new Image(
                    height: imageTool.getHeight(),
                    width: imageTool.getWidth(),
                    file: file,
                    thumbnail: false,
                    compressed: false,
                    page: page
                )
                image.save()
                images.put(key, image)
            }
            page.scanImage = images.scanImage
            page.viewImage = images.viewImage
            page.thumbnailImage = images.thumbnailImage
            page.save()
            
            // clean up the staging entities
            uFile.delete(flush:true)
            stagingPath.deleteDir() 
        }
    }
    
    def indexDocument(document, uFile) {
        document.file = uFile
        File file = new File(uFile.path)
        document.text = tikaService.parseFile(file)
        document.save()
    }
    
    def newProject(params) {
        def literal = generateLiteral()
        
        // create the necessary directories
        String path = grailsApplication.config.filesystem.main + literal + "/"
        File targetPath = new File(path)
        targetPath.mkdir()
        targetPath = new File(path + "docs/")
        targetPath.mkdir()
        targetPath = new File(path + "pages/")
        targetPath.mkdir()
        
        // create the database instances
        def client = new Company([name: params?.client])         // NEED TO CHECK THE PREEXISTENCE OF THE COMPANY
        client.save()
        def project = new Project([projectName: params?.projectName, comment: params?.comment, status: "A", company: permissionsService.getCompany(), lineItems:[], nodes:[], client: client, literal: literal, path: path])
        project.save()
        return(project)
    }
   
}