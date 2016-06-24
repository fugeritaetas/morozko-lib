<%@ taglib uri='/struts/tiles' prefix='tiles' %>
<%@ taglib uri='/struts/bean' prefix='bean' %>
<%@ taglib uri='/struts/logic' prefix='logic' %>
<%@ taglib uri='/struts/html' prefix='html' %>
<%@ taglib uri='/opinf/navmap' prefix='navmap' %>

	<logic:present name="cmsPageModel">

		<bean:define id="cmsPageBean" name="cmsPageModel" property="bean"/>
	
		<h3><bean:write name="cmsPageBean" property="title"/></h3>
			
		<bean:write name="CmsPageModel.DATA" filter="false"/>
	
	</logic:present>

<center>
	<br/>
	<logic:notEqual name="cmsPageModel" property="idCmsPageParent" value="-1">
		<html:link action="cmsPageView" paramId="idCmsPage" paramName="cmsPageModel" paramProperty="idCmsPageParent" styleClass="cms_link">
			Torna Indietro
		</html:link>	
	</logic:notEqual>		
</center>
