<%@ taglib uri='/struts/tiles' prefix='tiles' %>
<%@ taglib uri='/struts/bean' prefix='bean' %>
<%@ taglib uri='/struts/logic' prefix='logic' %>
<%@ taglib uri='/struts/html' prefix='html' %>
<%@ taglib uri='/opinf/navmap' prefix='navmap' %>
<%@ taglib uri='/opinf/oimap' prefix='oimap' %>

<logic:present name="cmsFileDescriptionModel">
	<bean:define id="cmsFileForm" name="cmsFileDescriptionModel" property="bean"></bean:define>
</logic:present>

<html:form action="cmsFileFormSave" enctype="multipart/form-data">
	<html:hidden property="idCmsFile"/>

	<bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsFile.field.filePath"/> : <html:text name="cmsFileForm" property="filePath"/>
	<br/>	
	<bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsFile.field.display"/> : <html:text name="cmsFileForm" property="fileDisplay"/>
	<br/>	
	<bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsFile.field.description"/> : <html:text name="cmsFileForm" property="fileDescription"/>		
	<br/>
	<html:file name="cmsFileForm" property="fileData"/>
	<br/>
	<html:submit>Salva</html:submit>
</html:form>

<html:link action="cmsFileList">Elenco</html:link>