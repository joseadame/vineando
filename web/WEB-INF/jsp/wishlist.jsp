<%-- 
    Document   : listaVinosCatados
    Created on : 14-dic-2011, 20:16:27
    Author     : Jose Adame
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es-Mx" lang="es-Mx" > 
<head>

  
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Tu lista de deseos</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le styles -->
    
    
  
   <style type="text/css">@import url(./css/docs.css);</style>
   <style type="text/css">@import url(./css/prettify.css);</style>
    <!-- Le javascript -->
    
    
    <style type="text/css">@import url(./css/bootstrap-1.0.0.css);</style>
    <style type="text/css">@import url(./css/Styles.css);</style>
    
  
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
              <a id="menu" class="menu" href="#">${usuarioSesion.alias}</a>
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
   
   
    <div id="lista-vinos" class="barra-izquierda">
    <div class="cabeceraRegistro">
      <h2>Listado de vinos ${titulopagina}</h2>
      
      <div>
          <a href="altaVino.htm" id="boton1" class="btn small">Añadir vino</a>
        
        </div>
    </div> 
   <div class="errorMsg" id="errorMsg">
                            <p>${error}</p>
   </div>
   
   <c:if test="${listawish.size()==0}">
       <div class="alert alert-success">
                 <h3>¡Tu lista de deseos esta vacia!. Es util para recordar aquellos vinos que quieres regalar, degustar, comprar...</h3>
       </div>
   </c:if>
   
   <div class="actividad" id="actividad">	
		
		<ul class="events">
                    
             <c:forEach var="wishlist" items="${listawish}">
		<li class="resultados-lista">
                    <div class="img">
				<img border="0" width="46px" heigth="46px" src="${wishlist.rutaimagen}"> 
		     </div>
		
		<div class="details">
		
		<a href="busquedavinoID.htm?idvino=${wishlist.idvino}" class="linkresaltado">${wishlist.nombre} </a> ${wishlist.anio} ${wishlist.tipovino} 
                <div>Puntuacion del vino:  ${wishlist.notamedia}  </div>
		<div>
		<strong>${wishlist.zona}</strong>
		</div>
		<div>
		<strong>${wishlist.bodega}</strong>
  	</div>
		</div>
		
		</li>
                </c:forEach>

		

	</ul>
       
       <c:if test="${listawish.size()!=0}">
	<div class="pagination">
        <ul>
          <li class="prev disabled"><a href="#">? Previous</a></li>
          <li class="active"><a href="#">1</a></li>
          <li><a href="#">2</a></li>
          <li><a href="#">3</a></li>
          <li><a href="#">4</a></li>
          <li><a href="#">5</a></li>
          <li class="next"><a href="#">Next ?</a></li>
        </ul>
      </div>
          </c:if>
	</div>
     
   
   
   </div>
   
   <div id="barra-derecha">
   
   <div id="lista-top">
   <h3 class="section-title">Vinos mas votados</h3>
   
   
   
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
   <script type="text/javascript" src="./javascript/listadocatas.js"></script>
   
  
  </body>
  

  
  </html>
