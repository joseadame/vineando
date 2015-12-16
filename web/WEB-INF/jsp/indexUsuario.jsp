<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es-Mx" lang="es-Mx" />
    <head>
        <style type="text/css">@import url(./css/docs.css);</style>
        <style type="text/css">@import url(./css/prettify.css);</style>
        <style type="text/css">@import url(./css/bootstrap-1.0.0.css);</style>   
        <link rel="StyleSheet" type="text/css" href="./css/Styles.css" media="all"/>
        <style type="text/css">
            body{
                font-family:Arial;
                font-size:.93em;
            }
            #content-box{
                background-color:#FAFAFA;
                border:2px solid #888;
                height:300px;
                overflow:scroll;
                padding:4px;
                width:500px;
            }
            #content-box p{
                border:1px solid #EEE;
                background-color:#F0F0F0;
                padding:3px;
            }
            #content-box p span{
                color:#00F;
                display:block;
                font:bold 21px Arial;
                float:left;
                margin-right:10px;
            }
        </style>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
        <meta name="description" content=""/>
        <meta name="author" content=""/>
        <title>Perfil de ${UsuarioSesion.alias}</title>
        <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <!-- Le styles -->
        <!--  <style type="text/css">@import url(./css/Styles.css);</style>-->
        <!-- Le javascript -->
        <link rel="stylesheet" href="bootstrap-1.0.0.css" media="all">
    </head>
    <body>
        <div style="z-index: 5;" class="topbar-wrapper">
            <div class="topbar">
                <div class="fill">
                    <div class="container">
                        <h3><a href="#">Vineando</a></h3>
                        <ul>
                            <li class="active"><a href="#">Home</a></li>
                            <li class="menu">
                                <a id="menubodega" href="#">Tus listas</a>
                                <ul id="listabodega" class="menu-dropdown">
                                    <c:forEach var="bodega" items="${listabodegas}">
                                        <li><a href="busquedaBodega.htm?idbodega=${bodega.idbodega}&idusuario=${UsuarioSesion.id}">${bodega.descripcion}</a></li>
                                    </c:forEach>
                                    <li class="divider"></li>
                                    <li><a href="nuevaBodega.htm">Añadir Lista....</a></li>
                                </ul>
                            </li>
                            <li><a href="global.htm">Global</a></li>
                             <li><a href="eventos.htm">Eventos</a></li>
                        </ul>
                        <form:form action="buscarVino.htm" commandName="busquedavino">
                            <form:input class="inputsearch" path="cadenabusqueda" type="text" valuer="Buscador de vinos..."/>
                        </form:form>
                        <ul class="nav secondary-nav">
                            <li class="menu">
                                <a id="menu" class="menu" href="#">${UsuarioSesion.alias}</a>
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
                        <div>
                            <div class="avatar">
                                <img id="imagenusuario" src="${UsuarioSesion.rutaAvatar}">
                            </div>
                            <div>
                                <h2><strong>${UsuarioSesion.alias}</strong><small class="bean"> ${UsuarioSesion.puntuacion} puntos</small>
                            </div>
                            <div id="info-user">
                                <fieldset>
                                    <label>Rango:</label><strong>${UsuarioSesion.rango}</strong>
                                </fieldset>
                            </div>
                            <div>
                                <a href="altaVino.htm" id="boton1" class="btn small">Añadir vino</a>
                            </div>
                        </div>
                    </div>
                    <div  id="actividad">
                        <h3 class="section-title">Actividad reciente</h3>
                        <ul id="listanotificaciones" class="galeria">
                             
                                     
                            <c:forEach var="notificacion" items="${listanotificaciones}">
                                ${notificacion}
                            </c:forEach>
                                     
                                       
                        
                        </ul>
                       
                    </div>
                </div>
                <div id="barra-derecha">
                    <div>
                        <h3 class="section-title">Mis estadisticas</h3>
                        <fieldset>
                            <ul class="galeria">
                                <li class="galeria">
                                    <div class="perfil">
                                        <strong><a href="vinoscatados.htm?idusuario=${UsuarioSesion.id}">Vinos probados:</a> ${probados}</strong>
                                    </div>
                                </li>
                                <li class="galeria">
                                    <div class="perfil">
                                        <strong>Numero de listas: ${numerobodegas}</strong>
                                    </div>
                                </li>
                                <li class="galeria">
                                    <div class="perfil">
                                        <strong><a href="verwishlist.htm?idusuario=${UsuarioSesion.id}">Wishlist:</a> ${wishlist}</strong>
                                    </div>
                                </li>
                            </ul>
                        </fieldset>
                    </div>
                    <div>
                        <h3 class="section-title">Siguiendo</h3>
                        <ul id="usuarios" class="galeria">
                            <c:forEach var="amigo" items="${listaamigos}">
                                <li class="imagen"><a href="busquedausuario.htm?idusuario=${amigo.id}"><img src="${amigo.rutaAvatar}" title="${amigo.alias}" width="40px" height="40px"/></a></li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div>
                        <h3 class="section-title">Notificaciones</h3>
                       
                        <ul class="galeria">
                            <c:forEach var="solicitud" items="${listasolicitudes}">
                                <li class="resultados-lista">
                                    <div class="avatar-lista">
                                        <img width="46px" border="0" src="${solicitud[0].rutaAvatar}" heigth="46px">
                                    </div>
                                    <div id="avatar" class="details">
                                        <a id="${solicitud[0].id}" class="linkresaltado solicitud" data-original-title="Mensaje enviado" data-content="${solicitud[1]}" href="#">${solicitud[0].alias}</a>
                                        <div>quiere empezar a seguirte.</div>
                                        <div>
                                            <a  id ="aceptarsolicitud" href="#" class="btn small">Aceptar</a>
                                            <a id="ignorarsolicitud" href="#" class="btn small">Ignorar</a>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                                             
                    </div>
                    <div>
                        <ul id="usuarios" class="galeria">
                            <h3 class="section-title">Sugerencias de amistad</h3>
                            <c:forEach var="amigosugerido" items="${listaamigossugeridos}">
                                <li class="imagen"><a href="busquedausuario.htm?idusuario=${amigosugerido[1]}"><img src="${amigosugerido[2]}" title="${amigosugerido[0]}" width="40px" height="40px"/></a></li>
                            </c:forEach>
                        </ul>
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
        <script type="text/javascript" src="./javascript/infoUsuario.js"></script>
        <script type="text/javascript" src="./javascript/typsy.js"></script>
        <script type="text/javascript" src="./javascript/popover.js"></script>
    </body>
</html>
