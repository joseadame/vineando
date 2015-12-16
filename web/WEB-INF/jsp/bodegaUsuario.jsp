<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es-Mx" lang="es-Mx" /> <head>
    <meta charset="utf-8">
    <title>Listas de vinos de ${usuario.alias}</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le styles -->
     <link rel="StyleSheet" type="text/css" href="./css/docs.css" media="all"/> 
      <link rel="StyleSheet" type="text/css" href="./css/prettify.css" media="all"/> 
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
                <c:forEach var="bodega" items="${listadobodegas}">
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
  
     
   <div class="container">  
        
  <div class="container-principal">
    <div id="principal">
        
       
        
        
        
        
        
        
    <div id="listas" class="barra-izquierda">
    <h2>Lista " ${bodega.descripcion}" de <a href="busquedausuario.htm?idusuario=${idusuario}">${aliasusuario}</a> </h2> 
    
     <c:if test="${error ne ''}"> 
                                            <div id="mensajeinfo" class="alert-message error" >
                                                <a id="xcerrar" class="close" href="#">×</a>
                                                <p>
                                                    <strong>Oh Oh! Error:${error} </strong>
                                                    
                                                </p>
                                            </div>
                                        </c:if> 
          <c:if test="${exito ne ''}"> 
                                            <div id="mensajeinfo" class="alert-message success" >
                                                <a id="xcerrar" class="close" href="#">×</a>
                                                <p>
                                                    <strong>${exito}</strong>
                                                    
                                                </p>
                                            </div>
                                        </c:if> 
    
    
   <h3>Puntuacion media de la lista <strong>${bodega.notamedia}</strong></h3> 
   <table>
        <thead>
          <tr>
            <th></th> 
            <th>Nombre del vino</th>
            <th>Añada</th>
            <th>Puntuacion</th>
            <th></th>
            
          </tr>
        </thead>
        <tbody>
            <c:forEach var="vino" items="${listadovinos}"> 
          <tr>
            <td><img width="46px" border="1" src="${vino.rutaimagen}" heigth="15px"></td>
            <td><p><a href="busquedavinoID.htm?idvino=${vino.idvino}">${vino.nombre}</a></p>
            <small>${vino.zona}</small></td>
            <td>${vino.anio}</td>
            <td>${vino.notamedia}/5</td>
            <td><td><a href="borrarVinoBodega.htm?idvino=${vino.idvino}&idbodega=${bodega.idbodega}"><img src="./images/wrong.png" alt="Eliminar" title="Eliminar" width="25px" heigth="25px"/></td></a>
            
          </tr>
            </c:forEach>
          
        </tbody>
      </table>
      
      
   
   
   
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
   <script type="text/javascript" src="./javascript/bodegausuario.js"></script>

  
  </body>
  

  
  </html>