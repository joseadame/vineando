

$(function () {
  $("#emaillogin").popover({offset: 10});
  
  
}); 


$("#emaillogin").focus(function(){

$("#divmaillogin").removeClass("error");


});


$("#btnlogin").click(function() {      
     var campo1 = $("#emaillogin").attr("value");     
     var campo2 = $("#passwordlogin").attr("value");
     
     if (campo1=='' || campo2=='' )
     {      
      if (campo1=='')
      {
      		$("#divmaillogin").addClass("error");
      }
      if (campo2=='')
      {
        $("#divpasswordlogin").addClass("error");
        
     }
      
     
     }
     else
     {
         if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($("#emaillogin").val()))
          {
              $("#hacerlogin").submit();  
          }
          else
          {
           $("#emaillogin").popover('show');
            $("#divmaillogin").addClass("error");
              
          }
     
     }
     
      
          
});