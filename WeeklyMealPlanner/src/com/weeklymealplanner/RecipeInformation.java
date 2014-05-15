package com.weeklymealplanner;

import com.weeklymealplanner.recipeendpoint.model.Recipe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RecipeInformation extends Activity {

	// currentRecipe is an object holidng infromation
	// from the chosen recipe selected from the list adapter
	protected static Recipe currentRecipe;
	String id;

	EditText recipeIngridients, recipeMethod;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_information);

		recipeIngridients = (EditText) findViewById(R.id.recipe_ingridients_editTxt);
		recipeMethod = (EditText) findViewById(R.id.method_editText2);

		// putting ingridients into textview
		// pulling from object not from datastore - array
		recipeIngridients.append(currentRecipe.getIngredients());
		recipeMethod.append(currentRecipe.getMethod());

	}

	public void onDestory() {
		finish();
	}

	// function to send current recipe to meal plan
	public void sendRecipe(View v) {
		// currentRecipe.getCalories();
		DatabaseHelper db = new DatabaseHelper(this);

		
		
		id = db.createRecipe(currentRecipe.getName(),
				currentRecipe.getMethod(), currentRecipe.getIngredients());

		db.addRecipeToMealPlan(WMP.daySelected, WMP.MealTimeSelected, id);

		/**
		 * http://stackoverflow.com/questions/2497205/how-to-return-a-result-
		 * startactivityforresult-from-a-tabhost-activity
		 **/
		Intent returnIntent = new Intent();
		returnIntent.putExtra("result", id);

		setResult(RESULT_OK, returnIntent);

	}
}
