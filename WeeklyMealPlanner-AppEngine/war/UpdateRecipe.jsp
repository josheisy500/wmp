<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.weeklymealplanner.RecipeController"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.weeklymealplanner.Recipe"%>
<script>
	function fillInFields() {
		var fields = document.getElementById("recipeSelect").value.split(",");
		document.getElementById("recipeID").value = fields[0]; // name
		document.getElementById("recipeName").value = fields[1]; // name
		document.getElementById("recipeMethod").value = fields[2]; // name
		document.getElementById("recipeIngredients").value = fields[3]; // name
		document.getElementById("recipeCalories").value = fields[4]; // name
		document.getElementById("recipeProtein").value = fields[5]; // name
		document.getElementById("recipeCarbohydrates").value = fields[6]; // name
		document.getElementById("recipeFats").value = fields[7]; // name
		document.getElementById("recipeCost").value = fields[8]; // name

	}
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Recipe</title>
</head>
<h1>Update Recipe</h1>
<jsp:include page="menu.jsp" flush="true" />
<body>
	<select id="recipeSelect" onchange="fillInFields()">
		<c:forEach items="${recipeList}" var="recipe">
			<option
				value="${recipe.id},${recipe.name},${recipe.ingredients},${recipe.method},${recipe.calories},${recipe.protein},${recipe.carbohydrates},${recipe.fats},${recipe.cost}">
				<c:out value="${recipe.name}" />
			</option>
		</c:forEach>
	</select>
	<form method="post" action="update">

		<table>
			<tr>
				<td>Recipe ID :</td>
				<td><input type="text" name="recipeID" id="recipeID"
					<c:out value="${item.id}"/> readonly="true" /></td>
			</tr>
			<tr>
				<td>Name :</td>
				<td><input type="text" name="recipeName" id="recipeName"
					<c:out value="${item.name}"/> /></td>
			</tr>
			<tr>
				<td>Method :</td>
				<td><input type="text" name="recipeMethod" id="recipeMethod"
					value="${item.name}" /></td>
			</tr>
			<tr>
				<td>Ingredients :</td>
				<td><input type="text" name="recipeIngredients"
					id="recipeIngredients" value="${item.name}" /></td>
			</tr>
			<tr>
				<td>Calories :</td>
				<td><input type="text" name="recipeCalories"
					id="recipeCalories" value="${item.name}" /></td>
			</tr>
			<tr>
				<td>Protein :</td>
				<td><input type="text" name="recipeProtein" id="recipeProtein"
					value="${item.name}" /></td>
			</tr>
			<tr>
				<td>Carbohydrates :</td>
				<td><input type="text" name="recipeCarbohydrates"
					id="recipeCarbohydrates" value="${item.name}" /></td>
			</tr>
			<tr>
				<td>Fats :</td>
				<td><input type="text" name="recipeFats" id="recipeFats"
					value="${item.name}" /></td>
			</tr>
			<tr>
				<td>Cost :</td>
				<td><input type="text" name="recipeCost" id="recipeCost"
					value="${item.name}" /></td>
			</tr>
		</table>
		<input type="submit" name="submit" value="Add Recipe" />
	</form>

</body>
</html>