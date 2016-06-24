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

<logic:present name="pollMessage">
	<ul>
	<c:forEach var="message" items="${pollMessage}">
		<li>${message}</li>
	</c:forEach>
	</ul>
</logic:present>

<form action="pollSave.do" method="post">

	<c:forEach var="questionModel" items="${pollModel.pollIndexModel.listPollQuestion}" varStatus="statusQuestion">
		<c:set var="questionBean" value="${questionModel.bean}"/>
		<p style="margin-top: 40px;"><b>${questionBean.questionText}</b>
			<c:forEach var="optionModel" items="${questionModel.listPollOption}" varStatus="statusOption">
				<c:set var="optionBean" value="${optionModel.bean}"/>
				<p style="margin-left: 20px;">
					<input type="radio" 
						<mmap:containsKey name="mappaRisposte" keyName="optionBean" keyProperty="idPollOption">
							checked="true"
						</mmap:containsKey>					
						name="question_${optionBean.idPollQuestion}" 
						id="option_${optionBean.idPollOption}"
						value="${optionBean.idPollOption}">
					${optionBean.optionText}
						<logic:present name="optionModel" property="listPollOptionKids">
							<p style="margin-left: 30px;">${optionBean.optionDefault}</p>
							<c:forEach var="optionKidModel" items="${optionModel.listPollOptionKids}">
								<c:set var="optionKidBean" value="${optionKidModel.bean}"/>
								<p style="margin-left: 40px;">
									<input type="checkbox" 
										<mmap:containsKey name="mappaRisposte" keyName="optionKidBean" keyProperty="idPollOption">
											checked="true"
										</mmap:containsKey>										
										name="question_kids_${optionBean.idPollQuestion}" 
										id="option_${optionKidBean.idPollOption}"
										value="${optionKidBean.idPollOption}">
									${optionKidBean.optionText}
								</p>
							</c:forEach>
						</logic:present>					
				</p>
			</c:forEach>
		</p>
	</c:forEach>
	
	<br/>
	
	<input type="submit" name="salva" value="salva"/>
	
</form>
