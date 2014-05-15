<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Recipe</title>
</head>
<body>
	<h1>Add Recipe</h1>
	<jsp:include page="menu.jsp" />
	<form method="post" action="add">
		<table>
			<tr>
				<td>Name :</td>
				<td><input type="text" name="recipeName" id="recipeName" /></td>
			</tr>
			<tr>
				<td>Method :</td>
				<td><input type="text" name="recipeMethod" id="recipeMethod" /></td>
			</tr>
			<tr>
				<td>Ingredients :</td>
				<td><input type="text" name="recipeIngredients"
					id="recipeIngredients" /></td>
			</tr>
			<tr>
				<td>Calories :</td>
				<td><input type="text" name="recipeCalories"
					id="recipeCalories" /></td>
			</tr>
			<tr>
				<td>Protein :</td>
				<td><input type="text" name="recipeProtein" id="recipeProtein" /></td>
			</tr>
			<tr>
				<td>Carbohydrates :</td>
				<td><input type="text" name="recipeCarbohydrates"
					id="recipeCarbohydrates" /></td>
			</tr>
			<tr>
				<td>Fats :</td>
				<td><input type="text" name="recipeFats" id="recipeFats" /></td>
			</tr>
			<tr>
				<td>Cost :</td>
				<td><input type="text" name="recipeCost" id="recipeCost" /></td>
			</tr>
		</table>
		<input type="submit" class="save" title="Save" value="Save" />
	</form>

</body>
</html>