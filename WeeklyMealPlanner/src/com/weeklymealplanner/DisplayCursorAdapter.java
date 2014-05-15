package com.weeklymealplanner;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class DisplayCursorAdapter extends CursorAdapter {

	private LayoutInflater mInflater;
	DatabaseHelper dbHelper;
	int minItemHeight = 0;

	public DisplayCursorAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		dbHelper = new DatabaseHelper(context);

		// this sets the height for each row in the meal planner
		// idea got from https://gist.github.com/dokkaebi/4173446 and
		// http://stackoverflow.com/questions/1016896/how-to-get-screen-dimensions
		minItemHeight = (WMP.screenHeight - WMP.MEAL_TIME_BAR_HEIGHT) / 7;

		Log.d("After constructor", "After constructor");
		// TODO Auto-generated constructor stub
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.d("Inside newView", "Inside newView");

		// inflates each row for meal planner got idea from
		// http://www.codelearn.org/android-tutorial/android-listview
		return mInflater.inflate(R.layout.grid_row, parent, false);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {

		// Sets each row to the height that was found above
		view.setMinimumHeight(minItemHeight);
		// TODO Auto-generated method stub
		String b_str;
		String l_str;
		String d_str;

		Log.d("Inside bindView", "Inside bindview");
		// checks if the each column is null for each row for the listview
		// idea got from
		// http://stackoverflow.com/questions/7753274/what-will-a-sqlitecursor-do-if-a-column-is-null
		if (cursor
				.isNull(cursor
						.getColumnIndexOrThrow(Database.MealTime.COLUMN_NAME_BREAKFAST))) {
			Log.d("inside isNull breakfast", "inside isNull breakfast");
			b_str = "no meal planned";
		} else {
			Log.d("inside assign breakfast", "inside assign breakfast");
			b_str = dbHelper
					.recipeName(cursor.getString(cursor
							.getColumnIndexOrThrow(Database.MealTime.COLUMN_NAME_BREAKFAST)));
		}
		if (cursor.isNull(cursor
				.getColumnIndexOrThrow(Database.MealTime.COLUMN_NAME_LUNCH))) {
			l_str = "no meal planned";
			Log.d("inside isNull lunch", "inside isNull lunch");
		} else {
			Log.d("inside assign lunch", "inside assign lunch");
			l_str = dbHelper
					.recipeName(cursor.getString(cursor
							.getColumnIndexOrThrow(Database.MealTime.COLUMN_NAME_LUNCH)));
		}
		if (cursor.isNull(cursor
				.getColumnIndexOrThrow(Database.MealTime.COLUMN_NAME_DINNER))) {
			d_str = "no meal planned";
			Log.d("inside isNull dinner", "inside isNull dinner");
		} else {
			Log.d("inside assign dinner", "inside assign dinner");
			d_str = dbHelper
					.recipeName(cursor.getString(cursor
							.getColumnIndexOrThrow(Database.MealTime.COLUMN_NAME_DINNER)));
		}

		// Create each mealtime for the rows in the listview
		TextView breakfast = (TextView) view.findViewById(R.id.breakfast);
		// sets the text for each rows meal time
		breakfast.setText(b_str);
		// uses this to get the meal time selected so it can be used to add and
		// remove
		// meals for the meal planner got from here
		// http://stackoverflow.com/questions/11690504/how-to-use-view-ontouchlistener-instead-of-onclick
		breakfast.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.d("Inside on ", " onTouch");
				WMP.MealTimeSelected = "1";
				Log.d("MealTimeSelected ", "" + 1);
				return false;
			}
		});

		if (b_str == null) {
			breakfast.setText("no meal");
		}

		// Exact same as breakfast above just another TextView area
		TextView lunch = (TextView) view.findViewById(R.id.lunch);
		lunch.setText(l_str);
		lunch.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.d("Inside on ", " onTouch");
				WMP.MealTimeSelected = "2";
				Log.d("MealTimeSelected ", "" + 2);
				return false;
			}
		});
		if (l_str == null) {
			lunch.setText("no meal");
		}

		// Exact same as breakfast above just another TextView area
		TextView dinner = (TextView) view.findViewById(R.id.dinner);
		dinner.setText(d_str);
		dinner.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.d("Inside on ", " dinner onTouch");
				WMP.MealTimeSelected = "3";
				Log.d("MealTimeSelected ", "" + 3);
				return false;
			}
		});
		if (d_str == null) {
			dinner.setText("no meal");
		}
	}

}
