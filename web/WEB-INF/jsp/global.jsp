<%-- 
    Document   : global
    Created on : 12-Abril-2013, 19:00:25
    Author     : Jose Adame
--%>

<?xml version="1.0" encoding="utf-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es-Mx" lang="es-Mx" /> <head>
    <meta charset="utf-8">
    <title>Perfil de ${Usuario.alias}</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le styles -->
    
    
    <style type="text/css">@import url(./css/docs.css);</style>
    <style type="text/css">@import url(./css/prettify.css);</style>
    <style type="text/css">@import url(./css/bootstrap-1.0.0.css);</style>
    <style type="text/css">@import url(./css/jquery-ui-1.8.16.custom.css);</style>
    <link rel="StyleSheet" type="text/css" href="./css/Styles.css" media="all"/>
    
    
    

    <!-- Le javascript -->
    
  
 
  
  
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
                <li><a href="busquedaBodega.htm?idbodega=${bodega.idbodega}&idusuario=${UsuarioSesion.id}">${bodega.descripcion}</a></li>
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
              <a id ="menu" class="menu" href="#">${UsuarioSesion.alias}</a>
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
  
     
    <div class="container-principal">
    <div id="principal">
    <div class="barra-izquierda">
    
        <div id="info-user">
             <c:if test="${error ne ''}"> 
                                            <div class="alert-message error" >
                                                <a class="close" href="#">×</a>
                                                <p>
                                                    <strong>Oh Oh! Error</strong>
                                                    ${error}
                                                </p>
                                            </div>
                                        </c:if> 
     
    
      </div>
    
    
      
        <div  id="actividad">	
		       <h3 class="section-title">Actividad</h3>
          		<ul id="listanotificaciones" class="galeria">
          		  <c:forEach var="notificacion" items="${listanotificaciones}">
                                ${notificacion}
                            </c:forEach>
              
        
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
   <script type="text/javascript" src="./javascript/global.js"></script>
   <script type="text/javascript" src="./javascript/jquery-ui-1.8.16.custom.min.js"></script>
  
  </body>
  

  
  </html>
