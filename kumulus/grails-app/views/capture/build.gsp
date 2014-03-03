
<html>
  <head>
    <title>Build documents | Kumulus</title>
    <g:javascript src='kumulus/documentBuilder.js'/>
    <g:javascript src='kumulus/preview.js'/>
  </head>
  <body>
    <div class="pure-g">
         <div class="pure-u-1-4">
          <div class="kumulus-container kumulus-element-border pure-form">
              <div class="kumulus-data-entry">
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
      <div class="pure-u-2-5">
        <div class="kumulus-container kumulus-element-border">
          <div class="kumulus-preview">
            <img id="preview-img" class="kumulus-element-border" onClick="zoom();">
          </div>
        </div>
      </div>
      <div class="pure-u-1-3">
        <div class="kumulus-container kumulus-element-border">
          <div class="pure-g">
            <div class="pure-u-5-6">    
              <div class="pure-g">
                <div class="pure-u-1-2">
                  <div class="kumulus-container-inside kumulus-scrollable-y kumulus-filmstrip-margin-top">
                    <div id="page-strip" class="kumulus-filmstrip">
                      <ul id="pages" class="connectedSortable">
                        <g:each var="task" in="${tasks}">
                          <g:each var="page" in="${task.document.pages.sort { it.number }}">
                               <li taskId ="${task.id}" documentId="${task.document.id}"><g:kumulusImg image="${page.thumbnailImage}" class="kumulus-thumbnail kumulus-element-border" height="140" width="100" viewId="${page.viewImage.id}" scanId="${page.scanImage.id}" barcode="${page.node.parent.barcode.text}" containerName="${page.node.parent.name}" containerType="${page.node.parent.type.name}" containerComment="${page.node.parent.comment}"/></li>
                          </g:each>
                        </g:each>
                      </ul>
                    </div>
                  </div>
                </div>
                <div class="pure-u-1-2">
                  <div class="kumulus-container-inside-full kumulus-scrollable-y">
                      <div class="kumulus-filmstrip-header">
                    <div id="document-strip" class="">
                        <span><h6>Drag the Doc Here</h6></span>
                        <li id="flimstrip-first-li" class="kumulus-filmstrip"></li>
                    </div>
                      </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="pure-u-1-8">
              <p class="kumulus-filmstrip-button-margin-top"><button class="pure-button" onClick="save();">Save</button></p>
            </div>
          </div>
        </div>
      </div>
      <div class="pure-u-1 kumulus-padding">
          <g:link class="pure-button kumulus-float-right kumulus-margin-right" controller='home' action='index'>Home</g:link>
          <div class="clear-float"></div>
      </div>
    </div>
  </body>
</html>

