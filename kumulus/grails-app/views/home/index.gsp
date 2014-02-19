<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home | Kumulus</title>
  </head>
  <body>
   <div class="pure-g kumulus-small-font">
      <div class="pure-u-1-2">
           <div class="kumulus-container kumulus-scrollable-y kumulus-element-border">
               <h3>You Have Task Outstanding.</h3>
                <table class='pure-table pure-table-horizontal'>
                <thead>
                  <tr>
                    <th>Created Date</th>
                    <th>Type</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>     
                  <g:each in="${taskList}"  var="list">
                     <tr>   
                        <td>${list.created}</td>
                         <td><g:if test="${list.type=1}">
                                 BUILD_DOCUMENT
                            </g:if>
                            <g:elseif test="${list.type=2}">
                                  OCR_DOCUMENT
                            </g:elseif>
                            <g:elseif test="${list.type=3}">
                                   REVIEW_DOCUMENT
                            </g:elseif></td>
                         
                         <td><g:if test="${list.type=1}">
                                 <a href="#">Complete</a>
                            </g:if>
                            <g:elseif test="${list.type=2}">
                                  <a href="#">Complete</a>
                            </g:elseif>
                            <g:elseif test="${list.type=3}">
                                   <a href="#">Complete</a>
                            </g:elseif></td>
                     </tr>
                  </g:each>
                </tbody>
              </table>  
           </div>
        </div>
        <div class="pure-u-1-2">
          <div class="kumulus-container kumulus-scrollable-y kumulus-element-border">
             <h3>You are working on Y projects.</h3>
               <table class='pure-table pure-table-horizontal'>
                  <thead>
                     <tr>
                        <th>Project Name</th>
                        <th>Client</th>
                      </tr>
                   </thead>
                   <tbody>     
                  <g:each var="plist" in="${projectList}">
                    <tr>
                      <td>${plist.projectName}</td>
                      <td>${plist.client}</td>
                    </tr>
                  </g:each>
                </tbody>
              </table>  
          </div>
         </div>
     </div>
  </body>
</html>
