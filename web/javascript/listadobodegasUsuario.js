//codigo para cerrar los mensajes

//codigo para cerrar los mensajes de informacion de manera animada.
$("a#cerraralert").click(function(){
    
    $(this).parent().fadeOut(300);
    
    
});





$("a.edit").click(function(){

if ($(this).parent().parent().children().children().next().children().is(":hidden")){
    

$(this).parent().parent().children().children().next().children().css("display","block");
$(this).parent().parent().children().children().next().children().attr("value",$(this).parent().parent().children().children().text());
$(this).parent().parent().children().children().next().children().focus();//ponemos el foco sobre el input donde vamos a editar.
return false;
}
else
{

$(this).parent().parent().children().children().next().children().hide();

}

});

$('#inputnombre ').focus(function(){

  $(this).css("border-color","#CCCCCC");


});


$('#inputnombre ').bind('keypress', function(e) {

  var code = (e.keyCode ? e.keyCode : e.which);
 if(code == 13) { //Enter keycode
   
   var nombrelista = $(this).attr("value");
   
   if (nombrelista=='')
   {
       $(this).css("border-color","red");
       e.preventDefault();

   }
   else
   {
     $("cambiarnombre").submit();
   
   }
 }







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


$("#btnnuevabodega").click(function(e) {
      
     var campo1 = $("#input1").attr("value");     
    
    
     if (campo1=='')
      {
        
     
      $("#div1").addClass("error");
      return false;
      
      }
     
     else
     {
      
        $("#nuevabodega").submit();  
     
     }
     
      
          
});


$("#input1").focus(function(){

    $("#div1").removeClass("error");



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



