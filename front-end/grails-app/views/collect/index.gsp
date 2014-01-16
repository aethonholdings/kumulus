<html>
    <head>
        <title>Prepare scans | Kumulus</title>
    </head>
    <body>
        <p>Prepare documents for import</p>
        <p>Select a project</p>
        <table class="pure-table" width="100%">
            <thead>
                <tr>
                    <th>Id</a>
                    <th>Project</th>
                    <th>Client</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${projects}" var="projectInstance">
                    <tr>
                        <td>${projectInstance?.id}</td>
                        <td><g:link controller="collect" action="workflow" id="${projectInstance?.id}">${projectInstance?.projectName}</g:link></td>
                        <td>${projectInstance?.clientLDAPId}</td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </body>
</html>