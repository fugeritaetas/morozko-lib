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

<bean:define id="mappaStat" name="pollModel" property="statMap"/>

<form action="pollStat.do" method="post">
	<input type="hidden" name="questionaryName" value="${param.questionaryName}"/>
	<select name="day">
		<option value="">---TUTTI---</option>
		<c:forEach items="${listDay}" var="currentDay">
			<option value="${currentDay}">${currentDay}</option>
		</c:forEach>
	</select>
	<input type="submit" name="carica" value="carica"/>
</form>

<br/>

<logic:present name="pollMessage">
	<ul>
	<c:forEach var="message" items="${pollMessage}">
		<li>${message}</li>
	</c:forEach>
	</ul>
</logic:present>

	<table class="pollStat">
		<tr>	
			<th colspan="1"></th>			
			<th colspan="1" width="30px"></th>									
		</tr>	
		<tr>
			<th colspan="1">Domanda</th>
			<th colspan="1" align="center">#</th>			
		</tr>
	<c:forEach var="questionModel" items="${pollModel.pollIndexModel.listPollQuestion}" varStatus="statusQuestion">
		<c:set var="questionBean" value="${questionModel.bean}"/>
		<tr>
			<td colspan="2" class="lvl1"><b>${questionBean.questionText}</b></td>
		</tr>
			<c:forEach var="optionModel" items="${questionModel.listPollOption}" varStatus="statusOption">
				<c:set var="optionBean" value="${optionModel.bean}"/>
					<tr>
						<td colspan="1" class="lvl2">${optionBean.optionText}</td>			
						<td colspan="1" align="center" class="value">
							<mmap:containsKey name="mappaStat" keyName="optionBean" keyProperty="idPollOption">
								<mmap:get id="statModel" name="mappaStat" keyName="optionBean" keyProperty="idPollOption"/>							
								<c:set var="current" value="${statModel.bean.answerNumber}"/>
							</mmap:containsKey>
							<mmap:containsKeyNot name="mappaStat" keyName="optionBean" keyProperty="idPollOption">
								<c:set var="current" value="-"/>							
							</mmap:containsKeyNot>							
							${current}						
						</td>															
					</tr>
						<logic:present name="optionModel" property="listPollOptionKids">
							<tr>
								<td colspan="2" class="lvl3">${optionBean.optionDefault}</td>			
							</tr>
							<c:forEach var="optionKidModel" items="${optionModel.listPollOptionKids}">
								<c:set var="optionKidBean" value="${optionKidModel.bean}"/>
								<tr>
									<td colspan="1" class="lvl4">${optionKidBean.optionText}</td>			
									<td colspan="1" align="center" class="value">
										<mmap:containsKey name="mappaStat" keyName="optionKidBean" keyProperty="idPollOption">
											<mmap:get id="statModel" name="mappaStat" keyName="optionKidBean" keyProperty="idPollOption"/>							
											<c:set var="current" value="${statModel.bean.answerNumber}"/>
										</mmap:containsKey>
										<mmap:containsKeyNot name="mappaStat" keyName="optionKidBean" keyProperty="idPollOption">
											<c:set var="current" value="-"/>							
										</mmap:containsKeyNot>							
										${current}										
									</td>									
								</tr>
							</c:forEach>
						</logic:present>					
			</c:forEach>
	</c:forEach>
	</table>
	
	<br/>
