<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es-Mx" lang="es-Mx" /> <head>
<title>Resultados de la busqueda</title>


<head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le styles -->
    
    <link href="docs.css" rel="stylesheet">
    <link href="prettify.css" rel="stylesheet">
   <style type="text/css">@import url(./css/docs.css);</style>
   <style type="text/css">@import url(./css/prettify.css);</style>

    <!-- Le javascript -->
    
    
    
    <style type="text/css">@import url(./css/bootstrap-1.0.0.css);</style>
    <style type="text/css">@import url(./css/Styles.css);</style>
    
    
  
  </head>
  <body>
  <div style="z-index: 5;" class="topbar-wrapper">
            <input type="hidden" id="porcentajeCompletado" value="${porcentajeCompletado}" /> 
            <div class="topbar">
                <div class="fill">
                    <div class="container">
                        <h3><a href="#">Vineando</a></h3>
                        <ul>
                            <c:if test="${usuario ne null}">
                            <li class="active"><a href="perfilUsuario.htm">Home</a></li>
                            </c:if>
                            <c:if test="${usuario eq null}">
                            <li class="active"><a href="index.htm">Home</a></li>
                            </c:if>
                            <c:if test="${usuario ne null}">
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
                            </c:if>
                          <c:if test="${usuario ne null}">  
                        <li><a href="global.htm">Global</a></li>
                            </c:if>
                        </ul>
                        <form:form action="buscarVino.htm" commandName="busquedavino">
                                    <form:input class="inputsearch" path="cadenabusqueda" type="text" valuer="Buscador de vinos..."/>
                        </form:form>
                        <c:if test="${usuario ne null}">
                        <ul class="nav secondary-nav">
                            <li class="menu">
                                <a id="menu" class="menu" href="#">${usuario.alias}</a>
                                <ul id ="lista" class="menu-dropdown">
                                    <li><a href="verPerfilUsuario.htm">Mi perfil</a></li>
                                    <li><a href="getLogros.htm">Logros</a></li>
                                    <li class="divider"></li>
                                    <li><a href="logout.htm">Log Out</a></li>
                                </ul>
                            </li>
                        </ul>
                        </c:if>
                    </div>
                </div> <!-- /fill -->
            </div> <!-- /topbar -->
        </div>
  
     
   <div class="container-principal">
      <div id="principal">
          <div id="lista-vinos" class="barra-izquierda">
    <div class="cabeceraRegistro">
      <h2>Resultados de la busqueda</h2>
      <p>Si no encuentras el vino puedes añadirlo</p>
      <c:if test="${usuario ne null}">
      <div>
          <a href="altaVino.htm" id="boton1" class="btn small">Añadir vino</a>
        
        </div>
      </c:if>
    </div> 
   <div class="errorMsg" id="errorMsg">
         <p>${error}</p>
   </div>
   
   <c:if test="${vinos.size()==0}">
       <div class="alert alert-success">
                 <h3>No existen vinos para tu busqueda</h3>
       </div>
   </c:if>
   
   <div class="actividad" id="actividad">
       
       
		
		<ul class="events">
                 
                    
                    
                    
                 <c:forEach  var="vino" items="${vinos}">   
		<li class="resultados-lista">
                    <div class="img">
				<img border="0" width="46px" heigth="46px" src="${vino.rutaimagen}"> 
		     </div>
		<div class="details">
		<a href="busquedavinoID.htm?idvino=${vino.idvino}" class="linkresaltado"><c:out value="${vino.nombre}"/></a> <c:out value="${vino.anio}"/> <c:out value="${vino.tipovino}"/> Puntuacion: <strong> ${vino.notamedia}/5 </strong>
                <div>
                <strong>${vino.zona}</strong>
                </div>
		</div>
                    
		</li>
                 </c:forEach>
		

		

	</ul>
       
   
       
       
	</div>
   
   <div class="actividad" style="float:left">
	<h2>Usuarios encontrados </h2>
        
        <c:if test="${usuariosencontrados.size()==0}">
       <div class="alert alert-success">
                 <h3>No existen usuarios para tu busqueda</h3>
       </div>
   </c:if>
	
	<ul class="events">
             <c:forEach  var="listausuarios" items="${usuariosencontrados}">  
		<li class="resultados-lista" "><div class="img">
				<img border="0" width="46px" heigth="46px" src="${listausuarios.rutaAvatar}"> 
		     </div>
		<div class="details">
		    <a href="busquedausuario.htm?idusuario=${listausuarios.id}" class="linkresaltado">${listausuarios.alias}</a>
    
		</div>
		</li>
             </c:forEach>

		

	</ul>
	
   </div>
   
   
   
          </div>
   <div id="barra-derecha">
   
   <div id="lista-top">
   <h3 class="section-title">Zona del vino</h3>
   <fieldset>

   <ul class="galeria">
   <c:forEach items="${listazonasvino}" var="listazonas">
   <li class="galeria">
   <div class="perfil">
    <a href="zona.htm?zona=${listazonas[0]}">${listazonas[0]} (${listazonas[1]})</a>
   </div>
   </li>
   </c:forEach>
   
   
   </ul>
   

   
   </fieldset>
   
   
   
   </div>
       
   <div >
   <h3 class="section-title">Tipo de vino</h3>
   <fieldset>

   <ul class="galeria">
       <c:forEach items="${listadotipos}" var="tipos">
   <li class="galeria">
   <div class="perfil">
    <a href="tipovino.htm?tipo=${tipos[0]}">${tipos[0]} (${tipos[1]})</a>
   </div>
   </li>
       </c:forEach>

  
   </ul>
   <ul class="galeria">
   
   </ul>

   
   </fieldset>
   
   
   
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
   <script type="text/javascript" src="./javascript/listadoVinos.js"></script>
   
   
  
  </body>
  

  
  </html>