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




$("#btnactualizar").click(function() {
      
     var campo1 = $("#alias").attr("value");     
    
    alert("dentro");
     
     
      
      if (campo1=='')
      {
        
    
      $("#divalias").addClass("error");
        
      
      }
      
     else
     {
      
        $("#formulario").submit();  
     
     }
     
      
          
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


