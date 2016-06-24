<%@ taglib uri='/struts/tiles' prefix='tiles' %>
<%@ taglib uri='/struts/bean' prefix='bean' %>
<%@ taglib uri='/struts/logic' prefix='logic' %>
<%@ taglib uri='/struts/html' prefix='html' %>
<%@ taglib uri='/opinf/navmap' prefix='navmap' %>
<%@ taglib uri='/opinf/oimap' prefix='oimap' %>
<%@ taglib uri='/opinf/cms' prefix='cms' %>

<logic:present parameter="editorType">
	<logic:equal parameter="editorType" value="javascript">
		<bean:define id="editorType" value="javascript" scope="session"/>	
	</logic:equal>
	<logic:notEqual parameter="editorType" value="javascript">
		<bean:define id="editorType" value="html" scope="session"/>	
	</logic:notEqual>	
</logic:present>

<logic:notPresent name="editorType">
	<bean:define id="editorType" value="html"/>
</logic:notPresent>

<script language="Javascript1.2"><!-- // load htmlarea
_editor_url = '<%=request.getContextPath()%>/html/edit/';                     // URL to htmlarea files
var win_ie_ver = parseFloat(navigator.appVersion.split("MSIE")[1]);
if (navigator.userAgent.indexOf('Mac')        >= 0) { win_ie_ver = 0; }
if (navigator.userAgent.indexOf('Windows CE') >= 0) { win_ie_ver = 0; }
if (navigator.userAgent.indexOf('Opera')      >= 0) { win_ie_ver = 0; }
if (win_ie_ver >= 5.5) {
  document.write('<scr' + 'ipt src="' +_editor_url+ 'editor.js"');
  document.write(' language="Javascript1.2"></scr' + 'ipt>');
} else { document.write('<scr'+'ipt>function editor_generate() { return false; }</scr'+'ipt>'); }
// --></script>

<logic:present name="cmsFragmentModel">
	<bean:define id="cmsFragmentForm" name="cmsFragmentModel" property="bean"/>
</logic:present>

<html:form action="cmsFragmentFormSave">

	<logic:present name="cmsPageModel">
		<html:hidden name="cmsPageModel" property="idCmsPage"/>		
	</logic:present>

	<html:hidden name="cmsFragmentForm" property="idCmsFragment"/>
	<html:hidden name="cmsFragmentForm" property="cmsFragmentCode"/>	
	<html:hidden name="cmsFragmentForm" property="cmsFragmentState"/>		
	<html:hidden name="cmsFragmentForm" property="cmsFragmentType"/>		
	<html:hidden name="cmsFragmentForm" property="saveDate"/>		

	<bean:message bundle="cms" key="morozko.java.web.cms.label.entity.cmsFragment.field.fragmentUrl"/> : <html:text name="cmsFragmentForm" property="fragmentUrl" size="64" maxlength="512"/>

	<logic:present name="clear">
		<html:textarea name="cmsFragmentForm" property="htmlContent" style="width:680; height:300" value=""/>	
	</logic:present>
	<logic:notPresent name="clear">
		<html:textarea name="cmsFragmentForm" property="htmlContent" style="width:680; height:300"/>	
	</logic:notPresent>	


	<html:submit property="save" styleClass="cms_submit">Salva</html:submit>

</html:form>

<br/>

<oimap:define id="params"/>

	<oimap:put name="params" keyValue="idCmsFragment" elementName="cmsFragmentForm" elementProperty="idCmsFragment"/>

<logic:equal name="editorType" value="javascript">
	<script language="javascript1.2">
		editor_generate('htmlContent');
	</script>

	<oimap:put name="params" keyValue="editorType" elementValue="html"/>
	<html:link action="cmsFragmentFormShow" name="params" styleClass="cms_submit">Cambia ad editor normale</html:link>	
</logic:equal>
<logic:notEqual name="editorType" value="javascript">
	<oimap:put name="params" keyValue="editorType" elementValue="javascript"/>
	<html:link action="cmsFragmentFormShow" name="params" styleClass="cms_submit">Cambia ad editor javascript</html:link>
</logic:notEqual>

<br/>

<logic:present name="cmsFragmentModel">
	<logic:notEmpty name="cmsFragmentModel" property="listCmsPage">
		<logic:iterate id="cmsPageModel" name="cmsFragmentModel" property="listCmsPage">
			<html:link action="cmsPageFormShow" paramId="idCmsPage" paramName="cmsPageModel" paramProperty="idCmsPage" styleClass="cms_submit">Torna indietro</html:link>
		</logic:iterate>
	</logic:notEmpty>
</logic:present>

<br/>

<navmap:link navEntry="cmsPageExplorer" navModule="cms" styleClass="cms_submit"/>

<br/>
<br/>
