
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


//control de la ventana para añadir a un amigo.
$("a#aceptarsolicitud").click(function(){
    var idsolicitante=$(this).parent().prev().prev().attr("id");
    var nombresolicitante=$(this).parent().prev().prev().text();
    var boton =  $(this).parent().parent().parent();
    $.ajax({
        type:"POST",
        url:"aceptarsolicitud.htm",
        data:{
            idsol:idsolicitante
        },
        success:function(msg){
            //podemos hacer algo para actualizar la informacion.
            //$("h4#puntuacion").text("actualizado");
            if (msg=='ok'){
                //$("ul#usuarios").append("<li class=\"imagen\"><img src=\"images/avatar.png\" title=\""+nombresolicitante+"\" width=\"40px\" height=\"40px\"/></li>").fadeIn("slow");
                boton.remove();
                alert(nombresolicitante+" ha empezado a seguirte");
            }
            else
            {
                alert("No se ha podido añadir un nuevo amigo en estos momentos");
            }
        }
    });
});

$("a#ignorarsolicitud").click(function(){
    var idsolicitante=$(this).parent().prev().prev().attr("id");
    var boton =  $(this).parent().parent().parent();
    $.ajax({
        type:"POST",
        url:"ignorarsolicitud.htm",
        data:{
            idsol:idsolicitante
        },
        success:function(msg){
            //podemos hacer algo para actualizar la informacion.
            //$("h4#puntuacion").text("actualizado");
            if (msg=='ok'){
                boton.remove();
            }
            else
            {
                alert("OH OH !!No se ha podido ignorar como amigo en este momento");
            }
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
$(function () {
    $(".solicitud").popover({
        offset: 10,
        placement:'below'
    });
});
