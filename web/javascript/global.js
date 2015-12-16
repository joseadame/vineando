
//vector para almacenar las variedades de uva.
var ids = new Array();
var seleccionados = new Array();
var encontrado=false;
//indice para llevar el control sobre el vector.
var indice= 0;
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
                        url:"paginarGlobal.htm",
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






$("#input1").focus(function(){
    $("#div1").removeClass("error");
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

/*
 *para mostrar los popovers de los mensajes de amistad enviados por el usuario que envia la solicitud
*/

