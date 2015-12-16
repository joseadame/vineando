//***********************************
////ATRIBUTOS GLOBALES
//***********************************

//vector para almacenar las variedades de uva.
var ids = new Array();      
var seleccionados = new Array();     
var encontrado=false;
//indice para llevar el control sobre el vector.
var indice= 0;

//***********************************
////MENU Y DESPLEGABLES
//***********************************


$("#input1").focus(function(){
    $("#div1").removeClass("error");
});


/*$("#input1").blur(function(){
    if ($("#input1").attr("value")=='')
    {
            $("#div1").addClass("error");
     
            var newdiv = document.createElement('div');
            newdiv.className = "alert-message warning";
            newdiv.id = "divError";
            
            var elementa= document.createElement('a');
            elementa.className="close";
            elementa.textContent="x";
            elementa.setAttribute('href', '#');
            
            var elementp= document.createElement('p');
            elementp.appendChild(document.createTextNode("Error mensaje de rror"));
            
            newdiv.appendChild(elementa);
            newdiv.appendChild(elementp);
      
        $("#formulario > fieldset").prepend(newdiv);
            
          
}
});*/
                                 

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

$("#boton1").click(function() {
    var campo1 = $("#input1").attr("value");     
    var campo2 = $("#select1").attr("value"); 
    if (campo1=='' || campo2==''  )
    {
        if (campo1=='')
        {
            $("#div1").addClass("error");
        }
        if (campo2=='')
        {
            $('#div2').css("background-color","orange");
            $('#div1').css({
                "background": "none repeat scroll 0 0 #FAE5E3",
                "border-radius": "4px 4px 4px 4px",
                "margin": "-10px 0 10px",
                "padding": "10px 0"
            });
        }
    }
    else
    {
        $("#formulario").submit();  
    }
});
$("#tags").click(function() {
    //limpiamos el vector
    indice=0;
    vector = null;
    vector = new Array();
    seleccionados = null;
    seleccionados = new Array();
    $("#variedades").css("display","block");
});
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
    $("#variedades").css("display","none");
});
$("#enviar").click(function() {
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
    $("#inputVariedad").attr("value",salida);
    $("#inputVariedad").attr("disable","true");
    //reiniciamos el color de los tags.
    /* for(var i=0;i<ids.length;i++){
      var cadena="#"+ids[i];
      $(cadena).css("color","#333333");
      
      }*/
    $(".pulsar").css("color","#333333");
    $("#variedades").css("display","none");
});

$("#inputVariedad").focus(function(){
    //limpiamos el vector
    indice=0;
    vector = null;
    vector = new Array();
    seleccionados = null;
    seleccionados = new Array();
    $("#variedades").css("display","block");
});