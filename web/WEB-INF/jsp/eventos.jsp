<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es-Mx" lang="es-Mx" /> <head>
    <meta charset="utf-8">
        <title>Tempranillo, tu red social de vinos!</title>
        <meta name="description" content="">
            <meta name="author" content="">
                <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
                <!--[if lt IE 9]>
                  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
                <![endif]-->
                <!-- Le styles -->
                <link href="docs.css" rel="stylesheet">
                    <link href="prettify.css" rel="stylesheet">
                        <!-- Le javascript -->
                        <link rel="stylesheet" href="./css/bootstrap-1.0.0.css" media="all">
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
                                    <c:if test="${error ne ''}">
                                        <div class="alert-message error" >
                                            <a class="close" href="#">×</a>
                                            <p>
                                                <strong>Oh Oh! Error:${error} </strong>
                                            </p>
                                        </div>
                                    </c:if>
                                    <div id="principal">
                                        <div id="informacion" class="barra-izquierda">
                                            <div class="span12 columns">
                                                <form method="post">
                                                    <fieldset>
                                                        <h1>Eventos y catas</a></strong></h1>
                                                        <h3>Buscador de eventos</h3>
                                                        <div id="div1" class="clearfix">
                                                            <label for="">Ciudad:</label>
                                                            <div class="input">
                                                                <input type="text" size="30"  id="input1" class="xlarge">
                                                            </div>
                                                        </div> <!-- /clearfix -->
                                                        <div class="actions">
                                                            <!--<input class="btn primary" class="button"  value="Buscar" id="boton1"/>-->                                                            
                                                            <form:form id="vinoabodega" method="post" commandName="busquedaEvento" action="eventosCiudad.htm">
                                                                <input class="btn primary" id="btnBuscar" type="button" value="Buscar"/>
                                                            </form:form>
                                                        </div>
                                                    </fieldset>
                                                </form>
                                            </div>
                                            <div id="tabla-bodegas">
                                                <h2></h2>
                                                <ul id="itemContainer" class="galeria">
                                                    <c:forEach var="evento" items="${listaeventos}">
                                                        <li class="resultados-lista">
                                                            <div class="avatar-lista">
                                                                <img border="0" width="150px" heigth="150px" src="images\copavino.png">
                                                            </div>
                                                            <div class="details">
                                                                <a href="descEvento.htm?idevento=${evento[0].idevento}" class="linkresaltado">${evento[0].titulo}</a>
                                                                <div>${evento[1].direccion}</div>
                                                                <div>
                                                                    <strong>${evento[0].descripcion}</strong>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                        <div id="barra-derecha">
                                            <div>
                                                <h3 class="section-title">Eventos recomendados</h3>
                                                <c:if test="${listaevtpatrocinados.size() < 0}">
                                                    <strong>  No existen eventos recomendados </strong>
                                                </c:if>
                                                <c:if test="${listaevtpatrocinados.size() > 0}">
                                                    <ul id="itemContainer" class="galeria">
                                                        <li class="resultados-lista">
                                                            <div class="avatar-lista">
                                                                <img border="0" width="150px" heigth="150px" src="images\copavino.png">
                                                            </div>
                                                            <div class="details">
                                                                <a href="descEvento.htm?idevento=${evento[0].idevento}" class="linkresaltado">Cata Huno Matanegra en la Bodeguita</a>
                                                                <div>C\ Hernan cortes 57</div>
                                                                <div>
                                                                    <strong>Anunciamos cata del vino Huno matanegra en la bodeguita. El sabado a paratir de las 16.00 te esperamos. Precio por persona 20 euros.</strong>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <script type="text/javascript" src="./javascript/jQuery.js"></script>
                                <!--script type="text/javascript" src="http://code.jquery.com/jquery-1.5.2.min.js"></script>-->
                                <script type="text/javascript" src="./javascript/listadobodegasUsuario.js"></script>
                            </body>
                            </html>