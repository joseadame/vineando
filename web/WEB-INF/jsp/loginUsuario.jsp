<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es-Mx" lang="es-Mx" />
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<head>
<meta charset="utf-8">
<title>Login</title>
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
<!-- Le javascript -->
</head>
<body>
<div style="z-index: 5;" class="topbar-wrapper">
  <div class="topbar">
    <div class="fill">
      <div class="container">
        <h3><a href="#">Vineando</a></h3>
        <ul>
          <li class="active"><a href="index.htm">Inicio</a></li>
        </ul>
        <form action="">
          <input class="inputsearch" type="text" placeholder="Buscador de vinos...">
        </form>
        
      </div>
    </div>
    <!-- /fill --> 
  </div>
  <!-- /topbar --> 
</div>
<div class="container-principal">
<div id="principal">
  <div class="barra-izquierda">
  
  <div class="span12 columns">
   <h3 class="section-title">Login</h3>
   <form:form id="hacerlogin" action="makeloginUsuario.htm" method="post"  commandName="login">
      <fieldset>
          
          <c:if test="${error ne ''}"> 
                                            <div class="alert-message error" >
                                                <a class="close" href="#">×</a>
                                                <p>
                                                    <strong>Oh Oh! Error:${error} </strong>
                                                    
                                                </p>
                                            </div>
                                        </c:if> 
      <div id="divmaillogin" class="clearfix">
		              <label for="">email:</label>
		              <div class="input">
	                   <form:input path="email" type="text" id ="emaillogin" size="30"   class="large" data-content="Introduce el email con el que te registrastes" data-original-title="Introduce un email valido" />
                  </div>
               </div>
               <div id="divpasswordlogin" class="clearfix">
		              <label for="">Password:</label>
		              <div class="input">
                      <form:input path="password" id ="passwordlogin" type="password" size="30" class="medium"/>
                
                  </div>	
              </div>
        
      </fieldset>
      <div>
       
	     <a href="#" id="btnlogin" class="btn primary" style="float:right">Login</a>
      <div>
    </form:form>
  </div>
  </div>
  </div>
    </div>
  <div id="barra-derecha">
  <div class="separacion-arriba">
 
 
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
<script type="text/javascript" src="./javascript/loginusuario.js"></script>
<script type="text/javascript" src="./javascript/typsy.js"></script>
<script type="text/javascript" src="./javascript/popover.js"></script>



</body>
</html>