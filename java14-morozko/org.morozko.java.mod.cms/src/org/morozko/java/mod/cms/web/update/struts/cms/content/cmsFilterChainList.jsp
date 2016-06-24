<%--+
	|
	|	@(#)cmsFilterChainList.jsp
	|	@project  : org.morozko.java.mod.app.cms
	|	@package  : jsp
	|	@creation : 2006-08-23
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

	<h3 class="cms_title"><bean:message bundle="cms" key="morozko.java.web.cms.label.page.cmsFilterChainList"/></h3>

	<table class="cms_explorer">
		<tr>
			<th width="1%"></th>
			<th><bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsFilterChainModel.field.idCmsFilterChain"/></th>
			<th><bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsFilterChainModel.field.chainName"/></th>			
			<th><bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsFilterChainModel.field.chainDescription"/></th>	
			<th width="1%"></th>			
		</tr>
		<logic:iterate id="cmsFilterChainModel" name="listFilterChain">
			<bean:define id="cmsFilterChainBean" name="cmsFilterChainModel" property="bean"/>
			<tr>
				<td>
					<html:link action="cmsFilterChainShowForm" paramId="idCmsFilterChain" paramName="cmsFilterChainModel" paramProperty="idCmsFilterChain" styleClass="cms_submit">
						<bean:message bundle="cms" key="morozko.java.web.cms.label.common.action.update"/>
					</html:link>
				</td>
				<td><bean:write name="cmsFilterChainBean" property="idCmsFilterChain"/></td>
				<td><bean:write name="cmsFilterChainBean" property="chainName"/></td>
				<td><bean:write name="cmsFilterChainBean" property="chainDescription"/></td>							
				<td>
					<html:link action="cmsFilterChainDeleteForm" paramId="idCmsFilterChain" paramName="cmsFilterChainModel" paramProperty="idCmsFilterChain" styleClass="cms_submit">
						<bean:message bundle="cms" key="morozko.java.web.cms.label.common.action.delete"/>
					</html:link>
				</td>				
			</tr>
		</logic:iterate>
	</table>
	
	<br/>
	<br/>	
	
	<center>
	
	<html:link action="cmsFilterChainShowForm" styleClass="cms_submit">
		<bean:message bundle="cms" key="morozko.java.web.cms.label.common.action.new"/>
	</html:link>
	
	<html:link action="cmsPageExplorer" styleClass="cms_submit">
		Explorer
	</html:link>	
	
	</center>	
	