/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var selectedDate;
$(document).ready(function(){

  $("#divCalendar").datepicker({
       minDate: new Date(),
                onSelect: function (selectedDate) { 
                   selectedDate=selectedDate.toString(); 
                } 
            }); 
  getNodeList();  
});



function getNodeList(){

                 
        $.ajax({
            url: url('node', 'pickupNode', ''),
            type: 'POST',
//            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(response) {
                if(response.done) $('#documents').empty();
                $('#preview-img').hide();
                alert("<<<<"+ selectedDate)
            }
        });
           
}


