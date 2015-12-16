var addamigosopen=0; //flag para ver si hemos abierto el 
//vector para almacenar las variedades de uva.
      var ids = new Array();      
      var seleccionados = new Array();      
      var encontrado=false;
      var pais='';
      var zona='';
      var barcode='';
      var uvas='';
//indice para llevar el control sobre el ve$ctor.
var indice= 0;
var porcentaje;

//codigo para cerrar los mensajes de informacion de manera animada.
$("a#xcerrar").click(function(){
    
    $("div#mensajeinfo").fadeOut(300);
    
    
});

//codigo para cerrar los mensajes de informacion de manera animada.
$("a#cerraralert").click(function(){
    
    $(this).parent().fadeOut(300);
    
    
});

//codigo para los autocomplete de ciudades y establecimientos

$(function(){

 
var cadenaaux = $("#inputciudad").attr("value");

$("#inputciudad").autocomplete({minLength:3,
			source: "buscarciudad.htm"
                       
		});

});





//añadir vino a la wishlist


$("#whishlist").click(function(){
   
    var id = $("#ocultoidvino").attr("value");
    
   $.ajax({
        type:"POST",
        url:"wishlist.htm",
        data:{idvino:id},
        success:function(msg){
              //podemos hacer algo para actualizar la informacion.
                   
                   //$("h4#puntuacion").text("actualizado");
                   if (msg=='ok'){
                   alert("Has añadido el vino a tu wishlist");
                   }
                   else
                   {
                       
                    alert("OH OH !!El vino ya lo tenias en tu wishlist");
                   }
        }
        
 });
    
   
    
    
    
    
});






//puntuar


$("div.perfil").mouseover(function(){
    
    $(this).popover('hide');
    
    
    
});

$("img#megusta").click(function(){
    
    
   var idcomentario=$(this).prev().prev().parent().parent().prev().parent().parent().attr("id");
   var h4 =$(this).next().parent().next().next().children();
   var sumarvoto = $(this).next().parent().next().next().next().children();
   
   $.ajax({
        type:"POST",
        url:"puntuarComentario.htm",
        data:{idcomentario:idcomentario,accion:"p"},
        success:function(msg){
              //podemos hacer algo para actualizar la informacion.
                   
                   //$("h4#puntuacion").text("actualizado");
                   if (msg=='aceptado'){
                   h4.text("Has sumado 1 punto a este comentario");
                   var votos = parseInt(sumarvoto.text());
                   votos++;
                   sumarvoto.text(votos);
                   }
                   else
                   {
                     
                     h4.text(msg);
                   }
        }
        
 });
    
    
    
});



$("img#nomegusta").click(function(){
    
    
   var idcomentario=$(this).prev().prev().prev().parent().parent().prev().parent().parent().attr("id");
   var h4 =$(this).parent().next().next().children();
   var votonegativo = $(this).parent().next().next().next().next().children();
   
   $.ajax({
        type:"POST",
        url:"puntuarComentario.htm",
        data:{idcomentario:idcomentario,accion:"n"},
        success:function(msg){
              //podemos hacer algo para actualizar la informacion.
                   
                   //$("h4#puntuacion").text("actualizado");
                   if (msg=='aceptado'){
                   h4.text("Has sumado 1 punto negativo a este comentario");
                   var votos = parseInt(sumarvoto.text());
                   votos++;
                   sumarvoto.text(votos);
                   }
                   else
                   {
                       
                     h4.text(msg);
                   }
        }
        
 });
    
    
    
});







//enviar el comentario.

$(function () {
  $("#textocomentario").popover({offset: 10});
 // $("div.perfil").popover({offset: 10});
 
  
  
  
}); 

$("#btnenviarcomentario").click(function(){
    
    var textocomentario=$("#textocomentario").attr("value");
    
    if (textocomentario=='')
    {
         $("#textocomentario").popover('show');
         return false;
    }
   
    
    else
    {
        
         $("#enviarcomentario").submit();
         return false;
    }
    
});




//Control para el AJAX de actualizar la informacion de un vino
$("input.xlarge").keypress(function(e){
    
    if (e.keyCode=='13'){
       e.preventDefault();
    }
});





$("#guardar").click(function(){
    
  pais=$("#pais").text();
  zona=$("#zona").text();
  barcode=$("#barcode").text();
  uvas=$("#eligeuva").text();
    
    
    
  //  var info= $(this).parent().prev().attr("value");
    var idvino = $("#ocultoidvino").attr("value");
   // var dato= $(this).parent().prev().attr("id");
    var noactualizar=0; //esta variable nos sirve para decidir si actualizamos el progreso del perfil.
    $.ajax({
        type:"POST",
        url:"actuVino.htm",
        data:{uvas:uvas,barcode:barcode,zona:zona,pais:pais,idvino:idvino},
        success:function(msg){
               // alert("Data Saved: "+ msg );
               
                  $("#reportar").show();
                  $("#editar").show();
                  $("#guardar").hide();
                  $("#cancelar").hide();
                  $("#pais").removeClass("editable");
                  $("#zona").removeClass("editable");
                  $("#eligeuva").removeClass("editable");
                  $("#barcode").removeClass("editable");
                  $("#pais").attr("contenteditable","false");
                  $("#zona").attr("contenteditable","false");
                  $("#eligeuva").attr("contenteditable","false");
                  $("#barcode").attr("contenteditable","false");
                  $("#pais").text(pais);
                  $("#zona").text(zona);
                  $("#barcode").text(barcode);
                  $("#eligeuva").text(uvas);
               
            
                if (porcentaje!=100){
                porcentaje=parseInt(porcentaje)+20;
                
                }
                    
              
              
              $( "#progressbar" ).progressbar({
            value: parseInt(porcentaje)
        });
        $("#completado").text("");
         $("#completado").text(porcentaje+"% perfil completo");
        }
 });
    
});





//----------------------------------------------------------------

////////////////////////////////////////////////////////
//para el control de elegir las variedades de uva.
///////////////////////////////////////////////////////
$(".pulsar").click(function() {
    
      var cadena = $(this).attr("href");
      
      if (cadena!='#'){
      
        
        
        for(var b=0;b<seleccionados.length;b++){
        if(cadena==seleccionados[b]){
        
        encontrado=true;
        var posicion=b;
        }
        
        }
        if (encontrado==true){
        $(this).css("color","#333333");
        seleccionados[posicion]=null;
        encontrado=false;
        
        }
        else
        {
         $(this).css("color","brown");
         seleccionados[indice]=cadena;
         ids[indice]=$(this).attr("id"); //guardamos los ids de los links modificados
         encontrado=false; 
        }
      
        indice=indice+1;
      }
      
      
      return false;
      
    
     
      
          
});

$("#cerrar").click(function() {
      
     $("#ventana_variedades").css("display","none");
     
      
          
});


$("#add_variedad").click(function() {
      //limpiamos el vector de posibles repetidos.
   /*   for(i=0;i<vector.length;i++){
      var aux=vector[i];
      for(var j=i+1;j<vector.length;j++)
      {
        if (vector[j]==aux){
           vector[j]=null;
        
        }
        
      
      }
      
      
      
      }*/
      
      var salida = new Array();
      var indice2=0;
      for (i=0;i<seleccionados.length;i++){
      
      var aux2=seleccionados[i];
      if (aux2!=null)
      {
        salida[indice2]=aux2;
        indice2++;
      
      }
      
      
      }
      $("#variedades").attr("value",salida);
      $("#variedades").attr("disable","true");
      //reiniciamos el color de los tags.
     /* for(var i=0;i<ids.length;i++){
      var cadena="#"+ids[i];
      $(cadena).css("color","#333333");
      
      }*/
      
      $(".pulsar").css("color","#333333");
     
      $("#ventana_variedades").css("display","none");
      
      
          
});

$("input#variedades").focus(function(){
  //limpiamos el vector
     indice=0;    
     vector = null;
     vector = new Array();
     seleccionados = null;
     seleccionados = new Array();
     $("#ventana_variedades").css("display","block");
});

//controla toda la funcionalidad de añadir informacion a un vino.

$(" a img#edit").click(function(){
if ($(this).parent().next().is(":hidden")){
    

$(this).parent().next().show();
$(this).parent().next().next().children().attr("value","");
 }
 else
 {
  $(this).parent().next().hide();
 
 }



});


$("#botonreportar").click(function() {
   
      
     $("#reportarerror").show(); 
     
      
          
});

$("#enviarerror").click(function() {
      
     $("#enviarreporte").submit();
     
      
          
});

$("#cancelarerror").click(function() {
      
     $("#reportarerror").hide();
     
      
          
});



////////////////////////////////////////////////////////////////
//
//
//
//
//
//controles para le edicion de informacion del vino
//se muestra un edit para añadir informacion si no estuviera rellena.

$(function() {
		$("#draggable").draggable();
		$("#ventanacata").draggable();
                $("#reportarerror").draggable();
		
	});

$(function() {
		$( "#tabs" ).tabs();
	});
	
$("#subirfoto").click(function(){

  $("#uploadfoto").modal();


});

$("#btn1").click(function(){
   
    $("#updateformulario").submit();
    
    
});



$("h4.edit").click(function(){
    
    $(this).hide();
    $(this).next().css("display","block");
    $("#divactualizar").show();
   
    
    
    
    
});


$(document).ready(function() {
$("#gallery a").lightBox({fixedNavigation:true});
});




$(window).resize(function(){
 
          // aquí le pasamos la clase o id de nuestro div a centrar (en este caso "caja")
          $('#ventanabodega').css({
               position:'absolute',
               left: ($(document).width() - $('#ventanabodega').outerWidth())/2,
               top: ($(document).height() - $('#ventanabodega').outerHeight())/2
          });
       
    });

// Ejecutamos la función
$(window).resize();
 

$("#enviar").click(function(){

  $("#vinoabodega").submit();


});



$(document).ready(function() {
$("#gallery a").lightBox({fixedNavigation:true});
});
	

$(window).resize(function(){
 
          // aquí le pasamos la clase o id de nuestro div a centrar (en este caso "caja")
          $('#ventanabodega').css({
               position:'absolute',
               left: ($(document).width() - $('#ventanabodega').outerWidth())/2,
               top: ($(document).height() - $('#ventanabodega').outerHeight())/2
          });
       
    });

// Ejecutamos la función
$(window).resize();
 







$("p  a").click(function(){

 
      if ($(this).parent().next().is(":hidden")) 
      {
               $(this).parent().next().slideDown("slow");
       } else 
       {
               $(this).parent().next().hide();
       }



//$(this).parent().next().css("display","block");



return false;

});



$("div .respuesta a").click(function(){

  var texto =$(this).parent().prev().attr("value");
  alert(texto);
 $(this).parent().parent().prev().prev().append('<blockquote><p>'+texto+'</p><small><a href="perfilAmigo.html">oscarcasillas</a></small></blockquote>');
      






//$(this).parent().next().css("display","block");



return false;

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

$("#enviar").click(function() {
      
     $("#vinoabodega").submit();
     
      
          
});


$("#boton1").click(function() {
      
     $("#draggable").css("display", "inherit"); 
     
      
          
});


/*$("#cata").click(function() {
      
     $("#ventanacata").css("display","block");
     $("textarea").attr("value","");
     $("#addamigos").attr("value","");
     $("#nota").text("0");
     $("#addamigos").css("display","none");
     
      $("#ventanacata").css("top", "200");
          
});*/

$("#nocatar").click(function() {
      
     $("#ventanacata").css("display", "none");
     
     
      
          
});




$("#cancelarlista").click(function() {     
     $("#draggable").css("display", "none");
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

$("#iconamigos").click(function(){

  if (addamigosopen==0)
  {
  $("#addamigos").css("display","block");
  addamigosopen=1; //hemos abierto la ventana
  }
  else
  {
   $("#addamigos").css("display","none"); //si estaba abierto lo cerramos.
   $("#addamigos").attr("value","");//eliminamos lo que tenga dentro.
   addamigosopen=0;
  }
  



});



//control para el AUTOCOMPLETE de amigos
$(function() {
   // $( "#tabs" ).tabs();

    //Este bloque de código establece el porcentaje de progreso del perfil.
    //El valor del porcentaje esta en un input oculto, porcentajeCompletado, dentro de la pagina infoVino.jsp.
    var _porcentajeCompletado = $("#porcentajeCompletado").attr("value");
    porcentaje=_porcentajeCompletado;
    if (_porcentajeCompletado!=null){
        $( "#progressbar" ).progressbar({
            value: parseInt(_porcentajeCompletado)
        });
    }
    
    
       

var availableTags = [
"joseadame",
"oscarcasillas",
"leandrotena",
"raul",
"usuario33",
"josecordero",
"fran345",
"josejose"
];
function split( val ) {
return val.split( /,\s*/ );
}
function extractLast( term ) {
return split( term ).pop();
}
$( "#addamigos" )
// don't navigate away from the field on tab when selecting an item
.bind( "keydown", function( event ) {
if ( event.keyCode === $.ui.keyCode.TAB &&
$( this ).data( "autocomplete" ).menu.active ) {
event.preventDefault();
}
})
.autocomplete({
minLength: 0,
source: function( request, response ) {
// delegate back to autocomplete, but extract the last term
response( $.ui.autocomplete.filter(
availableTags, extractLast( request.term ) ) );
},
focus: function() {
// prevent value inserted on focus
return false;
},
select: function( event, ui ) {
var terms = split( this.value );
// remove the current input
terms.pop();
// add the selected item
terms.push( ui.item.value );
// add placeholder to get the comma-and-space at the end
terms.push( "" );
this.value = terms.join( ", " );
return false;
}
});
}); 


$('#addamigos').keypress(function(e) {
  if (e.keyCode == '13') {
     e.preventDefault();
     
   }
});




$("#editar").click(function(){
    
  pais=$("#pais").text();
  zona=$("#zona").text();
  barcode=$("#barcode").text();
  uvas=$("#eligeuva").text();
  $("#reportar").hide();
  $("#editar").hide();
  $("#guardar").show();
  $("#cancelar").show();
  
  if(pais=='')
   {
       $("#pais").addClass("editable");
       $("#pais").attr("contenteditable","true");
   }
  if (zona=='')
      {
          
          $("#zona").addClass("editable");
          $("#zona").attr("contenteditable","true");
      }
  

  
  if (barcode=='')
      {
          $("#barcode").addClass("editable");
          $("#barcode").attr("contenteditable","true");
      }
  
  if (uvas=='')
      {
         $("#eligeuva").addClass("editable");
         $("#eligeuva").attr("contenteditable","true");
      }
  
 


 


});



$("#cancelar").click(function(){
    
  
  $("#reportar").show();
  $("#editar").show();
  $("#guardar").hide();
  $("#cancelar").hide();
  $("#pais").removeClass("editable");
  $("#zona").removeClass("editable");
  $("#eligeuva").removeClass("editable");
  $("#barcode").removeClass("editable");
  $("#pais").attr("contenteditable","false");
  $("#zona").attr("contenteditable","false");
  $("#eligeuva").attr("contenteditable","false");
  $("#barcode").attr("contenteditable","false");
  $("#pais").text(pais);
  $("#zona").text(zona);
  $("#barcode").text(barcode);
  $("#eligeuva").text(uvas);


});