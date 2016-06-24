<%--+
	|
	|	@(#)cmsFilterChainForm.jsp
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

	<logic:present name="cmsFilterChainModel">
		<bean:define id="cmsFilterChainForm" name="cmsFilterChainModel" property="bean"/>
	</logic:present>

	<h3 class="cms_title"><bean:message bundle="cms" key="morozko.java.web.cms.label.page.cmsFilterChainList"/></h3>

	<html:form action="cmsFilterChainSaveForm" method="post">
		<table class="cms_explorer">
			<tr>
				<td><bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsFilterChainModel.field.idCmsFilterChain"/></td>
				<td><html:text name="cmsFilterChainForm" property="idCmsFilterChain" size="64" maxlength="128" readonly="true"/></td>
			</tr>		
			<tr>
				<td><bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsFilterChainModel.field.chainName"/></td>
				<td><html:text name="cmsFilterChainForm" property="chainName" size="64" maxlength="128"/></td>
			</tr>
			<tr>
				<td><bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsFilterChainModel.field.chainDescription"/></td>
				<td><html:text name="cmsFilterChainForm" property="chainDescription" size="64" maxlength="512"/></td>
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
	
	<logic:notEqual name="cmsFilterChainForm" property="idCmsFilterChain" value="0">
		<logic:present name="cmsFilterChainModel" property="listCmsFilterHandler">
		
		<oimap:define id="params"/>
		<oimap:put name="params" keyValue="idCmsFilterChain" elementName="cmsFilterChainModel" elementProperty="idCmsFilterChain"/>
		
		<table class="cms_explorer">
			<tr>
				<th width="1%"></th>			
				<th>Handler</th>
				<th width="1%"></th>							
			</tr>
			
			<logic:iterate id="cmsFilterHandlerModel" name="cmsFilterChainModel" property="listCmsFilterHandler">
				<oimap:put name="params" keyValue="idCmsFilterHandler" elementName="cmsFilterHandlerModel" elementProperty="idCmsFilterHandler"/>
				<tr>
					<td>
						<html:link action="cmsFilterHandlerShowForm" paramId="idCmsFilterHandler" paramName="cmsFilterHandlerModel" paramProperty="idCmsFilterHandler" styleClass="cms_submit">
							<bean:message bundle="cms" key="morozko.java.web.cms.label.common.action.update"/>
						</html:link>
					</td>				
					<td><bean:write name="cmsFilterHandlerModel" property="handlerDescription"/></td>
					<td>
						<html:link action="cmsFilterHandlerDeleteForm" name="params" styleClass="cms_submit">
							<bean:message bundle="cms" key="morozko.java.web.cms.label.common.action.delete"/>
						</html:link>
					</td>						
				</tr>
			</logic:iterate>
		</table>
		
		<br/>
		<br/>	

		</logic:present>
	
		<center>
	
		<html:link action="cmsFilterHandlerShowForm" styleClass="cms_submit">
			<bean:message bundle="cms" key="morozko.java.web.cms.label.common.action.new"/> Filtro
		</html:link>
	
		</center>	

		<br/>
		<br/>

	</logic:notEqual>

	

	<center>
	
		<html:link action="cmsFilterChainList" styleClass="cms_submit">
			Indietro
		</html:link>
	
	</center>
