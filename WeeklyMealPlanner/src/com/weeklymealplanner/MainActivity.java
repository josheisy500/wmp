package com.weeklymealplanner;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.weeklymealplanner.recipeendpoint.Recipeendpoint;
import com.weeklymealplanner.recipeendpoint.model.CollectionResponseRecipe;
import com.weeklymealplanner.recipeendpoint.model.Recipe;

public class MainActivity extends Activity {

	private ListView recipeList;
	// array of objects of type Recipe
	private List<Recipe> recipes = null;
	private SimpleAdapter adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		recipeList = (ListView) findViewById(R.id.RecipeList);

		new ListOfRecipesAsyncRetriever(this).execute();

		recipeList.setOnItemClickListener(recipeListClickListener);

	}// end on create

	/**
	 * Async Task for retrieving the list of recipes stored on the datastore
	 */

	private class ListOfRecipesAsyncRetriever extends
			AsyncTask<Void, Void, CollectionResponseRecipe> {

		Context context;
		private ProgressDialog pd;

		public ListOfRecipesAsyncRetriever(Context context) {
			this.context = context;
		}

		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(context);
			pd.setMessage("Loading Recipes");
			pd.show();
		}

		@Override
		protected CollectionResponseRecipe doInBackground(Void... params) {

			// calls the back end
			Recipeendpoint.Builder endpointBuilder = new Recipeendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					null);

			endpointBuilder = CloudEndpointUtils.updateBuilder(endpointBuilder);

			CollectionResponseRecipe result;

			Recipeendpoint endpoint = endpointBuilder.build();

			try {
				result = endpoint.listRecipe().execute();
			}// end try

			catch (IOException e) {
				e.printStackTrace();
				result = null;
			}// end catch
			
			return result;

		}// end protected

		@Override
		// @SuppressWarnings("null")
		protected void onPostExecute(CollectionResponseRecipe result) {

			pd.dismiss();
			// cant initialze a list
			
			
			ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
			
			recipes = result.getItems();
			
			for (Recipe recipe : recipes) {
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("recipeIcon", R.drawable.ic_launcher_recipe);
				map.put("recipeName", recipe.getName());
				map.put("recipeIngridients", recipe.getIngredients());
				map.put("recipeMethod", recipe.getMethod());
				data.add(map);
			}

			adapter = new SimpleAdapter(MainActivity.this, data,
					R.layout.recipe_item,
					new String[] { "recipeIcon", "recipeName",
							"recipeIngridients", "recipeMethod" }, new int[] {
							R.id.recipe_Icon, R.id.recipe_name,
							R.id.recipe_Ingridients, R.id.recipe_method });

			recipeList.setAdapter(adapter);

		}

	}// end class listOfRecipes

	private OnItemClickListener recipeListClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			/*
			 * parse to int assign to selected recipe
			 */
			Recipe selectedRecipe = recipes.get((int) arg3);

			RecipeInformation.currentRecipe = selectedRecipe;
			Intent i = new Intent(MainActivity.this, RecipeInformation.class);
			startActivity(i);

		}

	};

	// Refresh Button Click
	public void RefreshButton(View v) {
		new ListOfRecipesAsyncRetriever(this).execute();
	}

}// end main

