//Envio de la solicitud de amistad mediante ajax.

//Num pagina de notificacion
var numPage=2;





$(document).ready(function(){
    $contentLoadTriggered = false;
    $(window).scroll(function(){                
        if($(window).scrollTop() >= ($(document).height() - $(window).height()) && $contentLoadTriggered == false)
        {
            $contentLoadTriggered = true;
            $.ajax({
                        type:"POST",
                        url:"paginarNotificacion.htm",
                        data:{nPage:numPage},
                        success:function(msg){     
                            if (msg)
                                {
                            $("#listanotificaciones").append(msg);
                            $contentLoadTriggered = false;
                            numPage++;
                                }
                                else
                                    {
                                          $("#listanotificaciones").append('<p>No existen mas notificaciones</p>');
                                    }
                            
                        }
                    });
        }
    });
});








$("#botonaddamigo").click(function(){
    
    $("#ventanaamigo").show();
    
    
});

$("#enviaramistad").click(function(){
    var id = $("#ocultoidusuario").attr("value");
    var mensajeaux = $("#mensaje").attr("value");
    
    $.ajax({
        
        type:"POST",
        url:"addamigo.htm",
        data:{idamigo:id,mensaje:mensajeaux},
        success:function(msg){
              //podemos hacer algo para actualizar la informacion.
                   
                   //$("h4#puntuacion").text("actualizado");
                   if (msg=='ok'){
                    $("#botonaddamigo").hide();
                    $("#ventanaamigo").hide();
                    
                   }
                   else
                   {
                       
                       $("#botonaddamigo").attr("value","solicitud enviada anteriormente");
                       
                       
                       

                   }
               
                   
        }
        
 });
    
    
    
    
});








$(function() {
		$("#ventanaamigo").draggable();
		
	});

$("#menu").mouseover(function(){

  
  
   
 
  
   $("#lista").css("visibility","visible");
  
});

$("#lista").mouseover(function(){

  
  
   
 
  
   $("#lista").css("visibility","visible");
  
});

$("#lista").mouseout(function(){

  
  
   
 
  
   $("#lista").css("visibility","hidden");
  
});


$("#menu").mouseout(function(){

  
  
   
 
  
   $("#lista").css("visibility","hidden");
  
});

$("#alias").focus(function(){


  $("#divalias").removeClass("error");



});


$("#password").focus(function(){


  $("#divpassword").removeClass("error");



});

$("#cancelar").click(function() {
      
     $("#ventanaamigo").css("display", "none");
      $("#ventanaamigo").css("top", "200");
     
      
          
});







//para el menu de las bodegas
 
$("#menubodega").mouseover(function(){

  
  
   
 
  
   $("#listabodega").css("visibility","visible");
  
});

$("#listabodega").mouseover(function(){

  
  
   
 
  
   $("#listabodega").css("visibility","visible");
  
});

$("#listabodega").mouseout(function(){

  
  
   
 
  
   $("#listabodega").css("visibility","hidden");
  
});


$("#menubodega").mouseout(function(){

  
  
   
 
  
   $("#lista").css("visibility","hidden");
  
});





