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

$("#alias").focus(function(){
    $("#divalias").removeClass("error");
});

$("#password").focus(function(){
    $("#divpassword").removeClass("error");
});

$("#btnCambiarAvatar").click(function() { 
   
      $("#draggable").css("display", "inherit");   
});

$("#enviar").click(function(){
    if (ValidarExtension())
    {
        $("#fileUploadForm").submit();
    }
});

$("#cancelar").click(function() {     
     $("#draggable").css("display", "none");
});

$("#btnactualizar").click(function() {
    var campo1 = $("#alias").attr("value");
    if (campo1=='')
    {
        $("#divalias").addClass("error");
    }
    else
    {
        $("#formulario").submit();
    }
});

$("#btnpassword").click(function(){
    var password1=$("#newpassword").attr("value");
    var password2=$("#newpassword2").attr("value");
    if (password1==password2){
        $("#formcambiarpassword").submit();
    }
    else
    {
        $("#divpassword1").addClass("error");
        $("#divpassword2").addClass("error");
    }
});


///Funcion que valida la extensión del fichero a utilizar como avatar. 
function ValidarExtension()
{
    var image =$("#file").val();
    if(image!=''){
        var checkimg = image.toLowerCase();
        if (!checkimg.match(/(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)){
            $("#alertTipoNoValido").show();
            $("#file").focus();
            return false;
        }
    }
    return true;
}
