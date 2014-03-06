<html>
  <head>
      <title>Edit project | Kumulus</title>
      <g:javascript src='jquery/validate/validate.js'/>
      <g:javascript src='kumulus/validation.js'/>
  </head>
  <body>
      <div class="pure-g">
          <div class="pure-u-1">
              <div class="kumulus-container kumulus-scrollable-y kumulus-padding">
                 <g:form name="project" action="save" id="${project?.id}" class="pure-form pure-form-aligned">
                  <div class="pure-control-group">
                    <label for="projectName">* Project name</label>
                    <input id="projectName" name="projectName" type="text" value="${project?.projectName}" class="pure-input-1-2">
                  </div>
                  <div class="pure-control-group">
                    <label for="clientName">* Client name</label>
                    <input id="ClientName" name="ClientName" type="text"  class="pure-input-1-2 ui-widget"></input>
                  </div>
                  <div class="pure-control-group">
                    <label for="comment">Comment</label>
                    <textarea id="comment" name="comment" type="text" class="pure-input-1-2" rows="5">${project?.comment}</textarea>
                  </div>
                  <div class="pure-controls">
                    <g:link controller="home" action="index" class="pure-button">Cancel</g:link>
                    <input type="submit" id="createUser" value="Save" class="pure-button pure-button-primary kumulus-button-align" onclick="validate();"></input>
                  </div>
                </g:form>
              </div>
        </div>
      </div>
  </body>
</html>