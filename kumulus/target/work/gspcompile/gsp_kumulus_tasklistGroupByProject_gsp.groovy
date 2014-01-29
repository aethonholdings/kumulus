import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kumulus_tasklistGroupByProject_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/task/listGroupByProject.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
expressionOut.print(title)
printHtmlPart(2)
})
invokeTag('captureTitle','sitemesh',3,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',3,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',4,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
for( _it577798014 in (projectList) ) {
changeItVariable(_it577798014)
printHtmlPart(5)
expressionOut.print(it?.projectName)
printHtmlPart(6)
expressionOut.print(it?.client.name)
printHtmlPart(6)
expressionOut.print(tasksByProject.get(it.id)?.tasks?.size())
printHtmlPart(6)
createClosureForHtmlPart(7, 3)
invokeTag('link','g',21,['controller':("document"),'action':("build"),'id':(it.id)],3)
printHtmlPart(8)
}
printHtmlPart(9)
})
invokeTag('captureBody','sitemesh',26,[:],1)
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1390917381000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
