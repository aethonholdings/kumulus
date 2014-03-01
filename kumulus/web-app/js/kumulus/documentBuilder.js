$(document).ready(function(){ 
    var bool=true; 
    if(bool){
    $("#pages").sortable({
        connectWith: "div",
        dropOnEmpty: true       
    });
     $("#flimstrip-first-li").addClass("kumulus-hight");
     bool=false;
}
     if(bool=='false'){
         alert(bool);
     $("#flimstrip-first-li").removeClass("kumulus-hight");
}
    $("#document-strip").sortable({
        connectWith: "ul",
        dropOnEmpty: true
    });
    
    $("#document-strip li").hover(          
    function () {
        if(bool=='true'){
        $("#document-strip").addClass("connectedSortable"); 
          bool=false;    
          }
         if(bool=='false'){
            $("#flimstrip-first-li").removeClass("kumulus-hight");
        }
    });
    $("#document-strip span").hover(
    function () {
        $("#document-strip").removeClass("connectedSortable");
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
                if(response.done) documents.empty();
                $('#preview-img').hide();
            }
        });
    }
}