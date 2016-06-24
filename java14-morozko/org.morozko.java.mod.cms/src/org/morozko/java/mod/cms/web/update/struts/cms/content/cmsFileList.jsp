<%--+
	|
	|	@(#)cmsFileList.jsp
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


	<table class="cms_explorer">
		<tr>
			<th width="1%"></th>
			<th><bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsFile.field.idCmsFile"/></th>
			<th><bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsFile.field.filePath"/></th>			
			<th><bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsFile.field.display"/></th>	
			<th width="1%"></th>	
			<th width="1%"></th>				
		</tr>
		<logic:iterate id="cmsFileDescriptionModel" name="fileList">
			<bean:define id="cmsFileDescriptionBean" name="cmsFileDescriptionModel" property="bean"/>
			<tr>
				<td>
					<html:link action="cmsFileFormShow" paramId="idCmsFile" paramName="cmsFileDescriptionModel" paramProperty="idCmsFile" styleClass="cms_submit">
						<bean:message bundle="cms" key="morozko.java.web.cms.label.common.action.update"/>
					</html:link>
				</td>
				<td><bean:write name="cmsFileDescriptionBean" property="idCmsFile"/></td>
				<td><bean:write name="cmsFileDescriptionBean" property="filePath"/></td>
				<td><bean:write name="cmsFileDescriptionBean" property="fileDisplay"/></td>	
				<td>
					<html:link action="cmsFileFormDelete" paramId="idCmsFile" paramName="cmsFileDescriptionModel" paramProperty="idCmsFile" styleClass="cms_submit">
						<bean:message bundle="cms" key="morozko.java.web.cms.label.common.action.delete"/>
					</html:link>
				</td>											
				<td>
					<a href="<%= request.getContextPath() %>/servlet/cms/file/<bean:write name="cmsFileDescriptionBean" property="filePath"/>" target="_blank" class="cms_submit">test</a>
				</td>							
			</tr>
		</logic:iterate>
	</table>
	
	<html:link action="cmsFileFormShow">Crea Nuovo</html:link>
	