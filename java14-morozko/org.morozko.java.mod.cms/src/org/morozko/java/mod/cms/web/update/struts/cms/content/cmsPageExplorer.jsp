<%--+
	|
	|	@(#)cmsPageExplorer.jsp
	|	@project  : org.morozko.java.mod.app.cms
	|	@package  : jsp
	|	@creation : 2006-09-21
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

<logic:notEmpty name="listPages">

	<logic:notEqual name="cmsPageModel" property="idCmsPageParent" value="-1">
		<html:link action="cmsPageExplorer" styleClass="cms_submit" paramId="idCmsPage" paramName="cmsPageModel" paramProperty="idCmsPageParent">
			Torna indietro
		</html:link>
		<br/>
		<br/>
	</logic:notEqual>

	<table class="cms_explorer">
		<tr>
			<th></th>
			<th><bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsPage.field.title"/></th>
			<th><bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsPage.field.display"/></th>
			<th><bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsPage.field.pageUrl"/></th>
			<th></th>
			<th></th>			
			<th></th>			
		</tr>

		<logic:iterate id="cmsPageModel" name="listPages">
			<bean:define id="cmsPageBean" name="cmsPageModel" property="bean"/>
			<tr>
				<td class="cms_noborder">
					<html:link action="cmsPageFormShow" paramId="idCmsPage" paramName="cmsPageBean" paramProperty="idCmsPage" styleClass="cms_submit">
						&gt;
					</html:link>
				</td>
				<td><bean:write name="cmsPageBean" property="title"/></td>
				<td><bean:write name="cmsPageBean" property="display"/></td>
				<td><bean:write name="cmsPageBean" property="pageUrl"/></td>								
				<td class="cms_noborder">
					<html:link action="cmsPageFormShow" paramId="idCmsPageParentNew" paramName="cmsPageBean" paramProperty="idCmsPage" styleClass="cms_submit">+</html:link>
				</td>								
				<td class="cms_noborder">
					<oimap:define id="deletePageParams"/>
					<oimap:put name="deletePageParams" keyValue="idCmsPage" elementName="cmsPageBean" elementProperty="idCmsPageParent"/>
					<oimap:put name="deletePageParams" keyValue="idCmsPageDelete" elementName="cmsPageBean" elementProperty="idCmsPage"/>					
					<html:link action="cmsPageDelete" name="deletePageParams" styleClass="cms_submit">-</html:link>					
				</td>										
				<td class="cms_noborder">
					<logic:greaterThan name="cmsPageBean" property="kidsCount" value="0">
						<oimap:define id="params"/>
						<oimap:put name="params" keyValue="idCmsPage" elementName="cmsPageBean" elementProperty="idCmsPage"/>
						<oimap:put name="params" keyValue="idCmsPageParent" elementName="cmsPageBean" elementProperty="idCmsPageParent"/>						
						<html:link action="cmsPageExplorer" name="params" styleClass="cms_submit">
							&lt;
						</html:link>
					</logic:greaterThan>
				</td>																	
			</tr>
		</logic:iterate>
	</table>
		
	
</logic:notEmpty>

<br/>

<logic:empty name="listPages">
	<h3>Non ci sono pagine</h3>
	<br/>
	<br/>	
</logic:empty>

	<html:link action="cmsPageFormShow?idCmsPage=1" styleClass="cms_submit">
		Modifica la home page
	</html:link>

	<html:link action="cmsPageFormShow" styleClass="cms_submit">Nuova pagina</html:link>

	<html:link action="cmsFileList" styleClass="cms_submit">
		Elenco File
	</html:link>	
	
	<html:link action="cmsFilterChainList" styleClass="cms_submit">
		Elenco Catene
	</html:link>		
