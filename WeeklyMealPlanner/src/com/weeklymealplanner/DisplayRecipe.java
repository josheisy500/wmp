package com.weeklymealplanner;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DisplayRecipe extends Activity {

	ListView recipeListView;
	DatabaseHelper dbHandler;
	RecipeAdapter recipeAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_recipe);

		recipeListView = (ListView) findViewById(R.id.recipeList);
		dbHandler = new DatabaseHelper(this);

		recipePopulate();

		// This is to select the recipe in the listView
		// idea got from
		// http://stackoverflow.com/questions/10277549/onitemclicklistener-listview
		recipeListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			// Actually selects the item and returns attributes belonging to
			// that view
			public void onItemClick(AdapterView<?> listV, View v, int pos,
					long id) {
				// TODO Auto-generated method stub
				Log.d("Clicked item id", " " + id);

				// This starts up and returns the recipe id to the calling
				// activity WMP
				// idea got from
				// http://stackoverflow.com/questions/2497205/how-to-return-a-result-startactivityforresult-from-a-tabhost-activity
				String result = String.valueOf(id);
				Intent returnIntent = new Intent();
				returnIntent.putExtra("result", result);
				setResult(RESULT_OK, returnIntent);
				finish();
			}
		});
	}

	// This populates all the recipes to the screen to chose from
	public void recipePopulate() {
		Log.d("Inside recipePopulate", "Inside recipePopulate");

		Log.d("Before cursor", "Before cursor");

		// gets all the recipes and stores them in a cursor
		Cursor allRecipeCursor = dbHandler.getAllRecipes();

		// check if it works without variable cursor adapter

		Log.d("After allRecipes getting cursor",
				"After allRecipes getting cursor");

		// creates an adapter an then sets it to the list to link the database
		// and
		// list together
		recipeAdapter = new RecipeAdapter(this, allRecipeCursor, false);
		recipeListView.setAdapter(recipeAdapter);
	}

}
