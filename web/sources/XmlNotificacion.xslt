<?xml version="1.0" encoding="UTF-8"?>
<!-- edited with XMLSpy v2009 sp1 (http://www.altova.com) by . (.) -->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions">
	<xsl:template match="/">
		<li class="resultados-lista">
			<xsl:for-each select="notificaciones/notificacion">
				
					<xsl:choose>
						<xsl:when test="@tipoNotificacion = 'CHECKIN'">
                                                    <div class="avatar-lista">
                                                        <img border="0" width="46px" heigth="46px">
                                                                <xsl:attribute name="src"><xsl:value-of select="infovino/rutaimagen"/></xsl:attribute>
                                                        </img>
                                                    </div>
                                                     <div>
							<strong>
								<xsl:value-of select="@fecha"/>
							</strong>
                                                     </div>
				<div class="details">
					<a>
						<xsl:attribute name="href">busquedausuario.htm?idusuario=<xsl:value-of select="infousuario/idusuario"/></xsl:attribute>
						<xsl:value-of select="infousuario/alias"/>
					</a>
			 ha catado el vino 			
                                        <a>
								<xsl:attribute name="href">busquedavinoID.htm?idvino=<xsl:value-of select="infovino/idvino"/></xsl:attribute>
								<xsl:value-of select="infovino/nombre"/>
                                        </a>
			               de <xsl:value-of select="infovino/anio"/>. 
                                                <div>
							<strong>
								<xsl:value-of select="infovino/zona"/>
							</strong>
                                            </div>
                                            
                                </div>
						</xsl:when>
						<xsl:when test="@tipoNotificacion = 'ALTAVINO'">
                                                     <div class="avatar-lista">
					<img border="0" width="46px" heigth="46px">
						<xsl:attribute name="src"><xsl:value-of select="infovino/rutaimagen"/></xsl:attribute>
					</img>
				</div>
                                                     <div>
							<strong>
								<xsl:value-of select="@fecha"/>
							</strong>
                                                     </div>
				<div class="details">
					<a>
						<xsl:attribute name="href">busquedausuario.htm?idusuario=<xsl:value-of select="infousuario/idusuario"/></xsl:attribute>
						<xsl:value-of select="infousuario/alias"/>
					</a>
			 ha dado de alta el vino 			
			<a>
								<xsl:attribute name="href">busquedavinoID.htm?idvino=<xsl:value-of select="infovino/idvino"/></xsl:attribute>
								<xsl:value-of select="infovino/nombre"/>  
			</a>
						de <xsl:value-of select="infovino/anio"/>.
			<div>
							<strong>
								<xsl:value-of select="infovino/zona"/>
							</strong>
			</div>
                                </div>
						</xsl:when>
						<xsl:when test="@tipoNotificacion = 'LOGROCONSEGUIDO'">
                                                              <div class="avatar-lista">
					<img border="0" width="46px" heigth="46px">
						<xsl:attribute name="src">images/badge.png</xsl:attribute>
					</img>
				</div>
                                <div>
							<strong>
								<xsl:value-of select="@fecha"/>
							</strong>
                                </div>
                                <div class="details">
                                                    <a>
						<xsl:attribute name="href">busquedausuario.htm?idusuario=<xsl:value-of select="infousuario/idusuario"/></xsl:attribute>
						<xsl:value-of select="infousuario/alias"/>
                                                    </a>
			 ha conseguido el Logro:  			
                                                    <strong>
								<xsl:value-of select="infologro/nombrelogro"/>
                                                    </strong>
							
			
			
                                </div>
						</xsl:when>
                                                <xsl:when test="@tipoNotificacion = 'SEGUIMIENTOAMIGO'">
                                                    <div class="avatar-lista">
					<img border="0" width="46px" heigth="46px">
						<xsl:attribute name="src">images/friends.png</xsl:attribute>
					</img>
                                                    </div>
                                                    <div>
							<strong>
								<xsl:value-of select="@fecha"/>
							</strong>
                                                     </div>
                                                    
                                                         <div class="details">
                                                            <a>
								<xsl:attribute name="href">busquedausuario.htm?idusuario=<xsl:value-of select="infousuario/idusuario"/></xsl:attribute>
								<xsl:value-of select="infousuario/alias"/>
                                                            </a>
							 ha empezado a seguir a                        <a> <xsl:attribute name="href">busquedausuario.htm?idusuario=<xsl:value-of select="infousuariodestino/idusuario"/></xsl:attribute>
                                                                                                           <xsl:value-of select="infousuariodestino/alias"/>
                                                                                                       </a>

                                                         </div>                                             
                                                         
						</xsl:when>
						<xsl:otherwise>
							<td>ERRORR</td>
						</xsl:otherwise>
						
					</xsl:choose>
			
			</xsl:for-each>
		</li>
	</xsl:template>
</xsl:stylesheet>
