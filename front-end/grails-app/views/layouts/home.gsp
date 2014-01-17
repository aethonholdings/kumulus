<!DOCTYPE html>
<html lang='en'>
  <head>
    <link rel='stylesheet' type='text/css' href='${resource(dir: 'css/pure', file: 'pure-min.css')}'/>
    <link rel='stylesheet' type='text/css' href='${resource(dir: 'css/pure', file: 'side-menu.css')}'/>
    <link rel='stylesheet' type='text/css' href='${resource(dir: 'css/kumulus', file: 'main.css')}'/>
    <g:javascript library='jquery' />
    <r:layoutResources />             
    <g:javascript src='jquery/ui/jquery-ui.js'/>
    <g:javascript src='jquery/cookie/jquery.cookie.js'/>
    <g:javascript src='pure/ui.js'/>   
    <g:layoutHead/>
  </head>
  <body>
    <div id='layout'>
      <div id='menu'>
        <div class='pure-menu pure-menu-open'>
          <a class='pure-menu-heading' href=''>kumulus</a>
          <ul>
            <li><g:link controller='home' action='index'>Home</g:link></li>
            <li><a href=''>Order materials</a></li>
            <li><g:link controller='project' action='index'>Manage projects</g:link></li>
            <li><g:link controller='collect' action='index'>Collect documents</g:link></li>
            <li><a href=''>Import documents</a></li>
            <li><g:link controller='review' action='index'>Review data</g:link></li>
            <li><g:link controller='extract' action='index'>Download ledger</g:link></li>
            <li><a href=''>Schedule pickup</a></li>
            <li><g:link controller='access' action='index'>Access archive</g:link></li>
            <li class='menu-item-divided'><a href=''>Manage account</a></li>
          </ul>
        </div>
      </div>
      <div class='content'>
        <div id='kumulus-header'>
          <div class='pure-g'>
            <div class='pure-u-1-2'></div>
            <div class='pure-u-1-2'>
              <div id='kumulus-session-management'>
                Welcome <b><sec:loggedInUserInfo field='username'/></b> |
                <g:link controller='logout'>Logout</g:link>
              </div>
            </div>
          </div>
        </div>
        <div id='kumulus-body'>
          <g:layoutBody/>
        </div>
      </div>
    </div>
  </body>
</html>
