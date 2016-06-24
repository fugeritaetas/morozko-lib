<%--+
	|
	|	@(#)cmsPageForm.jsp
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
<%@ taglib uri='/opinf/cms' prefix='cms' %>
 
<logic:present name="cmsPageModel"> 
	<bean:define id="cmsPageForm" name="cmsPageModel" property="bean"/>
</logic:present>

<html:form action="cmsPageFormSave">
	
	<html:hidden name="cmsPageForm" property="idCmsPage"/>
<%--+
	|
	|	@(#)cmsPageForm.jsp
	|	@project  : org.morozko.java.mod.app.cms
	|	@package  : jsp
	|	@creation : 2006-08-25
	|	@license  : META-INF/LICENSE.TXT
	|	@author   : Matteo Franci
	|
	+--%>

	<html:hidden name="cmsPageForm" property="idCmsPageParent"/>
	<html:hidden name="cmsPageForm" property="cmsPageType"/>	
	<html:hidden name="cmsPageForm" property="cmsPageCode"/>		
	<html:hidden name="cmsPageForm" property="saveDate"/>		
	
<table class="cms_explorer">	
	<tr>
		<td><bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsPage.field.title"/></td><td><html:text name="cmsPageForm" property="title" size="64" maxlength="128"/></td>
	</tr>
	<tr>
		<td><bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsPage.field.display"/></td><td><html:text name="cmsPageForm" property="display" size="64" maxlength="64"/></td>		
	</tr>
	<tr>		
		<td><bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsPage.field.description"/></td><td><html:text name="cmsPageForm" property="description" size="64" maxlength="512"/></td>				
	</tr>	
	<tr>		
		<td><bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsPage.field.pageUrl"/></td><td><html:text name="cmsPageForm" property="pageUrl" size="64" maxlength="512"/></td>				
	</tr>		
	<tr>		
		<td><bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsPage.field.idCmsFilterChain"/></td>
		<td>
			<html:select name="cmsPageForm" property="idCmsFilterChain">
				<html:option value="">--- Scegli una catena ---</html:option>
				<html:options collection="listFilterChain" property="idCmsFilterChain" labelProperty="chainDescription"/>
			</html:select>
		</td>				
	</tr>			
	<tr>		
		<td colspan="2"><html:textarea name="cmsPageForm" property="htmlData" rows="20" cols="100%"/></td>		
	</tr>				
	<tr>
		<td colspan="2" align="center"><html:submit property="save" styleClass="cms_submit">Salva</html:submit></td>
	</tr>
</table>
	
</html:form>

<br/>

<logic:present name="cmsPageModel">
	
</logic:present>

<logic:notEqual name="cmsPageForm" property="idCmsPage" value="0">

	<br/>
	
	<html:link action="cmsPageView" paramId="idCmsPage" paramName="cmsPageModel" paramProperty="idCmsPage" target="_blank" styleClass="cms_submit">
		ANTEPRIMA
	</html:link>

	<html:link action="cmsPageExplorer" styleClass="cms_submit">
		Explorer
	</html:link>
	
</logic:notEqual>

<br/>

