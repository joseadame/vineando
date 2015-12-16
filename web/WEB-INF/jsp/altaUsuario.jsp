<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="es-ES">  
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
        <title>Alta nuevo usuario</title>  
        <link rel="StyleSheet" type="text/css" href="./css/styles.css" media="all"/> 
    </head>
    <body>  
        <div class="page_top">
            <div id="header">
                <div class="Logotipo">
                    <h1 class="titulo">Vineando</h1>
                    <p>&nbsp;</p>
                </div>
            </div>
        </div>
        <div id="content" class="clearfix">
            <div class="page_width clearfix">
                <div id="main">
                    <div id ="sigup" class="module">
                        <div class="text_block">
                            <h2>Bienvenidos a Vineando</h2>
                            <p>Unete a Vineando y comparte tus vinos, catas con tus amigos!!</p>
                            <p> y... ¿porque no hacer mas amigos conociendo el vino? </p>
                        </div>	
                        <div id="errorMsg" class="errorMsg">
                            <p>${error}</p>
                        </div>
                        <form:form id="formulario" method="post" class="inline_labels lw_80" commandName="usuario">
                            <div class="field_row">
                                <label>Alias:</label>  
                                <form:input path="alias" id="search1" type="text" class="text" value="Alias..." />  
                                <form:errors path="alias"/>
                            </div>
                            <div class="field_row">
                                <label>Nombre:</label>
                                <form:input path="nombre" id="search2" type="text" class="text" value="Nombre..." /> 
                                <form:errors path="nombre"/>
                            </div>
                            <div class="field_row">
                                <label>Apellidos:</label>
                                <form:input path="apellidos" id="search3" type="text" class="text" value="Apellidos..." />
                                <form:errors path="apellidos"/>
                            </div>
                            <div class="field_row">
                                <label>Password:</label>
                                <form:input path="password" id="search4" type="password" class="text" value="Password..." />
                                <form:errors path="password"/>
                            </div>
                            <div class="field_row">
                                <label>Email:</label>
                                <form:input path="email" id="search5" type="text" class="text" value="Email..." /> 
                                <form:errors path="email"/>
                            </div>
                            <div class="field_row">
                                <input id="btnAlta" class="button" type="submit" value="Registrarse"/>
                            </div>
                        </form:form>
                    </div>
                </div>  
            </div>
            <div id="pie">
                <p>Copyright (c) 2013   -   Vineando</p>
            </div> 
        </div>
                        
   <DIV  class="container-principal">
  <DIV class="opcionespie"><STRONG><A href="#">Blog</A></STRONG></DIV>
  <DIV class="opcionespie">Vineando 2013</DIV>
  <DIV class="opcionespie"><STRONG><A href="#">Contacta con nosotros</A></STRONG></DIV>
  </DIV>
        <script type="text/javascript" src="./javascript/jquery.js"/>
        <script type="text/javascript" src="./javascript/jQuery.dPassword.js"/>
        <script type="text/javascript" src="./javascript/main.js" /> 
    </body>  
</html>  
