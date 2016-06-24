<%@ taglib uri="/morozko/mhtml" prefix="mhtml" %>
<%@ taglib uri="/morozko/mlist" prefix="mlist" %>
<%@ taglib uri="/morozko/mmap" prefix="mmap" %>
<%@ taglib uri="/morozko/mlogic" prefix="mlogic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Sql Servlet</title>
	</head>
	<body>
		<h3>Sql Servlet v 1.0.1</h3>
		<c:forEach items="${messageList}" var="message">
			<p>${message}</p>
		</c:forEach>
		<c:choose>
			<form action="sql" method="post">
				<select name="query">
					<c:forEach items="${sqlConfig.queryCollection}" var="query">
						<option value="${query.name}">${query.description}</option>
					</c:forEach>
				</select>
				<input type="submit" name="esegui"/>
			</form>
			<c:when test="${output != null}">
				<c:set value="${output.query.updateMap}" var="updateMap"/>
				<table width="100%">
				<c:if test="${output.header != null}">
					<tr>
						<c:forEach items="${output.header}" var="currentCell">
							<th style="border: 1px solid black">${currentCell}</th>
						</c:forEach>
					</tr>
					<c:forEach items="${output.rows}" var="currentRow">
						<tr>
						<c:forEach items="${currentRow.listCell}" var="currentCell">
							<mmap:containsKey name="updateMap" keyName="currentCell" keyProperty="name">
								<td style="border: 1px solid black; valign: middle;">
									<form action="sql" method="post">
										<input type="hidden" name="query" value="${output.query.name}"/>
										<input type="hidden" name="key" value="${currentRow.keyValue}"/>
										<input type="hidden" name="name" value="${currentCell.name}"/>
										<input type="text" name="value" value="${currentCell}"/>
										<input type="submit" name="update" value="Salva"/>
									</form>
								</td>
							</mmap:containsKey>
							<mmap:containsKeyNot name="updateMap" keyName="currentCell" keyProperty="name">
								<td style="border: 1px solid black; valign: middle;">${currentCell}</td>
							</mmap:containsKeyNot>
						</c:forEach>
						</tr>
					</c:forEach>
				</c:if>
				</table>
			</c:when>
			<c:otherwise>
				<p>Esegui una query</p>
			</c:otherwise>
		</c:choose>
	</body>
</html>