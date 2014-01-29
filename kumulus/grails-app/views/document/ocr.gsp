<html>
  <head>
    <title>OCR data entry | Kumulus</title>
    <g:javascript src='kumulus/preview.js'/>
    <g:javascript src='kumulus/autocomplete.js'/>
    <g:javascript src='kumulus/lineItems.js'/>
  </head>
  <body>
    <div class="pure-g">
      <div class="pure-u-1-8">
        <div class="kumulus-container kumulus-element-border kumulus-scrollable-y">
          <div id="page-strip" class="kumulus-filmstrip">
            <ul id="pages">
              <g:each var="page" in="${document.pages}">
                <li documentId="${document.id}"><g:kumulusImg image="${page.thumbnailImage}" class="kumulus-thumbnail kumulus-element-border" height="140" width="100" onClick="selectPage(this);" viewId="${page.viewImage.id}" scanId="${page.scanImage.id}"/></li>
              </g:each>
            </ul>
          </div>
        </div>
      </div>
      <div class="pure-u-7-8">
        <div class="kumulus-container kumulus-element-border">
          <div class="kumulus-container-half">
            <div class="pure-g">
              <div class="pure-u-3-4">
                <div class="kumulus-container-half kumulus-element-border kumulus-scrollable-y">
                  <div class="kumulus-preview">
                    <img id="preview-img" onClick="zoom();">
                  </div>
                </div>
              </div>
              <div class="pure-u-1-4">
                <div class="kumulus-container-half kumulus-element-border">
                  <div class="kumulus-data-entry">
                    <g:form name="document" action="update" id="${document.id}" class="pure-form pure-form-stacked">
                      <fieldset>
                        <div class="pure-control-group">
                          <label for="documentType">* Document type</label>
                          <select id="documentType" name="documentType" value="${document.type}" class="pure-input-1">
                            <g:each var="documentType" in='${documentTypes}'>
                              <option value="${documentType.id}" <g:if test="${documentType.id==4}">selected</g:if>>${documentType.name}</option>
                            </g:each>
                          </select>
                        </div>
                        <div class="pure-control-group">
                          <label for="company">* Issuing company</label>
                          <input id="company" name="company" type="text" value="${document.company?.name}" class="pure-input-1 ui-widget"></input>
                        <div class="pure-control-group">
                          <label for="date">* Date</label>                
                          <input id="date" name="date" type="date" value="${document.date}" class="pure-input-1"></input>
                        </div>
                        <div class="pure-control-group">
                          <label for="documentId">* Identifier</label>
                          <input id="documentId" name="documentId" type="text" value="${document.identifier}" class="pure-input-1"></input>
                        </div>
                      </fieldset>
                    </g:form>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="kumulus-container-half kumulus-element-border">
            <div class="kumulus-line-item-table pure-table-horizontal">
              <table id="lineItems" class="kumulus-small-font">
                <thead>
                  <th>Id</th>
                  <th>Page</th>
                  <th>Date</th>
                  <th>Description</th>
                  <th>Quantity</th>
                  <th>Currency</th>
                  <th>Price</th>
                  <th>Amount</th>
                  <th>Action</th>
                </thead>
                <tbody class="kumulus-scrollable-y">
                  <g:each var="page" in="${document.pages}">
                    <g:each var="lineItem" in="${page.lineItems}">
                      <tr>
                        <td class="kumulus-column-id kumulus-row-${lineItem.id}"></td>
                        <td class="kumulus-column-page kumulus-row-${lineItem.id}"></td>
                        <td class="kumulus-column-date kumulus-row-${lineItem.id}"></td>
                        <td class="kumulus-column-description kumulus-row-${lineItem.id}"></td>
                        <td class="kumulus-column-quantity kumulus-row-${lineItem.id}"></td>
                        <td class="kumulus-column-currency kumulus-row-${lineItem.id}"></td>
                        <td class="kumulus-column-price kumulus-row-${lineItem.id}"></td>
                        <td class="kumulus-column-amount kumulus-row-${lineItem.id}"></td>
                        <td></td>
                      </tr>
                    </g:each>
                  </g:each>
                </tbody>
                <tfoot>
                  <tr>  
                    <td><input size="4" type="text" value="" class="kumulus-column-id kumulus-row-new"></input></td>
                    <td><input size="4" type="text" value="" class="kumulus-column-page kumulus-row-new"></input></td>
                    <td><input size="4" type="text" value="" class="kumulus-column-date kumulus-row-new"></input></td>
                    <td><input size="4" type="text" value="" class="kumulus-column-description kumulus-row-new"></input></td>
                    <td><input size="4" type="text" value="" class="kumulus-column-quantity kumulus-row-new"></input></td>
                    <td><input size="4" type="text" value="" class="kumulus-column-currency kumulus-row-new"></input></td>
                    <td><input size="4" type="text" value="" class="kumulus-column-id kumulus-row-new"></input></td>
                    <td><input size="4" type="text" value="" class="kumulus-column-id kumulus-row-new"></input></td>
                    <td><input size="4" type="text" value="" class="kumulus-column-id kumulus-row-new"></input></td>
                  </tr>
                </tfoot>
              </table>
            </div>
          </div>
        </div>            
        <div class="kumulus-button-bank">
          <a class="pure-button" href="#">Save</a>
        </div>
      </div>
    </div>
  </body>
</html>