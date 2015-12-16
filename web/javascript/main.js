var searchBoxes = $(".text");
var searchBox1= $("#search1");
var searchBox2= $("#search2");
var searchBox3= $("#search3");
var searchBox4= $("#search4");
var searchBox5= $("#search5");

var defaultText = "eSTO ES UNA PRUEBA";

    //efectos en el evento focus (foto) para ambas cajas de busqueda  
   
 

     
      //Mostramos / ocultamos el texto por defecto si es necesario  

    searchBox1.focus(function(){  
        $(this).attr("value","");  
    });  
    
     searchBox2.focus(function(){  
        $(this).attr("value","");  
    });  
 
    searchBox3.focus(function(){  
        $(this).attr("value","");  
    });      

    
	
    searchBox5.focus(function(){  
        $(this).attr("value","");  
    });  

$(function(){
   $('#search4').dPassword();
});

   
    $("#boton").click(function() {
       
	if (searchBox1.attr("value")=="" || searchBox2.attr("value")==""){

		searchBox1.attr("value","No hemos validado");
		
	}
	else
	{	

		searchBox1.attr("value","Hemos validado");
		$("#formulario").submit();
	}


});



