package com.weeklymealplanner;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Display;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;

public class WMP extends Activity {

	ListView eachDay;
	DisplayCursorAdapter disAdapter;
	String message;
	DatabaseHelper db;
	public static String MealTimeSelected = null;
	String recipeid = null;
	public static String daySelected = null;
	static int screenHeight = 0;
	final static int MEAL_TIME_BAR_HEIGHT = 80;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wmp);

		// This is to get the height of the screen so to stretch the listView to
		// the bottom
		// Got ideas from https://gist.github.com/dokkaebi/4173446 and
		// http://stackoverflow.com/questions/1016896/how-to-get-screen-dimensions
		Display screenDis = getWindowManager().getDefaultDisplay();
		screenHeight = screenDis.getHeight();

		Log.d("sunTxt height", "" + screenHeight);

		db = new DatabaseHelper(this);

		Intent intent = getIntent();
		message = intent.getStringExtra(MainMenu.EXTRA_MESSAGE);

		Log.d("Before pop", "Before pop" + db.weekId(message));
		pop(db.weekId(message));
	}

	// Got this method from here to close the database when the activity
	// finishes
	// http://stackoverflow.com/questions/4464892/android-error-close-was-never-explicitly-called-on-database
	@Override
	protected void onDestroy() {
		super.onDestroy();
		db.close();
	}

	public void pop(String weekId) {
		Log.d("Inside pop", "Inside pop");

		Log.d("Before cursor", "Before cursor");

		// This gets the ID of the meal plan name used. This was created to make
		// it easier in the future to add Create a few weekly different meal
		// plans
		Cursor dayC = db.getMealPlan(weekId);
		Log.d("After getting cursor", "After getting cursor");

		// Custom display adapter got for
		// http://stackoverflow.com/questions/5300787/how-do-i-create-a-custom-cursor-adapter-for-a-listview-for-use-with-images-and-t
		// and
		// http://www.gustekdev.com/2013/05/custom-cursoradapter-and-why-not-use.html
		disAdapter = new DisplayCursorAdapter(this, dayC, false);

		// Got the list idea from
		// http://www.vogella.com/tutorials/AndroidListView/article.html
		eachDay = (ListView) findViewById(R.id.list);

		// attach the created adpter to the list
		eachDay.setAdapter(disAdapter);

		// got context menu idea from
		// http://developer.android.com/guide/topics/ui/menus.html
		registerForContextMenu(eachDay);

	}

	// got context menu idea from
	// http://developer.android.com/guide/topics/ui/menus.html
	public void registerForContextMenu(View view) {
		view.setOnCreateContextMenuListener(this);
	}

	@Override
	// got context menu idea from
	// http://developer.android.com/guide/topics/ui/menus.html
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		Log.d(menuInfo.toString(), "pressed");

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.wmp_a_u_r, menu);
	}

	@Override
	// got context menu idea from
	// http://developer.android.com/guide/topics/ui/menus.html
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		Log.d("item.getItemId ", "");
		Log.d("item " + item, "info " + info.id);
		daySelected = String.valueOf(info.id);
		switch (item.getItemId()) {
		case R.id.add_Recipe:
			// Starts the request to list recipes from another class
			// got this from
			// http://developer.android.com/training/basics/intents/result.html
			// use the result down below to insert that recipe into meal plan
			Intent intent = new Intent(this, MainActivity.class);
			startActivityForResult(intent, 1);
			return true;
		case R.id.display_Recipe:
			return true;
		case R.id.remove_Recipe:
			// This removes the particular recipe from meal plan
			// using the day selected which is got from the listView and the
			// meal selected which is got from touching on a particular view
			// within the each item in each row on the listview
			db.removeRecipeFromMP(daySelected, MealTimeSelected);
			// re-populates the meal plan to see changes made
			pop(db.weekId(message));
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	// This is used to get the result back from DisplayRecipe.class using the
	// intent started above
	// got this idea from
	// http://developer.android.com/training/basics/intents/result.html#ReceiveResult
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 1) {

			if (resultCode == RESULT_OK) {
				// gets the id of the recipe from DisplayRecipe
				recipeid = data.getStringExtra("result");
				Log.d("Result back ", "" + recipeid);
				// Adds the recipe to meal plan using the dayselected got from
				// adapter class when
				// a view is selected MealTimeSelected is got when touching a
				// particular view inside
				// each list item and the recipeid is got from the returned id
				// from DisplayRecipe
				db.addRecipeToMealPlan(daySelected, MealTimeSelected, recipeid);
				// re-populate to see changes made to meal planner
				pop(db.weekId(message));
			}
			if (resultCode == RESULT_CANCELED) {
				// Write your code if there's no result
				Toast.makeText(getBaseContext(), "An error has occured",
						Toast.LENGTH_SHORT).show();
			}
		}
	}// onActivityResult

}