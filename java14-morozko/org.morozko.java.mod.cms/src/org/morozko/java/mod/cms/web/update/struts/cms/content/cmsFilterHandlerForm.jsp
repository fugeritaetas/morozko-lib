<%--+
	|
	|	@(#)cmsFilterHandlerForm.jsp
	|	@project  : org.morozko.java.mod.app.cms
	|	@package  : jsp
	|	@creation : 2006-08-25
	|	@license  : META-INF/LICENSE.TXT
	|	@author   : Matteo Franci
	|
	+--%>

<%@ taglib uri='/struts/tiles' prefix='tiles' %>
<%@ taglib uri='/struts/bean' prefix='bean' %>
<%@ taglib uri='/struts/logic' prefix='logic' %>
<%@ taglib uri='/struts/html' prefix='html' %>
<%@ taglib uri='/opinf/navmap' prefix='navmap' %>
<%@ taglib uri='/opinf/oimap' prefix='oimap' %>

	<logic:present name="cmsFilterHandlerModel">
		<bean:define id="cmsFilterHandlerForm" name="cmsFilterHandlerModel" property="bean"/>
	</logic:present>

	<h3 class="cms_title"><bean:message bundle="cms" key="morozko.java.web.cms.label.page.cmsFilterChainList"/></h3>
	
	<html:form action="cmsFilterHandlerSaveForm" method="post">
		<html:hidden name="cmsFilterChainModel" property="idCmsFilterChain"/>
		<table class="cms_explorer">
			<tr>
				<td>id</td>
				<td><html:text name="cmsFilterHandlerForm" property="idCmsFilterHandler" size="64" maxlength="128" readonly="true"/></td>
			</tr>		
			<tr>
				<td>tipo</td>
				<td><html:text name="cmsFilterHandlerForm" property="handlerType" size="64" maxlength="128"/></td>
			</tr>		
			<tr>
				<td>desrizione</td>
				<td><html:text name="cmsFilterHandlerForm" property="handlerDescription" size="64" maxlength="512"/></td>
			</tr>		
			<tr>
				<td>configurazione</td>
				<td><html:textarea name="cmsFilterHandlerForm" property="handlerConfig" rows="20" cols="100%"/></td>
			</tr>					
			<tr>
				<td colspan="2">
					<html:submit property="conferma" styleClass="cms_submit">
						Salva
					</html:submit>
				</td>
			</tr>
		</table>
	</html:form>	
	
	<br/>
	<br/>	
	
	<center>
	
	<html:link action="cmsFilterChainShowForm" styleClass="cms_submit" paramId="idCmsFilterChain" paramName="cmsFilterChainModel" paramProperty="idCmsFilterChain">
		Indietro
	</html:link>
	
	</center>		