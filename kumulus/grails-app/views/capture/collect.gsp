
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/dynatree/skin', file: 'ui.dynatree.css')}"/>
    <g:javascript src='dynatree/jquery.dynatree.js'/>
    <g:javascript src='kumulus/nodeTree.js'/>
    <g:javascript src='kumulus/disableDiv.js'/>
    <title>Prepare scans | Kumulus</title>
  </head>
  <body>
    <span id="project" projectID="${project.id}"/>  
    <div class="pure-g">
      <div class="pure-u-1-3">
        <div class="kumulus-container kumulus-scrollable-y kumulus-scrollable-x kumulus-element-border">
          <div class="kumulus-node-tree">
            <div id="nodeTree" class="jstree-draggable"></div>
          </div>
        </div>
        <div class="kumulus-button-bank">
          <a id="button-add" class="pure-button" onclick="add_node();">Add</a>
          <a id="button-edit" class="pure-button" onclick="update_node();">Edit</a>
          <a id="button-delete" class="pure-button" onclick="delete_node();">Delete</a>
          <a id="button-search" class="pure-button" onclick="search_node();">Search</a>
          <g:render template="/layouts/searchPopup" />
        </div>
      </div>
      <div class="pure-u-2-3">
        <div class="kumulus-container kumulus-element-border">
          <div class="kumulus-data-entry">
            <form name="newContainer" action="add" method="POST" class="pure-form pure-form-aligned">
              <fieldset>
                <div class="kumulus-hight kumulus-control-group kumulus-element-border">
                    <div class="kumulus-barcode-image"> <img src="../../images/barcode.png" class="kumulus-image"  alt="no image"/> </div>
                  <label for="barcode"class="kumulus-label">* Stick the Barcode sticker on the container or Box and Scan the Barcode</label>
                  <input id="barcode" type="text" placeholder="Scan container barcode" disabled>
                </div>  
                <div class="kumulus-hight kumulus-control-group kumulus-element-border">
                  <div class="kumulus-barcode-image"> <img src="../../images/container-type.jpg" class="kumulus-image"  alt="no image"/> </div>
                  <label for="type" class="kumulus-label">* Please select a container type</label>
                    <select id="type" disabled>
                     <option value=""  selected="selected">Please Select Container Type</option>
                      <g:each in="${nodeTypes}" var="nodeType">
                          <option value="${nodeType?.id}">${nodeType?.name}</option>
                      </g:each>                  
                  </select>
                </div>
                <div class="kumulus-hight kumulus-control-group kumulus-element-border">
                  <div class="kumulus-barcode-image"> <img src="../../images/container-name.jpg" class="kumulus-image"  alt="no image"/> </div>
                  <label for="name" class="kumulus-label">* Please enter the container name</label>
                  <input id="name" type="text" placeholder="Enter name here" disabled>
                </div>
                
                <div class="kumulus-hight kumulus-control-group kumulus-element-border">
                    <div class="kumulus-barcode-image"> <img src="../../images/comment.png" class="kumulus-image"  alt="no image"/> </div>
                  <label for="comment" class="kumulus-label">Comments</label>
                  <textarea id="comment" type="text" disabled rows="5"></textarea>
                </div>
              </fieldset>
            </form>
          </div>
        </div>
        <div class="kumulus-button-bank">
            <button type="button" id="button-save" class="pure-button pure-button-primary" onclick="save(); " disabled="disabled">Save </button>
          <button type="button" id="button-cancel" class="pure-button" onclick="cancel();" disabled="disabled">Cancel</button>
        </div>
      </div>
    </div>
  </body>
</html>
