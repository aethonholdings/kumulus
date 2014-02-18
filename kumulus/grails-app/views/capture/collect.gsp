<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/dynatree/skin', file: 'ui.dynatree.css')}"/>
    <g:javascript src='dynatree/jquery.dynatree.js'/>
    <g:javascript src='kumulus/nodeTree.js'/>
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
        </div>
      </div>
      
      
      <div class="pure-u-2-3">
        <div class="kumulus-container kumulus-element-border kumulus-hight">
          <div class="kumulus-data-entry">
            <form name="newContainer" action="add" method="POST" class="pure-form pure-form-aligned">
              <fieldset>
                <div class="pure-control-group kumulus-element-border kumulus-hight" id="barcodeDiv">
                  <label for="barcode">* Barcode</label>
                  <input id="barcode" type="text" placeholder="Scan container barcode" disabled onblur="disablebarcodeDiv();">
                </div>
                <div class="pure-control-group kumulus-element-border kumulus-hight" id="nameDiv">
                  <label for="name">* Name</label>
                  <input id="name" type="text" placeholder="Enter name here" class="pure-input-1-2" disabled onblur="disablenameDiv();">
                </div>
                <div class="pure-control-group kumulus-element-border kumulus-hight">
                  <label for="type">* Type</label>
                  <select id="type" class="pure-input-1-4" disabled onblur="disabletypeDiv();">
                    <option>Container</option>  
                    <option>Box</option>
                  </select>
                </div>
                <div class="pure-control-group kumulus-element-border kumulus-hight">
                  <label for="comment">Comment</label>
                  <textarea id="comment" type="text" class="pure-input-1-2" disabled rows="5"></textarea>
                </div>
              </fieldset>
            </form>
          </div>
        </div>
        <div class="kumulus-button-bank">
          <a id="button-save" class="pure-button pure-button-primary" onclick="save();">Save</a>
          <a id="button-cancel" class="pure-button" onclick="cancel();">Cancel</a>
        </div>
      </div>
    </div>
  </body>
</html>
