
	
var puntos=0; //puntos para el vino.
	
	
	
	
	//cogemos todas las estrellas
	var estrellas = $(".estrella");
	
	estrellas.mouseover(function(){
			  eventos.quitar();
			  eventos.llenar(this);
		});
 
 estrellas.mouseout(function(){
			  eventos.quitar();
			  eventos.reiniciar();
		});
		

 estrellas.focus(function(){
			  eventos.quitar();
			  eventos.llenar(this);
		});
 
 estrellas.blur(function(){
			  eventos.quitar();
			  eventos.reiniciar();
		});

	//Cuando se hace click en una estrella
	estrellas.click(function(){

		
			puntos = (estrellas.index(this) * .5) + .5;
	    $("#nota").text(puntos);
	    $("#notainput").attr("value",puntos);//asignamos la puntuacion al input oculto para el formulario.
	      	
	      	
	      	
			
  	});
       
	var eventos = {
		// llenar hasta la posición del ratón.
		llenar: function(el) { 		
			var index = estrellas.index(el) + 1;
			estrellas
				.children('a').css('width', '100%').end()
				.slice(0,index).addClass('hover').end();
		},
		
		// quitar todas las estrellas.
		quitar: function() { 		
			estrellas
				.filter('.on').removeClass('on').end()
				.filter('.hover').removeClass('hover').end();
		},
		
		// reiniciar el sistema.
		reiniciar: function() { 	
			var unidad = parseInt(container.puntos);
			var decimales = container.puntos - unidad;
			var num_estrellas = (unidad * 2) + 1;
			
			if (decimales >= .5)
				num_estrellas += 1;
			
			estrellas.slice(0, num_estrellas).addClass('on').end();
		},
		
		
	};    
	
	