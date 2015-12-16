
$("#cerrar_cookie").click(function() {
		$("#cookiebar").hide();
                
                
	});



$("#login").click(function() {
		$('#basic-modal-content').modal();

		return false;
	});



//para abrir las ventanas modales.
$("#login").click(function(){

  $("#loginform").css("display","block");
  $("#registroform").css("display","none");
  $("form div").removeClass("error");





});


$("#registro").click(function(){

  $('#basic-modal-content2').modal();

		return false;






});

//-------------------------------------

$("#cerrar").click(function(){

  
  $("div.modal").css("display","none");






});


$("#cerrarregistro").click(function(){

  $("#registroform").css("display","none");
 




});

$("#alias").focus(function(){

  $("#alias").css("border-color","white");


});


$("#nombre").focus(function(){

  $("#nombre").css("border-color","white");


});


$("#apellidos").focus(function(){

  $("#apellidos").css("border-color","white");


});


$("#email").focus(function(){

  $("#email").css("border-color","white");


});

$("#password").focus(function(){

  $("#password").css("border-color","white");


});









$("#btnregistro").click(function() {
      
     var alias = $("#alias").attr("value");     
     var nombre = $("#nombre").attr("value"); 
     var apellidos = $("#apellidos").attr("value");
     var email = $("#email").attr("value");
     var password = $("#password").attr("value");    
     
      if (alias=='' || nombre=='' || apellidos=='' || email=='' || password=='' )
     {
      
      if (alias=='')
      {
        $('#alias').css("border-color","red");
      
      }
      if (nombre=='')
      {
      $('#nombre').css("border-color","red");
      
      
      }
      if (apellidos=='')
      {
       $('#apellidos').css("border-color","red");
      
      
      }
      if (email=='')
      {
        $('#email').css("border-color","red");
      
      
      }
      if (password=='')
      {
      $('#password').css("border-color","red");
      
      
      }
      
      
      
      
      
     }
     else
     {
      
        $("#formulario").submit();  
     
     }
     
      
          
});

$("#btnlogin").click(function() {
      
     var email = $("#emaillogin").attr("value");     
     var password = $("#passwordlogin").attr("value"); 
      
     
      if (email=='' || password=='' )
     {
      
      if (email=='')
      {
        $('#emaillogin').css("border-color","red");
      
      }
      if (password=='')
      {
          $('#passwordlogin').css("border-color","red");
      
      
      }
      
      
      
      
      
      
     }
     else
     {
      
        $("#hacerlogin").submit();  
     
     }
     
      
          
});


$("#emaillogin").focus(function(){


  $('#emaillogin').css("border-color","white")


});  


$("#passwordlogin").focus(function(){


  $('#passwordlogin').css("border-color","white");


});  




//para el carrousel de ultimos vinos añadidos.
stepcarousel.setup({
	galleryid: 'carousel', //id of carousel DIV
	beltclass: 'belt', //class of inner "belt" DIV containing all the panel DIVs
	panelclass: 'panel', //class of panel DIVs each holding content
	autostep: {enable:true, moveby:1, pause:3000},
	panelbehavior: {speed:500, wraparound:true, persist:true},
	statusvars: ['statusA', 'statusB', 'statusC'], //register 3 variables that contain current panel (start), current panel (last), and total panels
	contenttype: ['external'] //content setting ['inline'] or ['external', 'path_to_external_file']
})

