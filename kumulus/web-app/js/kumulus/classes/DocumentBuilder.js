$(document).ready(function(){ 

    $("#pages").sortable({
        connectWith: "ul",
        dropOnEmpty: true
    });

    $("#documents").sortable({
        connectWith: "ul",
        dropOnEmpty: true
    });

  
    $('.kumulus-filmstrip > ul > li > img').bind('mousedown', function() {
        preview($('#preview-img'), $(this).attr('viewId'));
        $("#barcode").val($(this).attr('barcode'))
        $("#containername").val($(this).attr('containerName'))
        $("#containertype").val($(this).attr('containerType'))
        $("#comment").val($(this).attr('containerComment'))
    });
   
    $("#pages, #documents").disableSelection();
});

function save() {
    var documents = $('#document-strip li');
    var taskIds = [];
    documents.each(function(i, li){
        taskIds.push($(li).attr('taskId'));
    });
    
    if(documents.length>0) {
        var data = {tasks: taskIds};
        $.ajax({
            url: url('document', 'merge', ''),
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(response) {
                $('#document-strip li').remove();    
                $('#preview-img').hide();
            }
        });
        
    }
}