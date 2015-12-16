<?xml version="1.0" encoding="utf-8"?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es-Mx" lang="es-Mx" /> <head>
    <meta charset="utf-8">
    <title>Logros de ${UsuarioSesion.alias}</title>
    <meta name="description" content="">
    <meta name="author" content="">

    
    
    
    <style type="text/css">@import url(./css/docs.css);</style>
    <style type="text/css">@import url(./css/prettify.css);</style>
    <style type="text/css">@import url(./css/bootstrap-1.0.0.css);</style>
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
            <li class="active"><a href="perfilUsuario.htm">Inicio</a></li>
            <li class="menu">
            <a id="menubodega" class="Menu" href="#">Tus listas</a>
            <ul id="listabodega" class="menu-dropdown">
                <c:forEach var="bodega" items="${listabodegas}">
                <li><a href="busquedaBodega.htm?idbodega=${bodega.idbodega}&idusuario=${UsuarioSesion.id}">${bodega.descripcion}</a></li>
                </c:forEach>
                <li class="divider"></li>
                <li><a href="nuevaBodega.htm">Añadir Lista....</a></li>
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
    
  
   <div id="tabla-bodegas">
   <h2>Logros que has conseguido</h2> 
    
   <table>
        <thead>
          <tr>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="logro" items="${listalogros}">
                  <tr>
                    <td><div class="imagen-logro">
                          <img id="imagenusuario" src="images/badge.png">

                          </div></td>
                    <td>${logro[1].nombre}<div></td>
                    <td>${logro[1].descripcion}</td>
                    <td>${logro[0]}</td>

                  </tr>
            </c:forEach>
          
          
          
        </tbody>
      </table>
      </div>
      </div>
      
      <div id="barra-derecha">
      <div>
    <h3 class="section-title">Listado de logros</h3>
    <p>Aqui puedes ver todos los logros que has conseguido. Sigue participando e iras descubriendo cada vez mas logros.</p>
    
    
    </div>
    
     
    
    
      
      
      </div>
      
   
   
   
   
   </div>
    </div>
        
         <DIV  class="container-principal">
  <DIV class="opcionespie"><STRONG><A href="#">Blog</A></STRONG></DIV>
  <DIV class="opcionespie">Vineando 2011</DIV>
  <DIV class="opcionespie"><STRONG><a href="mailto:info@vineando.com">Contacto</a></STRONG></DIV>
  </DIV>
   
   <script type="text/javascript" src="./javascript/jQuery.js"></script>
   
   <script type="text/javascript" src="./javascript/listadobodegasUsuario.js"></script>

  
  </body>
