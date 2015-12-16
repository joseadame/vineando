<?xml version="1.0" encoding="utf-8"?>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es-Mx" lang="es-Mx" >
    <head>
        <meta charset="utf-8">
        <title>Informacion de ${Vino.nombre}</title>
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <!-- CSS styles -->
        <link rel="StyleSheet" type="text/css" href="./css/docs.css" media="all"/>
        <link rel="StyleSheet" type="text/css" href="./css/prettify.css" media="all"/>
        <link rel="StyleSheet" type="text/css" href="./css/puntuacion.css" media="all"/>        
        <link rel="StyleSheet" type="text/css" href="./css/jquery-ui-1.8.16.custom.css" media="all"/>        
        <link rel="StyleSheet" type="text/css" href="./css/jquery.lightbox-0.5.css" media="all"/>        
        <link rel="StyleSheet" type="text/css" href="./css/bootstrap-1.0.0.css" media="all"/>
        <link rel="StyleSheet" type="text/css" href="./css/Styles.css" media="all"/>       
    </head>
    <body>
        
        <div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/es_ES/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
        
        
        
        
        
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
            <c:if test="${error ne ''}"> 
                                            <div id="mensajeinfo"class="alert-message error" >
                                                <a id="xcerrar"class="close" href="#">×</a>
                                                <p>
                                                    <strong>Oh Oh! Error:${error} </strong>
                                                    
                                                </p>
                                            </div>
          
         </c:if>                                             
            <c:if test="${checkin eq 'limitealcanzado'}"> 
                                            <div id="mensajeinfo" class="alert-message success" >
                                                <a id="xcerrar"class="close" href="#">×</a>
                                                <p>
                                                    <strong>¿No crees que estas bebiendo demasiado?</strong>
                                                    
                                                </p>
                                            </div>
                                        </c:if> 
            
            
            <c:if test="${listaacciones.size() > 0}">
                
                   
                 <c:forEach var="accion" items="${listaacciones}">
                                    <div id ="mensajeacciones" class="alert alert-success">
                                         <a id="cerraralert" class="close" href="#">×</a>
                                         
                                           
                                           <div class="imagen-logro">
                                               <img id="imagenusuario" width="55" height="55" src="images/badge.png">

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
                
                
                     
                 
                
                
                
                
                
                
                
                
   <div id="content-header">
       
    
       
       
       <div class="avatar" style="position:relative;">
           <div id="img_grande" style="position:absolute; display:none;" onmouseout="$('#img_grande').hide()">
               <img id="imagenusuariogrande" src="${Vino.rutaimagen}" style="height: 150%; width: 150%;top:0px; left:0px;box-shadow: 0 1px 5px 0 #CCCCCC;padding: 6px;background-color: white;"/>
           </div>
               <img id="imagenusuario" src="${Vino.rutaimagen}" onmouseover="$('#img_grande').show();"/>
           
       </div>
       <div style="float:left;border-right: 1px solid #E3E3E3;padding-right:10px;width:461px" >
           
            <h1>${Vino.nombre} <small>${Vino.anio}</small></h1>
            
         <fieldset class="separacionarriba">
          <div class="campotexto">
            <label for="">Tipo de vino:</label>
            <span >${Vino.tipovino}</span>
          </div> <!-- /clearfix -->
          <div class="campotexto">
            <label for="">Variedad de uva:</label>
            <span id="eligeuva" contenteditable="false">${Vino.variedad}</span>
            <input id="ocultoidvino" type="hidden" value="${Vino.idvino}"/>

           
            <div id="act_variedad" style="display:none">
            <div id="ventana_variedades" class="modal" style="top: auto; left: auto; margin: 0 auto; z-index: 1;display : none">
            <div class="modal-header">
              <h3>Variedades de uva</h3>
              <a id="cerrar" href="#" class="close">&times;</a> </div>
            <div id ="modal" class="modal-body" style=" height:150px">
              <ul  style="list-style: none outside none">
                <li style="float:left"> <a href="Tempranillo" class="btn small pulsar " id="Tempranillo">Tempranillo</a> </li>
                <li style="float:left"> <a href="Garnacha" id="Garnacha" class="btn small pulsar">Garnacha</a> </li>
                <li style="float:left"> <a href="Syrah" id="Syrah" class="btn small pulsar">Syrah</a> </li>
                <li style="float:left"> <a href="Cavernet Sauvignon" id ="Cavernet Sauvignon " class="btn small pulsar">Cavernet Sauvignon</a> </li>
                <li style="float:left"> <a href="Merlot" id ="Merlot" class="btn small pulsar">Merlot</a> </li>
                <li style="float:left"> <a href="Pedro Ximenez" id ="Pedro Ximenez" class="btn small pulsar">Pedro Ximenez</a> </li>
                <li style="float:left"> <a href="Variedad1" id ="Variedad1" class="btn small pulsar">Variedad1</a> </li>
                <li style="float:left"> <a href="VariedadPrueba" id ="Variedad Prueba" class="btn small pulsar">Variedad Prueba</a> </li>
              </ul>
            </div>
            <div class="modal-footer" > <a href="#" id="enviarvariedad" class="btn primary">Añadir</a> </div>
          </div>

            </div>
          </div> <!-- /clearfix -->
          <div class="campotexto">
            <label for="">Zona:</label>
            <span id="zona" contenteditable="false">${Vino.zona}</span>
          </div> <!-- /clearfix -->
          <div class="campotexto">
            <label for="">Pais:</label>
            <span id="pais" contenteditable="false">${Vino.pais}</span>
            
          </div> <!-- /clearfix -->
          <div class="campotexto">
            <label for="">Codigo de barras:</label>
            <span id="barcode" contenteditable="false">${Vino.barcode}</span>
          
           
          </div> <!-- /clearfix -->
          
        </fieldset>
           <div style="float:left" class="estadistica">
                <strong>${checkins}</strong>
                 <strong>Catas</strong>
     
     
            </div>
           <div class="estadistica" style="float:left;width:85px">
               <strong>${almacenado}</strong>
               <strong>WishList</strong>
               
               
           </div>
            <div class="estadistica" style="float:left;width:150px">
               <strong>Puntuacion:</strong>
               <strong>${Vino.notamedia}/5</strong>
               <div>
               <strong>${Vino.numerovotaciones} votaciones</strong>
               </div>
               
               
            </div>
           
           
           
       </div>
               
      
       <div style="float:right;width:300px" >
     <h3 class="section-title">Perfil del vino</h3>
          <div class="input">          	           
            <div id="progressbar"></div>           
          </div>
          <h4 id="completado">${porcentajeCompletado}% perfil completo</h4>
          
     
     
     </div>
          
     <div style="float:right;width:300px">
     <h3 class="section-title">Bodega productora</h3>
     <fieldset class="separacionarriba">
          <div id="div2" class="clearfix">
            <label for="">Bodega:</label>
            <c:if test="${Vino.bodega ne ''}">
            <h4>${Vino.bodega}</h3>
            </c:if>
            <c:if test="${Vino.bodega eq ''}">
               <h4>No se ha proporcionado la bodega</h3>   
            </c:if>
          </div> <!-- /clearfix -->
          <div class="clearfix">
            <label for="">Ciudad:</label>
            <h4></h3>
          </div> <!-- /clearfix -->
           <div class="clearfix">
            <label for="">Pais:</label>
            <h4></h3>
          </div> <!-- /clearfix -->
          <div class="clearfix">
            <label for="">Perfil de la bodega:</label>
            <c:if test="${Vino.bodega ne ''}">
            <h4><a href="#">Pulse aqui para obtener el contacto</a></h3>
            </c:if>
          </div> <!-- /clearfix -->
         
     
     </div>
          
      <div class="notacata" style="float:left">
       
        
        <div class="info">
        <c:if test="${Vino.comentariocata ne null}">
        <div> <blockquote>
        <p>${Vino.comentariocata}</p>
        </blockquote>
        </div>
        </c:if>
        </div>
        
        </div>
                                 
  
   <nav class="barranavegacion">
   <c:if test="${usuario ne null}">
   <ul class="galeria">
    <li class="menu-item">
      <a  id ="cata" class="btn small primary enabled" href="checkingVino.htm?idvino=${Vino.idvino}">Lo he probado</a>
    <li>
    <li class="menu-item">
    <a id="boton1" class="btn small primary enabled" href="#">Añadir a lista..</a>
    </li>
    <li class="menu-item">
    <a id="whishlist" class="btn small primary enabled" href="#">Wishlist</a>
    </li>
        <li class="menu-item">
         <div class="fb-like" data-href="http://beta.vineando.com/busquedavinoID.htm?idvino=${Vino.idvino}" data-send="true" data-layout="button_count" data-width="450" data-show-faces="true" data-font="tahoma"></div>
        </li>
    <li class="botones-accion">
    <button id="botonreportar" class="acciones" >Reportar error</button>
    <c:if test="${porcentajeCompletado ne 100}">
        <button id="editar" class="acciones">Editar informacion</button>
        <button id="guardar" class="acciones" style="display:none">Guardar</button>
        <button id="cancelar" class="acciones" style="display:none">Cancelar</button>
    </c:if>
    </li>
    
   </c:if>
   </ul>
   </nav>
     
  
    </div>
     <div id="informacion" class="barra-izquierda">
  
         
                    
        <div id="comentario">
            <h3 class="section-title">Escribe tu comentario</h3>
            <fieldset>
            <form:form id="enviarcomentario" method="post" action="enviarcomentario.htm?idvino=${Vino.idvino}" commandName="comentario">
            <form:textarea id="textocomentario" path="comentario" class="xxlarge"  name="textarea" placeholder="Introduzca un comentario..." data-content="El comentario no puede estar vacio" data-original-title="Introduce un comentario para ${Vino.nombre}"></form:textarea>
            </form:form>


            <a id="btnenviarcomentario" class="btn large primary enabled" href="">Enviar comentario</a>
            </fieldset>
        
        </div>
            
            <ul class="tabs" data-tabs="tabs">
                <li class="active">
                    <a href="#comentarios">Comentario de los usuarios</a>
                </li>
                <li class="">
                    <a href="#destacados">Comentarios destacados</a>
                </li>
            </ul>
            
        
            <div class="tab-content" id="mi-tab">
     
                
                
                
                
     <div id="comentarios" class="active">
        <div>
          <h3 class="section-title">Comentario de los usuarios</h3>
          <fieldset>
        <ul class="galeria">
            
       <c:forEach var="comentario" items="${listacomentarios}">    
        <div id="${comentario.idcomentario}" class="perfil" data-content="Has sumado 1 punto a este comentario" data-original-title="+1">
        <li>
        <div class="img">
         <img width="40px" height="40px" src="${comentario.rutaAvatar}">
        
        </div>
        <div class="info">
        <div id="cabecera-usuario">
        <a href="busquedausuario.htm?idusuario=${comentario.idusuario}">${comentario.usuario}</a>
        <small>${comentario.fecha}</small>
        <c:if test="${usuario ne null}">
        <img id="megusta" alt="Me gusta!" title="¡Me gusta!" src="images/thumb-up.png" style="float:right" class="btn small pulsar "> 
        <img id="nomegusta" width="25px" height="25px" alt="Reportar comentario" title="No me gusta" src="images/thumb_down.png" style="float:right" class="btn small pulsar ">
        </c:if>
        </div>
        <div class="comentario">
        <p>${comentario.comentario}</p>
        </div>
        <div>
        <h4 id="puntuacion"></h4>
       
        </div>
        
         <h4><strong>${comentario.puntuacion}</strong> puntos (de un total de ${comentario.numerovotaciones} votaciones )</h4>
        </div>
        </li>
        </div>
       </c:forEach>
        
        
        </ul>
        </fieldset>
        
        </div>
        </div>
        <div class="" id="destacados">
        
          <ul class="galeria">
        <c:forEach var="comentariovotado" items="${listacomentariosvotados}">    
        <div class="perfil">
        <li>
        <div class="img">
         <img width="40px" height="40px" src="images/avatar.png">
        
        </div>
        <div class="info">
        <div id="cabecera-usuario">
        <a href="#">${comentariovotado.usuario}</a>
        <small>${comentariovotado.fecha}</small>
        
        <div>
        <strong>${comentariovotado.puntuacion} puntos (de un total de ${comentariovotado.numerovotaciones} votaciones ) </strong>
        <div>
        </div>
        <div>
        <p>${comentariovotado.comentario}</p>
        </div>
        </div>
        </div></div>
        
        </li>
        </div>
        </c:forEach>
        </ul>
        
        </div>
        </div>
            
            
            
            
            
            
            
            
            
            
            
            
            
        
     
        
        
     </div> 
           
      <div id="barra-derecha">
     <div id="galeria">
     <h3 class="section-title">Galeria de imagenes</h3>
     <ul id="gallery" class="galeria">
         <c:if test="${listamedia eq null}"> 
             
             <p>No exiten imagenes de este vino</p>
         </c:if>
         <c:forEach var="imagenvino" items="${listaMedia}">
         
         <li class="imagen"><a href="${imagenvino.rutaavatar}"><img src="${imagenvino.rutaavatar}" width="40px" height="40px"/></a></li>
         </c:forEach>
     
     </ul>
     
     
     </div>
    
          <div id="galeria">
              <h3 class="section-title">Sommelier</h3>
              <ul class="galeria">
                  <c:if test="${usuarioSommelier eq null}">                   
                      <p>Conviertete en el Sommelier de este vino. Haz una cata!</p>
                  </c:if>
                  <c:if test="${usuarioSommelier ne null}">                   
                      <li class="imagen">
                          <a href="busquedausuario.htm?idusuario=${usuarioSommelier.id}">
                              <img width="40px" height="40px" src="${usuarioSommelier.rutaAvatar}" title="${usuarioSommelier.alias}">
                              <span>${usuarioSommelier.alias}</span>
                          </a>
                      </li>
                  </c:if>                  
              </ul>
          </div>
          <div id="galeria">
              <h3 class="section-title">Han probado este vino...</h3>
              <ul class="galeria">
                  <c:if test="${listadocheckinusuarios.size() == 0}">
                      <p>Nadie ha probado este vino aun..se tu el primero</p>
                  </c:if>
                  <c:forEach var="checkins" items="${listadocheckinusuarios}">
                      <li class="imagen"><a href="busquedausuario.htm?idusuario=${checkins.id}"><img width="40px" height="40px" src="${checkins.rutaAvatar}" title="${checkins.alias}"></a></li>
                      </c:forEach>
              </ul>
          </div>
     
     
     </div>  
    
    <div id="reportarerror" class="modal ui-widget-content" style="display:none">
          <div class="modal-header">
            <h3>Reportar informacion incorrecta</h3>
            
          </div>
          <div class="modal-body">
            <p>Elije el motivo por el cual quieres reportar el error</p>
             <form:form id="enviarreporte" method="post" action="reportar.htm?idvino=${Vino.idvino}" commandName="reporteerror">
            <fieldset>
           
            <div class="clearfix">
                <form:select path="motivo" id="select1">
                <option value="Información no adecuada">Información no adecuada</option>
                <option value="Información incorrecta">Información incorrecta</option>
                <option value="Otro">Otro</option>
            
                </form:select>
            </div>
            <p>Añade un comentario</p>
            <div class="clearfix">
            <form:textarea  path="descripcion" class="large" name="textarea"></form:textarea>
            </div>
            
            
          
            </fieldset>
              </form:form>
          </div>

          <div class="modal-footer">
            <a href="#" id="enviarerror" class="btn primary">Reportar</a>
            <a href="#" id="cancelarerror" class="btn secondary">Cancelar</a>
          </div>
        </div>
          
            </div>
    
    
    
   
        </div>
   

    
    
            <div id="draggable" class="modal ui-widget-content" style="display : none">
                <div class="modal-header">
                    <h3>Añadir vino a Bodega</h3>
                    <a href="#" class="close">&times;</a>
                </div>
                <div class="modal-body">
                    <p>Elije la bodega en la que quieras añadir el vino</p>
                    <form:form id="vinoabodega" method="post" commandName="bodega" action="altavino.htm?idvino=${Vino.idvino}">
                        <form:select path="idbodega" id="select1">
                            <c:forEach  var="varbodega" items="${listabodegas}">   
                                <option value="${varbodega.idbodega}">${varbodega.descripcion}</option>
                            </c:forEach>
                        </form:select>
                    </form:form>
                </div>

                <div class="modal-footer">
                    <a href="#" id="enviar" class="btn primary">Añadir vino</a>
                    <a href="#" id="cancelarlista" class="btn secondary">Cancelar</a>
                </div>
            </div>
          
           <DIV  class="container-principal">
  <DIV class="opcionespie"><STRONG><A href="#">Blog</A></STRONG></DIV>
  <DIV class="opcionespie">Vineando 2013</DIV>
  <DIV class="opcionespie"><STRONG><a href="mailto:info@vineando.com">Contacto</a></STRONG></DIV>
  </DIV>
    
    
         
            
           

        <!-- Javascript Frameworks -->
        <script type="text/javascript" src="./javascript/jQuery.js"></script>
        <script type="text/javascript" src="./javascript/infoVino.js"></script>
        <script type="text/javascript" src="./javascript/sistemaPuntuacion.js"></script>
        <script type="text/javascript" src="./javascript/jquery-ui-1.8.16.custom.min.js"></script>
        <script type="text/javascript" src="./javascript/jquery.simplemodal.js"></script>
        <script type="text/javascript" src="./javascript/jquery.lightbox-0.5.js"></script>
        <script type="text/javascript" src="./javascript/tabs.js"></script>
        <script type="text/javascript" src="./javascript/typsy.js"></script>
        <script type="text/javascript" src="./javascript/popover.js"></script>
        <!-- End Javascript Frameworks -->
    </body>
</html>