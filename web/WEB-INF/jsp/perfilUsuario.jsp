<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="es-ES">
    <head>
        <meta charset="utf-8"></meta>
        <title>Datos de tu cuenta</title>
        <meta name="description" content=""></meta>
        <meta name="author" content=""></meta>
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
                                <a id ="menu" class="menu " href="#">${usuario.alias}</a>
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
                <div class="barra-izquierda">
                    <div >
                        <h3 class="section-title">Datos personales</h3>
                        <form:form id="formulario"  method="post" commandName="usuario" action="cambiarDatosUsuario.htm">
                            <c:if test="${error ne ''}">
                                <div class="alert-message error" >
                                    <a class="close" href="#">×</a>
                                    <p>
                                        <strong>Oh Oh! Error:${error} </strong>

                                    </p>
                                </div>
                            </c:if>
                            <c:if test="${exito ne ''}">
                                <div class="alert-message success" >
                                    <a class="close" href="#">×</a>
                                    <p>
                                        <strong>${exito}</strong>

                                    </p>
                                </div>
                            </c:if>
                            <fieldset>

                                <div id="div1" class="clearfix">
                                    <div class="avatar">
                                        <img id="imagenusuario" src="${usuario.rutaAvatar}"/>
                                    </div>
                                    <div>
                                        <a  id="btnCambiarAvatar" class="btn large primary enabled" href="#">Cambiar avatar</a>
                                    </div>
                                </div> <!-- /clearfix -->
                                <div id="divalias" class="clearfix">
                                    <label for="">Alias:</label>
                                    <div class="input">
                                        <form:input path="alias" type="text" size="30" id="alias" class="xlarge"/>
                                    </div>
                                </div> <!-- /clearfix -->

                                <div class="clearfix">
                                    <label for="">Nombre:</label>
                                    <div class="input">
                                        <form:input path="nombre" type="text" size="30" id="nombre" class="xlarge" />
                                    </div>
                                </div> <!-- /clearfix -->
                                <div class="clearfix">
                                    <label for="">Apellidos:</label>
                                    <div class="input">
                                        <form:input path="apellidos" type="text" size="30"  id="apellidos" class="xlarge" />
                                    </div>
                                </div> <!-- /clearfix -->
                                <div class="clearfix">
                                    <label for="">Tipo de perfil</label>
                                    <div class="input">
                                    <form:select path="tipoperfil" id="selecttipo">
                                        
                                         
                                            <form:option value="publico">Publico</form:option>
                                            <form:option value="privado">Privado</form:option>
                                            


                                    </form:select>
                                    </div>
                                    
                                </div>

                                <div class="actions">
                                    <!--<input class="btn primary" class="button"  value="Dar de alta" id="boton1"/>-->
                                    <input class="btn primary" id="btnactualizar" type="button" value="Guardar cambios"/>

                                </div>
                            </fieldset>
                        </form:form>
                    </div>
                    <div>
                        <c:if test="${errorpassword ne ''}">
                            <div class="alert-message error" >
                                <a class="close" href="#">×</a>
                                <p>
                                    <strong>Oh Oh! Error:${errorpassword} </strong>
                                </p>
                            </div>
                        </c:if>
                        <c:if test="${exitopassword ne ''}">
                            <div class="alert-message success" >
                                <a class="close" href="#">×</a>
                                <p>
                                    <strong>${exitopassword}</strong>
                                </p>
                            </div>
                        </c:if>
                        <h3 class="section-title">Cambiar contraseña</h3>
                        <form:form id="formcambiarpassword" method="post" action="cambiarPassword.htm" commandName="nuevaPassword">
                            <div class="clearfix">
                                <label for="">Contraseña actual:</label>
                                <div class="input">
                                    <form:input type="text" path="passwordold" size="30"   id="passwordold" class="xlarge" placeholder="Introduzca su contraseña actual..."/>
                                </div>
                            </div> <!-- /clearfix -->
                            <div id="divpassword1" class="clearfix">
                                <label for="">Nueva contraseña:</label>
                                <div class="input">
                                    <form:input type="text" path="newpassword" size="30"  id="newpassword" class="xlarge" placeholder="Introduzca su nueva contraseña..."/>
                                </div>
                            </div> <!-- /clearfix -->
                            <div id="divpassword2" class="clearfix">
                                <label for="">Repite contraseña:</label>
                                <div class="input">
                                    <form:input type="text" path="newpassword2" size="30"  id="newpassword2" class="xlarge" placeholder="Repita su nueva contraseña..."/>
                                </div>
                            </div> <!-- /clearfix -->
                            <div class="actions">
                                <!--<input class="btn primary" class="button"  value="Dar de alta" id="boton1"/>-->
                                <input class="btn primary" id="btnpassword" type="button" value="Cambiar contraseña"/>
                            </div>
                        </form:form>
                        <div id="draggable" class="modal ui-widget-content" style="display : none">
                            <div class="modal-header">
                                <h3>Cambia tu imagen!</h3>
                                <a href="#" class="close">&times;</a>
                            </div>
                            <div class="modal-body">
                                <p>Elije la imagen que quieres usar como avatar</p>
                                <form:form commandName="fileUploadForm"  action="fileupload.htm" enctype="multipart/form-data" >
                                    <form  method="POST">
                                        <form:errors path="*" cssClass="errorblock" element="div"/>
                                        <input type="file" name="file" id="file" />
                                        <span>
                                            <form:errors path="file" cssClass="error" />
                                        </span>
                                    </form:form>
                                        
                                    <div class="alert-message warning" data-alert="alert" id="alertTipoNoValido" style="display : none">
                                        <p><strong>Oh no!</strong> x No soportamos ese tipo de imagen, por favor, utiliza una imagen JPG o PNG.</p>
                                    </div>

                                    <div class="modal-footer">
                                        <a href="#" id="enviar" class="btn primary">Cambiar avatar</a>
                                        <a href="#" id="cancelar" class="btn secondary">Cancelar</a>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
                    <div id="barra-derecha">
                        <div>
                            <h3 class="section-title">Cuenta</h3>
                            <p>Desde esta página podras cambiar tu foto de avatar, tu nombre y apellidos además de  tu alias de usuario y tu contraseña.
                                Cambia tu alias de usuario sin preocuparte por los cambios en tus comentarios, perfil, todo se cambiará automaticamente.</p>
                        </div>
                        <div>
                            <h3 class="section-title">Tipo de perfil</h3>
                            <p>Puedes cambiar tu privacidad cambiando el tipo de perfil. Si lo estableces a privado serás tu quien elijas las personas que quieres que te sigan
                            pero si prefieres no tener que preocuparte establecelo a público.</p>
                        </div>
                        <div>
                            <h3 class="section-title">Consejos</h3>
                            <ul>
                                <li>
                                    <p>Si no quieres que tu nombre  y tus apellidos aparezcan reflejados de algun modo en Vineando puedes borrarlos sin que esto afecte
                                        a tu perfil</p>
                                </li>
                                <li>
                                    <p>Elige una foto de usuario que te represente! no tiene porque ser una foto personal.... !elige como quieres que te vean los demas usuarios!</p>
                                </li>
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
            <!--script type="text/javascript" src="http://code.jquery.com/jquery-1.5.2.min.js"></script>-->
            <script type="text/javascript" src="./javascript/perfilUsuario.js"></script>
    </body>
</html>