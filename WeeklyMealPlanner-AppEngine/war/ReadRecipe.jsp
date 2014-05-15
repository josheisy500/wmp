<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<h1>Read Recipe</h1>
<jsp:include page="menu.jsp" />
<table border="5" >
	<tr>
	<th>Recipe Name</th>
	<th>Ingredients</th>
	<th>Method</th>
	<th>Calories</th>
	<th>Protein</th>
	<th>Carbohydrates</th>
	<th>Fats</th>
	<th>Cost</th>
		<c:forEach items="${recipeList}" var="recipe">
		<tr></tr>
<td><c:out value="${recipe.name}" /></td>
<td><c:out value="${recipe.ingredients}" /></td>
<td><c:out value="${recipe.method}" /></td>
<td><c:out value="${recipe.calories}" /></td>
<td><c:out value="${recipe.protein}" /></td>
<td><c:out value="${recipe.carbohydrates}" /></td>
<td><c:out value="${recipe.fats}" /></td>
<td><c:out value="${recipe.cost}" /></td>
</tr>
		</c:forEach>
	</tr>
</table>


<body>

</body>
</html>