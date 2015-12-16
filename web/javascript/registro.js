$('#password').bind('keyup', function() { 
//calculamos como de segura es la contraseña.

var password = $('#password').attr("value");


 var longitud = password.length;
 $("#password").popover('show');
 var RegExPattern = /(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{5,12})$/;  
 
 if ($("#password").attr("value").match(RegExPattern)){
  
   $("#password").attr("data-content","Contraseña segura");   
    $("#password").css("background-color","green");
  
  
 
 }
 
 
 else
 {
  
    $("#password").attr("data-content","Contraseña no segura");   
   $("#password").css("background-color","red");
 }
 
 
 







} );







//inicializa los popovers
$(function () {
  $("#alias").popover({offset: 10});
  $("#nombre").popover({offset: 10});
  $("#apellidos").popover({offset: 10});
  $("#email").popover({offset: 10});
  $("#password").popover({offset: 10});
  
}); 

$("#nombre").focus(function(){
  
  $("#alias").popover('hide');
  $("#apellidos").popover('hide');
  $("#email").popover('hide');
  $("#password").popover('hide');
  

 $("#nombre").popover('show');

});

$("#alias").focus(function(){
  $("#alias").popover('show');
  $("#apellidos").popover('hide');
  $("#email").popover('hide');
  $("#password").popover('hide');
  $("#divalias").removeClass("error");

 $("#nombre").popover('hide');

});

$("#apellidos").focus(function(){
  $("#alias").popover('hide');
  $("#apellidos").popover('show');
  $("#email").popover('hide');
  $("#password").popover('hide');
  

 $("#nombre").popover('hide');

});

$("#password").focus(function(){
  $("#alias").popover('hide');
  $("#apellidos").popover('hide');
  $("#email").popover('hide');
  $("#password").popover('show');
  $("#divpassword").removeClass("error");
  $("#divpassword").css("background-color:red");
 $("#nombre").popover('hide');

});

$("#email").focus(function(){
  $("#alias").popover('hide');
  $("#apellidos").popover('hide');
  $("#email").popover('show');
  $("#password").popover('hide');
  $("#divemail").removeClass("error");
   $("#email").attr("data-content","Introduce tu email.Ejemplo: tunombre@mail.com");
 $("#nombre").popover('hide');

});



//chequeamos que todo sera correcto para iniciar el registro.
$("#btnregistro").click(function() {      
    
     var campo3 = $("#email").attr("value");     
     
       
     if (campo3=='')
     {
        $("#divemail").addClass("error");
     
     }
     
     else
     {
     
       
     
       if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($("#email").val()))
          {
              $("#formulario").submit();  
          }
          else
          {
          $("#email").attr("data-content","Email no valido.Ejemplo: tunombre@mail.com");
           $("#email").popover('show');
            $("#divemail").addClass("error");
              
          }
      
       
     
     }
     
      
          
});












