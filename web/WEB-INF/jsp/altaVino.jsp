<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="es-ES">  
    <head>
        <meta charset="utf-8">
            <title>¡Danos a conocer un nuevo vino!</title>
            <meta name="description" content="">
                <meta name="author" content="">
                    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
                    <!--[if lt IE 9]>
                      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
                    <![endif]-->
                    <!-- Estilos -->
                    <link rel="StyleSheet" type="text/css" href="./css/docs.css" media="all"/>
                    <link rel="StyleSheet" type="text/css" href="./css/prettify.css" media="all"/>
                    <link rel="StyleSheet" type="text/css" href="./css/bootstrap-1.0.0.css" media="all"/>     
                    <link rel="StyleSheet" type="text/css" href="./css/Styles.css" media="all"/>
                    </head>
                    <!-- Codigo -->                                
                    <body>
                        <div style="z-index: 5;" class="topbar-wrapper">
            <input type="hidden" id="porcentajeCompletado" value="${porcentajeCompletado}" /> 
            <div class="topbar">
                <div class="fill">
                    <div class="container">
                        <h3><a href="#">Vineando</a></h3>
                        <ul>
                            <li class="active"><a href="perfilUsuario.htm">Home</a></li>
                            <li class="menu">
                                <a id="menubodega" class="Menu" href="#">Listas</a>
                                <ul id="listabodega" class="menu-dropdown">
                                    <c:forEach var="bodega" items="${listabodegas}">
                                        <li><a href="busquedaBodega.htm?idbodega=${bodega.idbodega}&idusuario=${usuario.id}">${bodega.descripcion}</a></li>
                                    </c:forEach>
                                    <li class="divider"></li>
                                    <li><a href="nuevaBodega.htm">Añadir Listas....</a></li>
                                </ul>
                            </li>
                            <li><a href="global.htm">Global</a></li>

                        </ul>
                        <form:form action="buscarVino.htm" commandName="busquedavino">
                                    <form:input class="inputsearch" path="cadenabusqueda" type="text" valuer="Buscador de vinos..."/>
                        </form:form>
                        
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
                    </div>
                </div> <!-- /fill -->
            </div> <!-- /topbar -->
        </div>
                        <div class="container-principal">
                        <div id="principal">
                           <div class="barra-izquierda">
                               <div class="div-bordes">
  <p><strong>¡Gracias por colaborar! Añadiendo un nuevo vino permitirás a los demas usuarios encontrarlo y dar su opinión. Ademas
  recuerda que por añadir un vino ¡obtienes puntuación!</strong></p>
  </div>
                            <div class="span12 columns">
                                <form:form id="formulario" method="post" class="login" commandName="vino">
                                    <fieldset>
                                        <h2>Introducir datos del vino</h2>
                                        <p>Los campos marcados con * son obligatorios</p>
                                        <c:if test="${error ne ''}"> 
                                            <div class="alert-message error" >
                                                <a class="close" href="#">×</a>
                                                <p>
                                                    <strong>Oh Oh! Error</strong>
                                                    ${error}
                                                </p>
                                            </div>
                                        </c:if> 
                                        
                                        <div id="div1" class="clearfix">
                                            <label for="">Nombre (*):</label>
                                            <div class="input">
                                                <form:input path="nombre" type="text" size="30"  id="input1" class="xlarge"/>
                                                <form:errors path="nombre"></form:errors>
                                            </div>
                                        </div> <!-- /clearfix -->
                                        <div id="div2" class="clearfix">
                                            <label for="">Tipo de vino (*):</label>
                                            <div class="input">
                                                <form:select path="tipovino" id="select1">
                                                    <option>Tinto</option>
                                                    <option>Blanco</option>
                                                    <option>Rosado</option>
                                                    <option>Espumoso</option>
                                                </form:select>
                                                <form:errors path="tipovino"/>
                                            </div>
                                        </div> <!-- /clearfix -->
                                        <div class="clearfix">
                                            <label for="">Variedad de uva:</label>
                                            <div class="input">
                                                <form:input path="variedad" type="text" size="30" id="inputVariedad" class="xlarge" readonly="true"/>
                                                <form:errors path="variedad"/>
                                                <a href="#" id="tags" class="btn large">Tags</a>
                                            </div>

                                            <div id="variedades" class="modal" style="position: relative; top: auto; left: auto; margin: 0 auto; z-index: 1;display : none">
                                                <div class="modal-header">
                                                    <h3>Elije las variedades de uva</h3>
                                                    <a id="cerrar" href="#" class="close">&times;</a>
                                                </div>
                                                <div id ="modal" class="modal-body" style=" height:150px">
                                                    <ul  style="list-style: none outside none">
                                                        <c:forEach var="variedad" items="${listavariedades}">
                                                            <li style="float:left">
                                                                <a href="${variedad.nombreVariedad}" class="btn small pulsar " id="${variedad.nombreVariedad}">${variedad.nombreVariedad}</a>
                                                            </li>
                                                        </c:forEach>
                                                    </ul>
                                                </div>
                                                <div class="modal-footer">
                                                    <a href="#" id="enviar" class="btn primary">Añadir</a>
                                                </div>
                                            </div>

                                        </div> <!-- /clearfix -->
                                        <div class="clearfix">
                                            <label for="">Año(*):</label>
                                            <div class="input">
                                                <form:select path="anio" title="Año vino" name="selectAnio" id="selectAnio" style="text-align:center" class="xlarge">
                                                    <option>1969</option>
                                                    <option>1970</option>
                                                    <option>1971</option>
                                                    <option>1972</option>
                                                    <option>1973</option>
                                                    <option>1974</option>
                                                    <option>1975</option>
                                                    <option>1976</option>
                                                    <option>1977</option>
                                                    <option>1978</option>
                                                    <option>1979</option>
                                                    <option>1980</option>
                                                    <option>1981</option>
                                                    <option>1982</option>
                                                    <option>1983</option>
                                                    <option>1984</option>
                                                    <option>1985</option>
                                                    <option>1986</option>
                                                    <option>1987</option>
                                                    <option>1988</option>
                                                    <option>1989</option>
                                                    <option>1990</option>
                                                    <option>1991</option>
                                                    <option>1992</option>
                                                    <option>1993</option>
                                                    <option>1994</option>
                                                    <option>1995</option>
                                                    <option>1996</option>
                                                    <option>1997</option>
                                                    <option>1998</option>
                                                    <option>1999</option>
                                                    <option>2000</option>
                                                    <option>2001</option>
                                                    <option>2002</option>
                                                    <option>2003</option>
                                                    <option>2004</option>
                                                    <option>2005</option>
                                                    <option>2006</option>
                                                    <option>2007</option>
                                                    <option>2008</option>
                                                    <option>2009</option>
                                                    <option>2010</option>
                                                    <option>2011</option>
                                                </form:select>
                                                <form:errors path="anio"/>
                                            </div>
                                        </div> <!-- /clearfix -->
                                        <div class="clearfix">
                                            <label for="">Zona:</label>
                                            <div class="input">
                                                <form:input path="zona" type="text" size="30"  id="input3" class="xlarge"/>
                                                <form:errors path="zona"/>
                                            </div>
                                        </div> <!-- /clearfix -->
                                        <div class="clearfix">
                                            <label for="">Pais:</label>
                                            <div class="input">
                                                <form:input path="pais" type="text" size="30"  id="input4" class="xlarge"/>
                                                <form:errors path="tipovino"/>
                                            </div>
                                        </div> <!-- /clearfix -->
                                        <div class="clearfix">
                                            <label for="">Codigo de barras:</label>
                                            <div class="input">
                                                <form:input path="barcode" type="text" size="30"  id="input5" class="xlarge"/>
                                                <form:errors path="barcode"/>
                                            </div>
                                        </div> <!-- /clearfix -->
                                        
                                        <div class="clearfix">
                                            <label for="">Bodega productora:</label>
                                            <div class="input">
                                                <form:input path="bodega" type="text" size="30"  id="input6" class="xlarge"/>
                                                <form:errors path="bodega"/>
                                            </div>
                                        </div> <!-- /clearfix -->

                                        <div class="actions">
                                            <!--<input class="btn primary" class="button"  value="Dar de alta" id="boton1"/>-->
                                            <input class="btn primary" id="boton1" type="button" value="Dar de alta"/>
                                        </div>
                                    </fieldset>
                                </form:form>

                            </div>
                           </div>
                            <div id="barra-derecha">
  <div>
  <h3 class="section-title">Ultimos vinos añadidos</h3>
        <ul class="galeria">
            
            <c:forEach var="ultimos" items="${ultimosvinos}">
          <li class="resultados-lista">
              <div class="avatar-lista">
          				<img width="46px" border="0" src="${ultimos.rutaimagen}" heigth="30px"> 
          		  </div>
          		<div>
          		<a class="linkresaltado" href="busquedavinoID.htm?idvino=${ultimos.idvino}">${ultimos.nombre}</a> ${ultimos.anio} ${ultimos.tipovino} 
              <div>Puntuacion: ${ultimos.notamedia} </div>
          		<div>
          		<strong>${ultimos.zona}</strong>
            	</div>
          		</div>
          </li>
            </c:forEach>
            
        </ul>
  
  </div>
  
  <div>
  <h3 class="section-title">Vinos mas votados</h3>
  
          <ul class="galeria">
              <c:forEach var="mayorpuntuacion" items="${maximapuntuacion}">
          <li class="resultados-lista">
              <div class="avatar-lista">
          				<img width="46px" border="0" src="${mayorpuntuacion.rutaimagen}" heigth="30px"> 
          		  </div>
          		<div>
          		<a class="linkresaltado" href="busquedavinoID.htm?idvino=${mayorpuntuacion.idvino}">${mayorpuntuacion.nombre}</a> ${mayorpuntuacion.anio}  ${mayorpuntuacion.tipovino}
              <div>Puntuacion:  ${mayorpuntuacion.notamedia} </div>
          		<div>
          		<strong>${mayorpuntuacion.zona}</strong>
            	</div>
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
  <DIV class="opcionespie">Vineando 2011</DIV>
  <DIV class="opcionespie"><STRONG><a href="mailto:info@vineando.com">Contacto</a></STRONG></DIV>
  </DIV>
                                
                                
                        <script type="text/javascript" src="./javascript/jQuery.js"></script>
                        <script type="text/javascript" src="./javascript/altaVino.js"></script>
                    </body>



                    </html>