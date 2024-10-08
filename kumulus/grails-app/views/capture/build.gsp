
<html>
  <head>
    <title>Build documents | Kumulus</title>
    <g:javascript src='kumulus/classes/DocumentBuilder.js'/>
    <g:javascript src='kumulus/classes/DocumentViewer.js'/>
    <g:javascript src='kumulus/classes/Preview.js'/>
  </head>
  <body>
    <div class="pure-g">
      <div class="pure-u-1-5">
        <div class="kumulus-container">
          <div class="kumulus-widget-base kumulus-widget">
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">Container details</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="kumulus-data-entry pure-form">
              <fieldset>
                <div class="pure-control-group">
                  <label for="barcode">Barcode:</label>
                  <input id="barcode" type="text" name="barcode" class="pure-u-23-24" readonly></input>
                </div>
                <div class="pure-control-group">
                  <label for="containername">Container Name:</label>
                  <input id="containername" type="text" name="containername" class="pure-u-23-24" readonly/>
                <div class="pure-control-group">
                  <label for="type">Container Type:</label>                
                  <input id="containertype" type="text" name="type" class="pure-u-23-24" readonly></input>
                </div>
                <div class="pure-control-group">
                  <label for="comment">Comment:</label>
                  <input id="comment" type="text" name="comment" class="pure-u-23-24" readonly/>
                </div>
              </fieldset>
            </div>
          </div>
        </div>
      </div>
      <div class="pure-u-2-5">
        <div class="kumulus-container">
          <div class="kumulus-widget-base kumulus-widget">
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">Image preview</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="kumulus-preview">
              <img id="preview-img" class="kumulus-element-border" onClick="zoom();">
            </div>
          </div>
        </div>
      </div>
      <div class="pure-u-2-5">
        <div class="kumulus-container">
          <div class="kumulus-widget-base kumulus-widget">
            <div class="kumulus-widget-header">
              <span class="kumulus-widget-header-title">Document builder</span>
              <span class="kumulus-widget-header-action"></span>
            </div>
            <div class="pure-g">
              <div class="pure-u-3-4"> 
                <div class="pure-g">
                  <div class="pure-u-1-2">
                    <div id="page-strip" class="kumulus-filmstrip kumulus-scrollable-y">
                      <ul id="pages" class="connectedSortable">
                        <g:each var="task" in="${tasks}">
                          <g:each var="page" in="${task.document.pages.sort { it.number }}">
                            <li taskId ="${task.id}" documentId="${task.document.id}"><g:kumulusImg image="${page.thumbnailImage}" class="kumulus-thumbnail" height="140" width="100" viewId="${page.viewImage.id}" scanId="${page.scanImage.id}" barcode="${page.node.parent.barcode.text}" containerName="${page.node.parent.name}" containerType="${page.node.parent.type.name}" containerComment="${page.node.parent.comment}"/></li>
                          </g:each>
                        </g:each>
                      </ul>
                    </div>
                  </div>
                  <div class="pure-u-1-2">
                    <div id="document-strip" class="kumulus-filmstrip kumulus-scrollable-y">
                      <ul id="documents" class="connectedSortable">
                        
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
              <div class="pure-u-1-4">
                <button class="pure-button kumulus-float-right" onClick="save();">Save</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>

