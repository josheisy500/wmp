package com.weeklymealplanner;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainMenu extends Activity {

	public final static String EXTRA_MESSAGE = "com.weeklymealplanner";
	SharedPreferences prefs = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

	}

	// Function to invoke downloadRecipes button click
	public void DownloadRecipes(View v) {
		Intent i = new Intent(MainMenu.this, MainActivity.class);
		startActivity(i);
	}

	// Function to invoke weekly meal planner button click
	public void weeklyMealPlanner(View v) {
		
		
		// This sets up the shared preferences for first time app usage
		/** All code was got from https://www.youtube.com/watch?v=wc_v3edv3TA **/
		
		boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
				.getBoolean("isfirstrun", true);

		// This is used for when the app is first activated to create the
		// database
		if (isFirstRun) {
			DatabaseHelper db = new DatabaseHelper(this);
			db.createWMP("mealPlanName");
			Log.d("Just created db", "Just created db");

			getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
					.putBoolean("isfirstrun", false).commit();
		}

		Log.d("Inside weekly meal planner button", " Inside weekly meal planner button");
		
		
		// uses an intent and passes the meal plan name
		// All code is got from
		// http://developer.android.com/training/basics/firstapp/starting-activity.html#StartActivity
		Intent intent = new Intent(this, WMP.class);
		intent.putExtra(MainMenu.EXTRA_MESSAGE, "mealPlanName");
		startActivity(intent);
	}

}
