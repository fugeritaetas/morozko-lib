<%@ taglib uri='/struts/tiles' prefix='tiles' %>
<%@ taglib uri='/struts/bean' prefix='bean' %>
<%@ taglib uri='/struts/logic' prefix='logic' %>
<%@ taglib uri='/struts/html' prefix='html' %>
<%@ taglib uri='/struts/nested' prefix='nested' %>
<%@ taglib uri='/morozko/navmap' prefix='navmap' %>
<%@ taglib uri='/morozko/mhtml' prefix='mhtml' %>
<%@ taglib uri='/morozko/mlist' prefix='mlist' %>
<%@ taglib uri='/morozko/mmap' prefix='mmap' %>
<%@ taglib uri='/morozko/mlogic' prefix='mlogic' %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<bean:define id="mappaRisposte" name="pollModel" property="answerMap"/>

<script type="text/javascript">

	function checkRadio() {	
	<c:forEach var="questionModel" items="${pollModel.pollIndexModel.listPollQuestion}" varStatus="statusQuestion">
		<c:set var="questionBean" value="${questionModel.bean}"/>
			<c:forEach var="optionModel" items="${questionModel.listPollOption}" varStatus="statusOption">
				<c:set var="optionBean" value="${optionModel.bean}"/>
					<logic:present name="optionModel" property="listPollOptionKids">
							<c:forEach var="optionKidModel" items="${optionModel.listPollOptionKids}">
								<c:set var="optionKidBean" value="${optionKidModel.bean}"/>
									document.getElementById('option_${optionKidBean.idPollOption}').disabled = !document.getElementById('option_${optionBean.idPollOption}').checked;
							</c:forEach>
					</logic:present>
			</c:forEach>
	</c:forEach>
	}

			function initDoc() {		
				<c:forEach var="questionModel" items="${pollModel.pollIndexModel.listPollQuestion}" varStatus="statusQuestion">
					<c:set var="questionBean" value="${questionModel.bean}"/>			
						<c:forEach var="optionModel" items="${questionModel.listPollOption}" varStatus="statusOption">
							<c:set var="optionBean" value="${optionModel.bean}"/>
							<logic:present name="optionModel" property="listPollOptionKids">
								<c:forEach var="optionKidModel" items="${optionModel.listPollOptionKids}">
									<c:set var="optionKidBean" value="${optionKidModel.bean}"/>
									<mmap:containsKey name="mappaRisposte" keyName="optionBean" keyProperty="idPollOption">
										document.getElementById('option_${optionKidBean.idPollOption}').disabled = false;
									</mmap:containsKey>	
									<mmap:containsKeyNot name="mappaRisposte" keyName="optionBean" keyProperty="idPollOption">
										document.getElementById('option_${optionKidBean.idPollOption}').disabled = true;
									</mmap:containsKeyNot>	
								</c:forEach>
							</logic:present>							
							Event.observe( document.getElementById('option_${optionBean.idPollOption}'), 'click', checkRadio, false);				
						</c:forEach>
				</c:forEach>			
			}	

	Event.observe(window, 'load', initDoc, false);
	
</script>
