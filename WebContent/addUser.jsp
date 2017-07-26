<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="./theme.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<form action="./AddUserController" method="post">
		<table>
			<tr>
				<td>Firstname</td>
				<td><input name="firstname" type="text"></td>
			</tr>
			<tr>
				<td>Lastname</td>
				<td><input name="lastname" type="text"></td>
			</tr>
			<tr>
				<td>Username</td>
				<td><input name="newUsername" type="text" required></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input name="newPassword" type="text" required></td>
			</tr>
			<tr>
				<td>E-mail</td>
				<td><input name="email" type="text" ></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Dodaj" ></td>
			</tr>
		</table>
	</form>

</body>
</html>