<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="JavaScript" type="text/javascript" src="js/jquery-2.1.0.min.js"></script>
<script src="js/script.js"></script>
<title>Insert title here</title>
<link href="./theme.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form action="./TaskController" method="post">
	<table>
		<c:forEach items="${formProperties }" var="f">
		<tr>
			<td>${f.name }</td>
			<td>
			
			<c:if test="${f.type.name=='string' && f.id!='listaKompanija'}">
				<input " id="${f.id }" onfocusout="sendToServlet(id)" name="${f.name }"
			  	<c:if test="${f.readable!=true }">type="hidden"</c:if>
			   	<c:if test="${f.required==true }"> required</c:if>
			    <c:if test="${f.readable==true }">type="text"</c:if> 
			    <c:if test="${f.writable!=true }">readonly="readonly" value="${f.value }"</c:if>>
			</c:if>
			     
 			 <c:if test="${f.type.name=='enum' && f.id!='kategorijaPosla' }">
			   <select id="${f.id }" onfocusout="sendToServlet(id)">
			   <c:forEach items="${enumValues }" var="e" >
			   <option value="${e.key }">${e.value }</option>
			   </c:forEach>
			   </select> 
			 </c:if>
			 
			 <c:if test="${f.type.name=='enum' && f.id=='kategorijaPosla' }">
			   <select id="${f.id }" onfocusout="sendToServlet(id)">
			   <c:forEach items="${jobCategories }" var="jc" >
			   <option value="${jc.key }">${jc.value }</option>
			   </c:forEach>
			   </select> 
			 </c:if>
			 
 			 <c:if test="${f.type.name=='string' && f.id=='listaKompanija'}">
			   <select id="${f.id }" onfocusout="sendToServlet(id)">
			   <c:forEach items="${ponude }" var="p" >
			   <option value="${p.key }">${p.value }</option>
			   </c:forEach>
			   </select> 
			 </c:if>
			   
<%-- 			  <c:if test="${f.type.name=='enum' && f.id=='listaKompanija' }">
			   <select id="${f.id }" onfocusout="sendToServlet(id)">
			   <c:forEach items="${enumValues }" var="e" >
			   <option value="${e.key }">${e.value }</option>
			   </c:forEach>
			   </select> 
			 </c:if>  --%>
			   
			 <c:if test="${f.type.name=='long' }">
			 	<input  id="${f.id }" onfocusout="sendToServlet(id)" name="${f.name }" onkeypress="return isNumberKey(event)"
			 	<c:if test="${f.readable!=true }">type="hidden"</c:if>
			   	<c:if test="${f.required==true }"> required</c:if>
			    <c:if test="${f.readable==true }">type="text"</c:if>
			    <c:if test="${f.writable!=true }">readonly="readonly"</c:if>>
			 </c:if>
			
			 <c:if test="${f.type.name=='boolean' }">
				 <input    <c:if test="${f.readable==true }"> type="checkbox"</c:if>
				  id="${f.id }"  onfocusout="sendToServlet(id)" name="${f.name }" 
				  <c:if test="${f.readable!=true }">type="hidden"</c:if> >
			 </c:if>  
			   
			   </td>
			   
				
			
		</tr>
		</c:forEach>
		<tr>
			<td><input type="hidden" name="completed" value="completed" ></td>
			<td><input type="submit" value="Dodaj" > <td>
			
		</tr>
	</table>
</form>
<script>

function sendToServlet(id) {
    var x = document.getElementById(id);
    if(x.getAttribute("type")=="checkbox"){
    	if(x.checked==true){
    		x.value=true;
    	}else{
    		x.value=false;
    	}
    }
    
    var xhttp = new XMLHttpRequest();
    if(x.value!=""){
    xhttp.open("POST", "TaskController?propertyId=" +id +"&newValue="+ x.value, true);
    xhttp.send();
    }
}
</script>
	
</body>
</html>