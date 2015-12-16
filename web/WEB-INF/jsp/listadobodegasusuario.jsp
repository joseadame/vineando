<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es-Mx" lang="es-Mx" /> <head>
    <meta charset="utf-8">
    <title>Lista de vinos de ${usuario.alias}</title>
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
                <li><a href="#"></a></li>
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
        
          <c:if test="${listaacciones.size() > 0}">
                
                   
                 <c:forEach var="accion" items="${listaacciones}">
                                    <div id="mensajeacciones"class="alert alert-success">
                                         <a id="cerraralert"class="close" href="#">×</a>
                                         
                                           
                                           <div class="imagen-logro">
                                              <img id="imagenusuario" src="images/badge.png">

                                            </div>
                                             <div>
                                            <strong>${accion.nombre}</strong> 


                                            </div>    
                                            <div>
                                                <strong>${accion.descripcion}</strong>
                                            </div>    
                                            <div>

                                                 <label>Puntos obtenidos:</label><strong>${accion.puntuacion}</strong>

                                            </div>

                                         
   
                                     </div>
                                 </c:forEach>
                
                
            </c:if>
        
        
        
    <div id="principal">
    <div id="informacion" class="barra-izquierda">
        
        
        
    <div class="span12 columns">
        
         
   <form:form method="post" id="nuevabodega" commandName="nuevaBodega" action="altaBodega.htm">
    <fieldset>
          <h1>Listas de <a href="busquedausuario.htm?idusuario=${usuario.id}">${usuario.alias}</a></strong></h1>
          <h2>Nueva lista</h2>
          <c:if test="${error ne ''}"> 
                                            <div class="alert-message error" >
                                                <a class="close" href="#">×</a>
                                                <p>
                                                    <strong>Oh Oh! Error:${error} </strong>
                                                    
                                                </p>
                                            </div>
                                        </c:if> 
          
          
          <h3>Indique el nombre de la lista que va a dar de alta</h3>
          <div id="div1" class="clearfix">
            <label for="">Nombre de la lista:</label>
            <div class="input">
              <form:input path="descripcion" type="text" size="30"  id="input1" class="xlarge"/>
              <form:errors path="descripcion"/>
            </div>
          </div> <!-- /clearfix -->
          
           <div class="clearfix">
          
            <label for="">¿Quieres que la lista la puedan ver los demas usuarios?</label>
            <div class="input">
              <form:select path="compartida" id="select1" class="medium">
              <option value="Si">Si</option>
              <option value="No">No</option>
              </form:select>
            </div>
          
          </div>
         
          <div class="actions">
              <input class="btn primary" id="btnnuevabodega" type="button" value="Crear Lista de vinos"/>
            
                    </div>
                         
            
        </div>
    </fieldset>
      </form:form>  
        
   
    
        <div id="tabla-bodegas"> 
   <h2>Tus listas</h2> 
   <table>
        <thead>
          <tr>
            <th>Nombre de la lista</th>
            <th>Puntuacion media</th>
            <th>Compartida</th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
        <c:forEach  var="bodega" items="${listabodegas}">  
          <tr>

              <td><p><a href="busquedaBodega.htm?idbodega=${bodega.idbodega}&idusuario=${usuario.id}">${bodega.descripcion}</a></p><form:form id="cambiarnombre" action="cambioNombreBodega.htm?idbodega=${bodega.idbodega}" method="post" commandName="bodega"><form:input path="descripcion" id="inputnombre" type="text" style="display:none" class="xlarge"/></form:form></td>
            <td>${bodega.notamedia}</td>
            <td>${bodega.compartida}</td>
            <td><a href="borrarBodega.htm?idbodega=${bodega.idbodega}"><img src="./images/wrong.png" alt="Eliminar" title="Eliminar" width="25px" heigth="25px"/></td></a>
            <td><a href="" class="edit"><img src="./images/edit.png"  alt="Eliminar" title="Editar nombre" width="25px" heigth="25px"/></a></td>
          </tr>
          </c:forEach>
          
          
        </tbody>
      </table>
        </div>
       </div>
        
        <div id="barra-derecha">
      <div>
    <h3 class="section-title">Listas de vinos</h3>
    <p>Puedes crear tantas listas de vinos como quieras. Usalas para recordar y clasificar aquellos vinos que has ido encontrado en la red social, recuerda
    que tambien puedes hacerlas públicas para compartir con los demas usuarios los vinos que te han llamado la atención</p>
    
    
    </div>
    
    <div>
    <h3 class="section-title">Consejos</h3>
    <p>Si quieres crear una lista de vinos compartida solo tienes que elegir la opcion "Si" en el combo de opciones.</p>
    <p>Intenta crear listas utiles, para recordar vinos, vinos con mayor puntuacion, vinos que quiero probar...</p>
    <p>Siempre puedes añadir un vino a cualquiera de tus listas dentro de la página de información de cada vino.</p>
    
    
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
   <script type="text/javascript" src="./javascript/listadobodegasUsuario.js"></script>

  
  </body>
  

  
  </html>