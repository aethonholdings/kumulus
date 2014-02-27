<html>
    <head>
        <title>OCR data entry | Kumulus</title>
        <g:javascript src='jquery/validate/validate.js'/>
        <g:javascript src='kumulus/preview.js'/>
        <g:javascript src='kumulus/process.js'/>
        <g:javascript src='kumulus/validation.js'/>
    </head>
    <body>
        <g:form name="structure" action="save" id="${document.id}" class="pure-form pure-form-stacked">
            <div class="pure-g">
                <div class="pure-u-1-8">
                    <div class="kumulus-container kumulus-element-border kumulus-scrollable-y">
                        <div id="page-strip" class="kumulus-filmstrip">
                            <ul id="pages">
                                <g:each var="page" in="${document.pages.sort{it.number}}">
                                    <li documentId="${document.id}"><g:kumulusImg image="${page.thumbnailImage}" class="kumulus-thumbnail kumulus-element-border" height="140" width="100" viewId="${page.viewImage.id}" scanId="${page.scanImage.id}" pageId="${page.id}" pageNumber="${page.number}"/></li>
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
                                            <fieldset>
                                                <input type="hidden" name ="documentId" value="${document.id}"/>
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
                                                </div>
                                                <div class="pure-control-group">
                                                    <label for="date">* Date</label>                
                                                    <input type="date" name ="date" value="${document.date}" class="pure-input-1"></input>
                                                </div>
                                                <div class="pure-control-group">
                                                    <label for="documentId">* Identifier</label>
                                                    <input id="documentId"  name="identifier" type=text value="${document.identifier}" class="pure-input-1"></input>
                                                </div>
                                                <div>
                                                    <label for="documentId">*Currency</label>   
                                                    <select class="pure-input-1" name="currency">
                                                        <g:each var="currency" in="${currencies}">
                                                            <option value="${currency.shortName}" <g:if test="${currency.shortName=='SGD'}">selected</g:if>>${currency.shortName}</option>
                                                        </g:each>
                                                    </select>
                                                    </td>
                                                </div>
                                            </fieldset>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="kumulus-container-half-1 kumulus-element-border kumulus-magrin-top kumulus-scrollable-y">
                            <div class="kumulus-line-item-table">
                                <table id="lineItems" class="pure-table-horizontal">
                                    <thead>
                                    <th>ID</th>
                                    <th>*Page</th>
                                    <th>*Description</th>                  
                                    <th>Date</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>*Amount</th>
                                    <th>Actions</th>
                                    </thead>
                                    <tbody class="kumulus-vertical-align-top">
                                        <tr class="new" onClick="send(this)">
                                            <td><input id="lineItemId" name="lineItemId" size="4" type="text" value="" class="kumulus-column-id new" readonly></input></td>
                                            <td>
                                              <input id="pageNo" name="pageNo" size="2" type="text" value="" class="kumulus-column-page new" onkeypress="CheckNumeric(event)" ></input>
                                              <input id="pageId" name="pageId" type="hidden" value=""></input>
                                            </td>
                                            <td><input id="focus" name="description" size="25" type=text value="" class="kumulus-column-description new" ></input></td>
                                            <td><input id="lineItemDate" name="lineItemDate" size="4" type="date" value="" class="kumulus-column-date new"></input></td>
                                            <td><input id="quantity" name="quantity" type=text  size="6" value="" class="kumulus-column-quantity new" onkeydown="CheckNumeric(event)"></input></td>
                                            <td><input id="price" name="price" size="6" type="text" value="" class="kumulus-column-price new" onkeydown="CheckNumeric(event)" onchange="total(this)"></input></td>
                                            <td><input id="amount" name ="amount" size="6" type="text"  value="" class="kumulus-column-amount new" onkeydown="CheckNumeric(event)" id="test"></input></td>
                                            <td><a class="add" href="#" >Add</a></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>   
                    <div class="kumulus-button-bank">
                        <input type="button" id ="save" value="Save and next" class="pure-button"></input>
                         <a class="pure-button" href="#">Exit</a>
                    </div>
                </div>
            </g:form>
    </body>
</html>