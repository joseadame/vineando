<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es-Mx" lang="es-Mx" />
<head>
<meta charset="utf-8">
    <title>Danos a conocer tu opinion de : ${vino.nombre}</title>
<meta name="description" content="">
<meta name="author" content="">

<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<!-- Le styles -->

<link rel="StyleSheet" type="text/css" href="./css/docs.css" media="all"/>
<link rel="StyleSheet" type="text/css" href="./css/prettify.css" media="all"/>
<link rel="StyleSheet" type="text/css" href="./css/puntuacion.css" media="all"/>
<link rel="StyleSheet" type="text/css" href="./css/bootstrap-1.0.0.css" media="all"/>
<link rel="StyleSheet" type="text/css" href="./css/Styles.css" media="all"/>




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
                                <a id="menubodega" class="Menu" href="#">Tus listas</a>
                                <ul id="listabodega" class="menu-dropdown">
                                    <c:forEach var="bodega" items="${listabodegas}">
                                        <li><a href="busquedaBodega.htm?idbodega=${bodega.idbodega}&idusuario=${usuario.id}">${bodega.descripcion}</a></li>
                                    </c:forEach>
                                    <li class="divider"></li>
                                    <li><a href="nuevaBodega.htm">Añadir lista....</a></li>
                                </ul>
                            </li>
            <li><a href="global.htm">Global</a></li>
            
          </ul>
          <form:form action="buscarVino.htm" commandName="busquedavino">
                                    <form:input class="inputsearch" path="cadenabusqueda" type="text" valuer="Buscador de vinos..."/>
          </form:form>
          <ul class="nav secondary-nav">
            <li class="menu">
              <a id ="menu" class="menu" href="#">${usuario.alias}</a>
              <ul id="lista" class="menu-dropdown">
                <li><a href="verPerfilUsuario.htm">Mi perfil</a></li>
                <li><a href="getLogros.htm">Logros</a></li>
                <li class="divider"></li>
                <li><a href="logout.htm">Log Out</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div> <!-- /fill -->
    </div> <!-- /topbar -->
  </div>
  
     
    <div class="container-principal">
    <div id="principal">
    <div id="informacion" class="barra-izquierda">
    
      <div id="ventanacata">
          <div class="modal-header">
            <h3>Informacion de la cata de <strong>${vino.nombre}</strong></h3>
            
          </div>
          
          <div class="modal-body" style="heigth:200px">
              
                <c:if test="${fechaultimacata ne ''}"> 
                                            <div id="mensajeinfo" class="alert-message success" >
                                                <a id="xcerrar"class="close" href="#">×</a>
                                                <p>
                                                    <strong>No pruebas este vino desde ${fechaultimacata}</strong>
                                                    
                                                </p>
                                            </div>
                </c:if>
              <c:if test="${fechaultimacata eq ''}">
                  <div id="mensajeinfo" class="alert-message success" >
                                                <a id="xcerrar"class="close" href="#">×</a>
                                                <p>
                                                    <strong>No has probado nunca este vino, ¡animáte y dinos que te parece!</strong>
                                                    
                                                </p>
                                            </div>
                  
                  
              </c:if>
              
            <p>Añade un comentario de cata:</p>
            <form:form id="checkinvino" method="post" action="checkin.htm?idvino=${idvino}" commandName="pruebavino">
            <div class="div-bordes">
            <form:textarea path="comentario" class="xxlarge" id="comentariocata" name="textarea" placeholder="¿Que te ha parecido de sabor? ¿Con que lo has bebido?...."/></textarea>
            <div>
          <!--  <span ><img src="images/user.png" title="Añade amigos con los que has probado este vino" id ="iconamigos" height="25" width="25" alt="Añadir amigo"></span>
            <input id="addamigos2" class="xxlarge" type="text" size="40" name="prependedInput"/ style="display:none">-->
            </div>
            <form:input path="nota" id="notainput" type="hidden"/>
           
            
            <div class="puntuacion" id="1">		
        			<div class="estrella estrella_izq on"><a style="width: 100%" href="#0.5" title="0.5/5">0.5</a></div>
        			<div class="estrella estrella_der"><a style="width: 100%" href="#1" title="1/5">1</a></div>
        			<div class="estrella estrella_izq"><a style="width: 100%" href="#1.5" title="1.5/5">1.5</a></div>
        			<div class="estrella estrella_der"><a style="width: 100%" href="#2" title="2/5">2</a></div>
        			<div class="estrella estrella_izq"><a style="width: 100%" href="#2.5" title="2.5/5">2.5</a></div>
        			<div class="estrella estrella_der"><a style="width: 100%" href="#3" title="3/5">3</a></div>
        			<div class="estrella estrella_izq"><a style="width: 100%" href="#3.5" title="3.5/5">3.5</a></div>
        			<div class="estrella estrella_der"><a style="width: 100%" href="#4" title="4/5">4</a></div>
        			<div class="estrella estrella_izq"><a style="width: 100%" href="#4.5" title="4.5/5">4.5</a></div>
        			<div class="estrella estrella_der"><a style="width: 100%" href="#5" title="5/5">5</a></div>
	         	</div>
           
            <div>
              <span>Nota:</span><span id="nota">0</span>
            </div>
            </div>
            <div class="div-bordes">
             <p><strong>¿Que te ha parecido la relacion calidad/precio?</strong></p>
            <form:select path="relacion_calidad">
            
              <option value="">-Selecciona-</option>
              <option value="Excelente">Excelente</option>
              <option value="Buena">Buena</option>
              <option value="Correcta">Correcta</option>
              <option value="Mala">Mala</option>
              <option value="Pesima">Pesima</option>
            </form:select>
            </div>
            
            
            
            <div id=lugares class="div-bordes">
           
            
            <p><strong>Opcionalmente puedes introducir el lugar donde lo has probado</strong></p>
            <div id="div1" class="clearfix">
              <label for="">Ciudad:</label>
              <div class="input">
                  <form:input path="ciudad" type="text" size="30"  id="inputciudad" class="xlarge"/>
              </div>
            </div>
            <div id="div1" class="clearfix">
              <label for="">Establecimiento:</label>
              <div class="input">
                  <form:input path="establecimiento" type="text" size="30"  id="inputestablecimiento" class="xlarge"/>
              </div>
            </div>
         <p>  <strong>¿Los has probado solo?</strong></p>
           <div id="div1" class="clearfix">
              <label for="">Añade a tus amigos:</label>
              <div class="input">
                   <form:input path="amigos" id="addamigos2" class="xlarge" type="text" size="40" name="prependedInput"/>
              </div>
            </div>
          
            </form:form>
            </div>
          
            
            
          </div>

                <div style="float:right">
                    <a href="#" id="checkin" class="btn primary">Catar vino</a>
                    
                </div>
            </div>

    
     
        
        
        
        
        
        
        
        
    
        
        
        
        
     <div id="checkins" class="bordes">
        <h3 class="section-title">Checkins sobre este vino</h3>
          
        
     </div>
   
    
   
      </div>
      
      <div id="barra-derecha">
      <div>
          <h3 class="section-title">Checkin</h3>
    <p>Puedes hacer checkins en los vinos que pruebas, escribe un comentario, dale una puntuacion. Puedes incluso
    especificar la ciudad y el lugar donde lo has tomado, solo o en compañia. Cuanta mas información nos proporciones
    mejores resultados obtendrás.</p>
    
    
    </div>
    
    <div>
    <h3 class="section-title">Top checkins</h3>
    <ul class="galeria">
            
            <c:forEach var="vino" items="${listatopVinosCheckins}">
          <li class="resultados-lista">
              <div class="avatar-lista">
          				<img width="46px" border="0" src="${vino[13]}" heigth="30px"> 
          		  </div>
          		<div>
          		<a class="linkresaltado" href="busquedavinoID.htm?idvino=${vino[1]}">${vino[2]}</a> ${vino[4]} ${vino[3]}
              <div>Puntuacion: ${vino[10]} </div>
          		<div>
          		<strong>${vino[6]}</strong>
            	</div>
                <div>
          		<strong> Numero checkins: ${vino[0]}</strong>
            	</div>
          		</div>
          </li>
            </c:forEach>
            
        </ul>
    
    
    </div>
          
     <div>
    <h3 class="section-title">Mejores catadores</h3>
     <ul class="galeria">
                
                <c:forEach var="usuario" items="${listatopUserCheckins}">
                <li class="resultados-lista">
                          <div class="avatar-lista">
          				<img width="46px" border="0" src="${usuario[4]}" heigth="30px"> 
          		  </div>
            		<div id="avatar" class="details">
            		<a id="${solicitud[0].id}" class="linkresaltado solicitud" href="busquedausuario.htm?idusuario=${usuario[1]}">${usuario[2]}</a> 
                        
                        <div>Numero de checkins: ${usuario[0]} </div>
            		</div>
               </li>
                </c:forEach>
                
            </ul>
    
    
    </div>
      
      
      </div>
      
   
   
   
   
   </div>
    </div>
            
             <DIV  class="container-principal">
  <DIV class="opcionespie"><STRONG><A href="#">Blog</A></STRONG></DIV>
  <DIV class="opcionespie">Vineando 2013</DIV>
  <DIV class="opcionespie"><STRONG><a href="mailto:info@vineando.com">Contacto</a></STRONG></DIV>
  </DIV>
   
   <script type="text/javascript" src="./javascript/jQuery.js"></script>
   <!--script type="text/javascript" src="http://code.jquery.com/jquery-1.5.2.min.js"></script>-->
   <script type="text/javascript" src="./javascript/sistemaPuntuacion.js"></script>
   <script type="text/javascript" src="./javascript/checkinvino.js"></script>

  
  </body>
  
</html>
