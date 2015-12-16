<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es-Mx" lang="es-Mx" />
<head>
<head>
<meta charset="utf-8">
<title>Registro de nuevo usuario</title>
<meta name="description" content="">
<meta name="author" content="">

<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<!-- Le styles -->

<link rel="StyleSheet" type="text/css" href="./css/docs.css" media="all"/>

<link rel="StyleSheet" type="text/css" href="./css/prettify.css" media="all"/>

<link rel="StyleSheet" type="text/css" href="./css/jquery-ui-1.8.16.custom.css" media="all"/>



<!-- Le javascript -->


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
          <li class="active"><a href="index.htm">Inicio</a></li>
        </ul>
        <form:form action="buscarVino.htm" commandName="busquedavino">
                                    <form:input class="inputsearch" path="cadenabusqueda" type="text" valuer="Buscador de vinos..."/>
        </form:form>
        
      </div>
    </div>
    <!-- /fill --> 
  </div>
  <!-- /topbar --> 
</div>
<div class="container-principal">
<div id="principal">
  <div class="barra-izquierda">
  <div class="div-bordes">
  <p><strong>¡Registrate en la beta privada!. Entraras a formar parte de la comunidad de Vineando, podras añadir vinos, añadir amigos, puntuar vinos, 
escribir comentarios.. y sobre todo aprender y disfrutar del apasionante mundo del vino.
Si quieres inscribirte en la beta privada introduce tu mail a continuación.</strong></p>
  </div>
  <div class="span12 columns">
   <h3 class="section-title">Datos de registro</h3>
    <form:form id="formulario" action="makeregistro.htm" method="post" class="login" commandName="login">
      <fieldset>
          
          <c:if test="${error ne ''}"> 
                                            <div class="alert-message error" >
                                                <a class="close" href="#">×</a>
                                                <p>
                                                    <strong>Oh Oh! Error:${error} </strong>
                                                    
                                                </p>
                                            </div>
                                        </c:if> 
         
          
           
          <c:if test="${mensajebeta eq 'Si'}"> 
                                            <div class="alert-message info" >
                                                <a class="close" href="#">×</a>
                                                <p>
                                                    <strong>Has sido dado de alta en Vineando (Beta). En breve nos pondremos en contacto contigo. </strong>
                                                    
                                                </p>
                                            </div>
                                        </c:if> 
       
                            <div id="divemail" class="clearfix">
                                <label for="">Email:</label>
                                <div class="input">
                                <form:input path="email" id="email" type="text" class="xlarge" data-content="Introduce el email con el que quieres registrarte en la beta privada" data-original-title="Introduce un email valido"/> 
                                <form:errors path="email"/>
                                </div>
                            </div>
                            
      </fieldset>
      <a href="#" id="btnregistro" class="btn primary" style="float:right">¡Registrarse!</a>
      <div>
      <p>Si ya estas registrado puedes hacer <a href="loginusuario.htm">Login</a></p>
      <div>
    </form:form>
  </div>
  </div>
  
  
  </div>
</div>
    <div id="barra-derecha">
   <div id="galeria">
     <h3 class="section-title">Ultimos usuarios registrados</h3>
     <ul class="galeria">
         <c:forEach var="usuarios" items="${listadousuarios}">
     <li class="imagen"><img width="40px" height="40px" src="${usuarios.rutaAvatar}" title="${usuarios.alias}"></li>
         </c:forEach>
     
     </ul>
     
     
     </div>
  
  <div>
  
  
  </div>
  
  
  </div>
</div>
</div>
    
      <DIV id="piefooter">
  <DIV class="opcionespie">
      <STRONG><A href="#">Blog</A></STRONG>
      
  </DIV>
      
      <div class="opcionespie">
         <STRONG><a href="mailto:info@vineando.com">Contacto</a></STRONG>
          
      </div>
      
      
  <DIV style="float:right;margin-right: 10px">Vineando 2013</DIV>
 
  </DIV>
<script type="text/javascript" src="./javascript/jQuery.js"></script> 
<script type="text/javascript" src="./javascript/registro.js"></script>
<script type="text/javascript" src="./javascript/typsy.js"></script>
<script type="text/javascript" src="./javascript/popover.js"></script>

</body>
</html>