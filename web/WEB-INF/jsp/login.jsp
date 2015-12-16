<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es-Mx" lang="es-Mx" />
<head>
    <meta charset="utf-8">
    <title>Vineando. Tu red social de vinos</title>
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



  <BODY>
      
      
      <script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
      
      <div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/es_LA/all.js#xfbml=1&appId=240647595985978";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
      
      
  <DIV style="z-index: 5;" class="topbar-wrapper">
    <DIV class="topbar">
      <DIV class="fill">
        <DIV class="container">
          <H3><A href="index.htm">Vineando</A></H3>
          <UL>

            <LI id="login"><A href="loginusuario.htm">Iniciar Sesion</A></LI>
            <LI id="registro"><A href="registro.htm">Crea una cuenta</A></LI>
            
          </UL>
          <form:form action="buscarVino.htm" commandName="busquedavino">
             <form:input class="inputsearch" path="cadenabusqueda" type="text" valuer="Buscador de vinos..." placeholder="Nombre del vino.."/>
          </form:form>
        </DIV>
      </DIV> <!-- /fill -->

    </DIV> <!-- /topbar -->
  </DIV>
  
      
      <div id="cookiebar">
          <span>
            Vineando  usa cookies para mejorar la experiencia de usuario.
            <a href="#" id="cerrar_cookie" class="btn large">Cerrar</a>
          </span>
          
          
      </div>
      
      
      
      
  <DIV class="container-principal">


   
 
  
 
  

  <DIV id="texto1">
  <H1 class="section-title">¿Que es?</H1>
  <H3>Tu red social del mundo del vino</H3>

  <IMG class="avatar" src="images/copavino.png" width="70px" height="70px" />
  <P>Vineando es una red social que gira
  entorno al mundo del vino.
  Busca tus vinos preferidos, añadelos,
  encuentra a tus amigos
  y comparte con ellos tu pasion por el vino.
  Podras incluso crear tus propias listas de vinos,
  ..para ¡guardarlos y compartirlas!
  ¡El mundo del vino a tu alcance!</P>
        
  </DIV>
  
  
  
  <DIV id="imagen">

  <IMG src="images/grapes_Cartoonizer_1.jpg" height="289" width="444" style="float:right"/>
  
  </DIV>
  
 
    

 <DIV style="float:left">
  <DIV id="texto2" class="parrafos">

  <H1 class="section-title">!Comparte!</H1>
  <H3>Con tus amigos...</H3>
  <IMG class="avatar" src="images/avatar.png" width="70px" height="70px" />
  <P>¡Busca tus vinos preferidos! y si no
    los encuentras añadelos!.
    Deja tu nota de cata sobre el vino y 
    haz que la vean tus amigos,
  te sorprenderá las diferentes opiniones 
  sobre un vino...
  Puntua el vino y expresa lo que te ha parecido.
  Crea tus propias listas de vinos..
  vinos que te gustan..
  vinos que tienes en tu bodega...</P>
  </DIV>
  
  <DIV id="texto3" class="parrafos">
   <H1 class="section-title">!Descubre!</H1>

  <H3>Un mundo de posibilidades</H3>
  <IMG class="avatar" src="images/lupa.png" width="70px" height="70px" />
 <P>Sigue a tus amigos y mira que vinos prueban...
  Con el sistema de recomendaciones
  encontraras vinos
  de tu agrado mas facilmente. 
  Explora las listas de tus amigos para ver
  que vinos almacenan ellos
  y su opinion.
  ¡Crea catas virtuales e invita a tus amigos!</P>
  </DIV>
  </DIV>
  <DIV>
  <DIV id="texto4" class="parrafos">
  <H1 class="section-title">!Aumenta tu reputación!</H1>

  <H3>Sistema de puntuación</H3>
  <IMG class="avatar" src="images/trofeo.png"/>
  <P>Mide tu actividad dentro de la red social con el sistema de puntuaciones y logros. Tanto si das de alta vinos
 ,como si compartes valoraciones o realizas catas obtendras puntuación, ¡descubre la cantidad de logros que puedes conseguir!</P>
  </DIV>
  
  <DIV id="texto5" class="parrafos">
  <H1 class="section-title">!El vino en tus manos!</H1>
  <H3>Aplicación para moviles</H3>

  <IMG class="iconotexto" src="images/iconoapple.png"/>
  <IMG class="iconotexto" src="images/iconoandroid.png"/>
  <P>Proximamente estes donde estes siempre podras dar a conocer tu opinion sobre un vino, podras añadir un vino que has probado, tomar una foto y compartirlo
  con tu red de amigos en Vineando.</P>
  </DIV>
  </DIV>
  <DIV class="signup">
    <A href="registro.htm" id="signup" class="btn large">Date de alta</A>
  
  </DIV>

  




  </DIV>
  
   <DIV class="container-principal">
    <DIV class="bordes">
      <DIV>
      <H1 class="section-title">Ultimos vinos añadidos</H1>
      <DIV class="button-next">
		<A href="javascript:stepcarousel.stepBy('carousel', 1)"><IMG src="images/next.png" width="40px" heigth="40px" /></A>
      </DIV>

    <DIV class="button-prev">
		  <A href="javascript:stepcarousel.stepBy('carousel', -1)"><IMG src="images/previous.png" width="40px" heigth="40px" /></A>
    </DIV>
      <DIV id="carousel" class="stepcarousel">
	
          	<DIV class="belt">
          	
                    <c:forEach var="vino" items="${listanuevosvinos}"> 
          		<DIV class="panel">
          		<DIV class="panel-text">
                    		<A class="linkresaltado" href="busquedavinoID.htm?idvino=${vino.idvino}">${vino.nombre}</A> ${vino.anio} ${vino.tipovino} 
                        <DIV>Puntuacion:  ${vino.notamedia} </DIV>

                    		<DIV>
                    		<STRONG>${vino.zona}</STRONG>
                      	</DIV>
                    		</DIV>
          		</DIV>
          		
                   </c:forEach>
          		
          		
          			
          			
          		
              
              
              </DIV>
		
			
	 </DIV>
      
      </DIV>

    
    </DIV>
  
  
  </DIV>
  
  
  <DIV id="piefooter">
  <DIV class="opcionespie">
     <A href="#">Blog</A>
      
  </DIV>
      <div class="opcionespie">
          <a href="https://twitter.com/Vineando_App" class="twitter-follow-button" data-show-count="false" data-lang="en">Follow @vineando_app</a>
          
      </div>
      <div class="opcionespie">
         <a href="mailto:info@vineando.com">Contacto</a>
          
      </div>
      <div class="opcionespie">
          
          <div class="fb-like" data-href="http://beta.vineando.com" data-width="50" data-height="100" data-colorscheme="light" data-layout="standard" data-action="like" data-show-faces="true" data-send="true"></div>
      </div>
      
  <DIV style="float:right;margin-right: 10px">Vineando 2013</DIV>
 
  </DIV>

    

   
   <script type="text/javascript" src="./javascript/jQuery.js"></script>
   <script type="text/javascript" src="./javascript/carousel.js"></script>
   <!--script type="text/javascript" src="http://code.jquery.com/jquery-1.5.2.min.js"></script>-->
   <script type="text/javascript" src="./javascript/login.js"></script>
   

  
  </BODY>


  
  </html>