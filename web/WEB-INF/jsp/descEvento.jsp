<!DOCTYPE html

<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<head>
    <meta charset="utf-8">
    <title>Vineando -- Informacion del evento</title>
    <meta name="description" content="Evento">
    <meta name="author" content="">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
 


    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le styles -->
    
    <link href="./css/docs.css" rel="stylesheet">
    <link href="./css/prettify.css" rel="stylesheet">
    <link href="./css/jquery-ui-1.8.16.custom.css" rel="stylesheet">
    <!-- Le javascript -->
    
    
    
    
    <link rel="stylesheet" href="./css/bootstrap-1.0.0.css" media="all">
    <link rel="stylesheet" href="./css/Style.css" media="all">
    <style type="text/css">
   
      #map-canvas { 
	  height: 100%;
	  margin: 0px;
	  padding: 0px

	  
	  
	  }
    </style>

  
</head>
  <body>
  
 <div style="z-index: 5;" class="topbar-wrapper">
            <div class="topbar">
                <div class="fill">
                    <div class="container">
                        <h3><a href="#">Vineando</a></h3>
                        <ul>
                            <li class="active"><a href="perfilUsuario.htm">Home</a></li>
                            <li class="menu">
                                <a id="menubodega" href="#">Tus listas</a>
                                <ul id="listabodega" class="menu-dropdown">
                                    <c:forEach var="bodega" items="${listabodegas}">
                                        <li><a href="busquedaBodega.htm?idbodega=${bodega.idbodega}&idusuario=${UsuarioSesion.id}">${bodega.descripcion}</a></li>
                                    </c:forEach>
                                    <li class="divider"></li>
                                    <li><a href="nuevaBodega.htm">Añadir Lista....</a></li>
                                </ul>
                            </li>
                            <li><a href="global.htm">Global</a></li>
                            <li><a href="eventos.htm">Eventos</a></li>
                        </ul>
                        <form:form action="buscarVino.htm" commandName="busquedavino">
                            <form:input class="inputsearch" path="cadenabusqueda" type="text" valuer="Buscador de vinos..."/>
                        </form:form>
                        <ul class="nav secondary-nav">
                            <li class="menu">
                                <a id="menu" class="menu" href="#">${UsuarioSesion.alias}</a>
                                <ul id="lista" class="menu-dropdown">
                                    <li><a href="verPerfilUsuario.htm">Mi perfil</a></li>
                                    <li><a href="getLogros.htm">Logros</a></li>
                                    <li><a href="logout.htm">Log Out</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div> <!-- /fill -->
            </div> <!-- /topbar -->
        </div>
  
     
<div   class="container-principal">
<div id="principal">
    
<div id="content-header">
    
    <c:if test="${error ne ''}"> 
             <div class="alert-message error" >
                 <a class="close" href="#">×</a>
                       <p>
                           <strong>Oh Oh! Error:${error} </strong>
                                                    
                       </p>
             </div>
    </c:if> 
    
   
    <div style="float:left">
     
	  <h1>${listaeventos[0].titulo} <small> ${listaeventos[0].fechainicio}</small> </h1>
	  
     
    </div>
	
	<div id="map-canvas" style="width:500px;height:200px;float:left;margin-top:35px"></div>
	
	<div style="float:right">
     <h3 class="section-title">Establecimiento</h3>
     <fieldset class="separacionarriba">
          <div id="div2" class="clearfix">
            <label for="">Nombre:</label>
            <h4>${listaeventos[1].nombre}</h3>
          </div> <!-- /clearfix -->
          <div class="clearfix">
            <label for="">Ciudad:</label>
            <h4>Villanueva de la Serena</h3>
          </div> <!-- /clearfix -->
           <div class="clearfix">
            <label for="">Pais:</label>
            <h4>España</h3>
          </div> <!-- /clearfix -->
          <div class="clearfix">
            <label for="">Direccion:</label>
            <h4><a href="#">${listaeventos[1].direccion}</a></h3>
          </div> <!-- /clearfix -->
</div>

<fieldset class="separacionarriba rellenoinfo" style="float:left;width:550px;margin-top:25px">
<div class="clearfix">
            
            ${listaeventos[0].descripcion}
</div>
</fieldset>

<div style="float:right;width:300px">
<h3 class="section-title">Promociones</h3>
<div>
<ul class="galeria">
          <li>
                
            		<div>
            		<strong> Descuento en la entrada de 3 Euros </strong>
            		<div>
            	    <a href="#" id="boton1" class="btn small">Enviar promocion</a>
            		
            		</div>
            		</div>
          </li>
        </ul>




</div>

</div>

     

     
</div>
   
<div id="informacion" class="barra-izquierda">
  
    

    
    
</div>
</div>
</div>
   
   <script type="text/javascript" src="./jQuery.js"></script>
   <!--script type="text/javascript" src="http://code.jquery.com/jquery-1.5.2.min.js"></script>-->
   <script type="text/javascript" src="./infoVino.js"></script>
   <script type="text/javascript" src="./sistemaPuntuacion.js"></script>
   <script type="text/javascript" src="./jquery-ui-1.8.16.custom.min.js"></script>
   <script type="text/javascript" src="./jquery.simplemodal.js"></script>
   <script type="text/javascript" src="./jquery.lightbox-0.5.js"></script>
   <script type="text/javascript" src="./tabs.js"></script>
   <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>

   
<script>
var map;
function initialize() {
  var mapOptions = {
    zoom: 15,
    center: new google.maps.LatLng(38.971437,-5.78635)
  };
  map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);
	  
  

    var companyPos = new google.maps.LatLng(38.971437,-5.78635);
    var companyMarker = new google.maps.Marker({
          position: companyPos,
          map: map,
          title:"Restaurante el Rodeo"
      });

	  
}

google.maps.event.addDomListener(window, 'load', initialize);

</script>
 <script type="text/javascript" src="./javascript/jQuery.js"></script>
<script type="text/javascript" src="./javascript/listadobodegasUsuario.js"></script>

  
  
  </body>
  

  
  </html>