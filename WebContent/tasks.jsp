<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="./theme.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<c:if test="${assigned!=null }">
		<table>
			<thead>
				<tr>
				<th>Dodeljeni zadaci</th>
				<th>&nbsp;</th>
				</tr>
			</thead>
			<c:forEach items="${assigned }" var="a" >
				<tbody>
					<tr>
					<td>Naziv zadatka: ${a.name }</td>
					<td><a href="./TaskController?aId=${a.id }" >Prihvati zadatak</a></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</c:if>
	
	<c:if test="${candidate!=null }">
			<thead>
				<tr>
				<th>Zadaci za koje ste kandidat</th>
				<th>&nbsp;</th>
				</tr>
			</thead>
			<c:forEach items="${candidate }" var="c" >
				<tbody>
					<tr>
					<td>Naziv zadatka: ${c.name }</td>
					<td><a href="./TaskController?cId=${c.id }" >Prihvati zadatak</a></td>
					</tr>
				</tbody>
			</c:forEach>
	</c:if>
</body>
</html>