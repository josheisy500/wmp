package com.weeklymealplanner;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// Class to access the database
public class DatabaseHelper {

	// private class to handle the creation and updating of the database
	// http://developer.android.com/guide/topics/data/data-storage.html#db
	private class myDB extends SQLiteOpenHelper {

		public myDB(Context context) {
			super(context, Database.DATABASE_NAME, null,
					Database.DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.d("Inside onCreate", "onCreate");
			db.execSQL(Database.WMPDay.SQL_CREATE_WMP_TABLE);
			Log.d("WMP", "" + Database.WMPDay.SQL_CREATE_WMP_TABLE);
			db.execSQL(Database.MealTime.SQL_CREATE_MEALTIME_TABLE);
			Log.d("MealTime", "" + Database.MealTime.SQL_CREATE_MEALTIME_TABLE);
			db.execSQL(Database.Recipe.SQL_CREATE_ENTRIES);
			Log.d("Recipe", "" + Database.Recipe.SQL_CREATE_ENTRIES);
			Log.d("Finished onCreate", "Finished onCreate");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// This database is only a cache for online data, so its upgrade
			// policy is
			// to simply to discard the data and start over
			db.execSQL(Database.WMPDay.SQL_DELETE_WMP_TABLE);
			db.execSQL(Database.MealTime.SQL_DELETE_MEALTIME_TABLE);
			db.execSQL(Database.Recipe.SQL_DELETE_ENTRIES);
			onCreate(db);
		}

		public void onDowngrade(SQLiteDatabase db, int oldVersion,
				int newVersion) {
			onUpgrade(db, oldVersion, newVersion);
		}
	}

	// ideas got from http://developer.android.com/training/notepad/index.html
	// and Searchable Dictionary that comes with the ADT SDK
	// and http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
	// also http://www.vogella.com/tutorials/AndroidSQLite/article.html
	private myDB data;

	public DatabaseHelper(Context c) {
		data = new myDB(c);
	}

	public String createRecipe(String name, String method, String ingredients) {

		SQLiteDatabase db = data.getWritableDatabase();

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(Database.Recipe.COLUMN_NAME_NAME, name);
		values.put(Database.Recipe.COLUMN_NAME_METHOD, method);
		values.put(Database.Recipe.COLUMN_NAME_INGREDIENTS, ingredients);
		values.put(Database.Recipe.COLUMN_NAME_CALORIES, "");
		values.put(Database.Recipe.COLUMN_NAME_PROTEIN, "");
		values.put(Database.Recipe.COLUMN_NAME_CARBOHYDRATES, "");
		values.put(Database.Recipe.COLUMN_NAME_FAT, "");
		values.put(Database.Recipe.COLUMN_NAME_COST, "");
		// Insert the new row, returning the primary key value of the new row
		//convert long to string
		String newRowId = String.valueOf(db.insert(Database.Recipe.TABLE_NAME, null, values));
		return newRowId;
	}

	public String weekId(String name) {
		SQLiteDatabase db = data.getWritableDatabase();

		// Setting up a query idea was got from
		// http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html#query
		// and here
		// http://stackoverflow.com/questions/10600670/sqlitedatabase-query-method
		String[] columns = new String[] { Database.WMPDay._ID + ","
				+ Database.WMPDay.COLUMN_NAME_MEALPLAN_NAME };

		String selection = Database.WMPDay.COLUMN_NAME_MEALPLAN_NAME + " = ? ";

		String[] selectionArgs = { name };
		Cursor c = db.query(Database.WMPDay.TABLE_NAME, columns, selection,
				selectionArgs, null, null, null);

		c.moveToFirst();
		return c.getString(0);

	}

	// This is the one we are using
	public Cursor getMealPlan(String weekid) {
		SQLiteDatabase db = data.getWritableDatabase();
		Log.d("Inside getMealPlan", "getMealPlan " + weekid);

		Log.d("Before weekId", "Before weekId");

		// same as the query above
		String[] columns = new String[] { Database.MealTime._ID,
				Database.MealTime.COLUMN_NAME_BREAKFAST,
				Database.MealTime.COLUMN_NAME_LUNCH,
				Database.MealTime.COLUMN_NAME_DINNER,
				Database.MealTime.COLUMN_MEAL_PLANID };

		String selection = Database.MealTime.COLUMN_MEAL_PLANID + " = ?";

		String[] selectionArgs = new String[] { weekid };

		String groupBy = null;

		String orderBy = Database.MealTime._ID + " ASC ";

		Cursor c = db.query(Database.MealTime.TABLE_NAME, columns, selection,
				selectionArgs, groupBy, null, orderBy);

		Log.d("After", "Before c.moveToFirst ");
		c.moveToFirst();
		return c;
	}

	public void addRecipeToMealPlan(String daySelected,
			String mealTimeSelected, String recipeid) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = data.getWritableDatabase();
		Log.d("Inside addRecipeToMealPlan", "id " + daySelected
				+ " mealTimeSelected " + mealTimeSelected + " recipeid "
				+ recipeid);

		if (mealTimeSelected == "1") {
			mealTimeSelected = "breakfast";
		} else if (mealTimeSelected == "2") {
			mealTimeSelected = "lunch";
		} else if (mealTimeSelected == "3") {
			mealTimeSelected = "dinner";
		}

		// New value for one column
		ContentValues values = new ContentValues();
		values.put(mealTimeSelected, recipeid);

		// Which row to update, based on the ID
		String selection = Database.MealTime._ID + "= ? ";
		String[] selectionArgs = { daySelected };

		// work without this
		int count = db.update(Database.MealTime.TABLE_NAME, values, selection,
				selectionArgs);
		db.close();

	}

	// This is being used
	public String recipeName(String recipeID) {
		SQLiteDatabase db = data.getWritableDatabase();
		Log.d("Inside recipeName", "recipeName");

		String[] columns = new String[] { Database.Recipe._ID,
				Database.Recipe.COLUMN_NAME_NAME };

		String selection = Database.Recipe._ID + " = ?";

		String[] selectionArgs = new String[] { recipeID };

		Cursor c = db.query(Database.Recipe.TABLE_NAME, columns, selection,
				selectionArgs, null, null, null);
		c.moveToFirst();
		return c.getString(c
				.getColumnIndexOrThrow(Database.Recipe.COLUMN_NAME_NAME));
	}

	public void createWMP(String mealPlanName) {
		Log.d("Inside createMealPlan", "createMealPlan");
		SQLiteDatabase db = data.getWritableDatabase();

		// Got this idea from
		// http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
		ContentValues wmp = new ContentValues();
		wmp.put(Database.WMPDay.COLUMN_NAME_MEALPLAN_NAME, mealPlanName);
		wmp.putNull(Database.WMPDay.COLUMN_NAME_SUNDAY);
		wmp.putNull(Database.WMPDay.COLUMN_NAME_MONDAY);
		wmp.putNull(Database.WMPDay.COLUMN_NAME_TUESDAY);
		wmp.putNull(Database.WMPDay.COLUMN_NAME_WEDNESDAY);
		wmp.putNull(Database.WMPDay.COLUMN_NAME_THURSDAY);
		wmp.putNull(Database.WMPDay.COLUMN_NAME_FRIDAY);
		wmp.putNull(Database.WMPDay.COLUMN_NAME_SATURDAY);
		db.insert(Database.WMPDay.TABLE_NAME, null, wmp);

		ContentValues meals = new ContentValues();
		meals.putNull(Database.MealTime.COLUMN_NAME_BREAKFAST);
		meals.putNull(Database.MealTime.COLUMN_NAME_LUNCH);
		meals.putNull(Database.MealTime.COLUMN_NAME_DINNER);
		meals.put(Database.MealTime.COLUMN_MEAL_PLANID, weekId(mealPlanName));

		wmp.clear();
		wmp.put(Database.WMPDay.COLUMN_NAME_SUNDAY,
				db.insert(Database.MealTime.TABLE_NAME, null, meals));
		wmp.put(Database.WMPDay.COLUMN_NAME_MONDAY,
				db.insert(Database.MealTime.TABLE_NAME, null, meals));
		wmp.put(Database.WMPDay.COLUMN_NAME_TUESDAY,
				db.insert(Database.MealTime.TABLE_NAME, null, meals));
		wmp.put(Database.WMPDay.COLUMN_NAME_WEDNESDAY,
				db.insert(Database.MealTime.TABLE_NAME, null, meals));
		wmp.put(Database.WMPDay.COLUMN_NAME_THURSDAY,
				db.insert(Database.MealTime.TABLE_NAME, null, meals));
		wmp.put(Database.WMPDay.COLUMN_NAME_FRIDAY,
				db.insert(Database.MealTime.TABLE_NAME, null, meals));
		wmp.put(Database.WMPDay.COLUMN_NAME_SATURDAY,
				db.insert(Database.MealTime.TABLE_NAME, null, meals));

		String whereClause = Database.WMPDay.COLUMN_NAME_MEALPLAN_NAME + "=? ";

		String[] whereArgs = new String[] { mealPlanName };

		Log.d("Just before update", "" + wmp.toString());
		db.update(Database.WMPDay.TABLE_NAME, wmp, whereClause, whereArgs);
		Log.d("Just After update", "");
	}

	// This is being used
	public Cursor getAllRecipes() {
		// TODO Auto-generated method stub
		SQLiteDatabase db = data.getWritableDatabase();
		Log.d("Before getAllRecipes", "Before getAllRecipes");

		String[] columns = new String[] { Database.Recipe._ID,
				Database.Recipe.COLUMN_NAME_NAME,
				Database.Recipe.COLUMN_NAME_INGREDIENTS };
		Cursor c = db.query(Database.Recipe.TABLE_NAME, columns, null, null,
				null, null, null);

		Log.d("After getAllRecipes", "After getAllRecipes " + c.getCount());
		c.moveToFirst();
		while (!c.isAfterLast()) {
			Log.d(""
					+ c.getString(c
							.getColumnIndexOrThrow(Database.Recipe.COLUMN_NAME_NAME)),
					""
							+ c.getString(c
									.getColumnIndexOrThrow(Database.Recipe.COLUMN_NAME_INGREDIENTS)));
			c.moveToNext();
		}
		Log.d("After loop", "After loop " + c.getCount());
		c.moveToFirst();
		return c;

	}

	public void removeRecipeFromMP(String daySelected, String mealTimeSelected) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = data.getWritableDatabase();
		Log.d("Inside removeRecipeFromMP", "id " + daySelected
				+ " mealTimeSelected " + mealTimeSelected);

		if (mealTimeSelected == "1") {
			mealTimeSelected = "breakfast";
		} else if (mealTimeSelected == "2") {
			mealTimeSelected = "lunch";
		} else if (mealTimeSelected == "3") {
			mealTimeSelected = "dinner";
		}

		// New value for one column
		ContentValues values = new ContentValues();
		values.putNull(mealTimeSelected);

		// Which row to update, based on the ID
		String selection = Database.MealTime._ID + "= ? ";
		String[] selectionArgs = { daySelected };

		int count = db.update(Database.MealTime.TABLE_NAME, values, selection,
				selectionArgs);
		db.close();

	}

	public void close() {
		// TODO Auto-generated method stub
		data.close();
	}

	// /////////////////////// ENDS HERE //////////////////////////////////

	// This is the one used for Display
	public String getWeeklyMealPlanID(String mealPlanName) {
		SQLiteDatabase db = data.getWritableDatabase();

		String[] projection = { Database.WMPDay._ID,
				Database.WMPDay.COLUMN_NAME_MEALPLAN_NAME };

		String selection = Database.WMPDay.COLUMN_NAME_MEALPLAN_NAME + "= ? ";

		String[] selectionArgs = { mealPlanName };

		Cursor c = db.query(Database.WMPDay.TABLE_NAME, projection, selection,
				selectionArgs, null, null, null);

		c.moveToFirst();

		// Was a string mealPlanId
		String mealPlanId = c.getString(c
				.getColumnIndexOrThrow(Database.WMPDay._ID));

		c.close();
		return mealPlanId;

	}

	public/* ArrayList<String> */Cursor getEachWeekDayID(String weekId) {
		SQLiteDatabase db = data.getWritableDatabase();

		String[] columns = new String[] { Database.WMPDay._ID + ","
				+ Database.WMPDay.COLUMN_NAME_SUNDAY + ","
				+ Database.WMPDay.COLUMN_NAME_MONDAY + ","
				+ Database.WMPDay.COLUMN_NAME_TUESDAY + ","
				+ Database.WMPDay.COLUMN_NAME_WEDNESDAY + ","
				+ Database.WMPDay.COLUMN_NAME_THURSDAY + ","
				+ Database.WMPDay.COLUMN_NAME_FRIDAY + ","
				+ Database.WMPDay.COLUMN_NAME_SATURDAY };

		String selection = Database.WMPDay._ID + " = ? ";

		String[] selectionArgs = { weekId };
		Cursor c = db.query(Database.WMPDay.TABLE_NAME, columns, selection,
				selectionArgs, null, null, null);

		c.moveToFirst();
		/*
		 * ArrayList<String> dayList = new ArrayList();
		 * dayList.add(c.getString(c
		 * .getColumnIndexOrThrow(Database.WMPDay.COLUMN_NAME_SUNDAY)));
		 * dayList.add(c.getString(c.getColumnIndexOrThrow(Database.WMPDay.
		 * COLUMN_NAME_MONDAY)));
		 * dayList.add(c.getString(c.getColumnIndexOrThrow
		 * (Database.WMPDay.COLUMN_NAME_TUESDAY)));
		 * dayList.add(c.getString(c.getColumnIndexOrThrow
		 * (Database.WMPDay.COLUMN_NAME_WEDNESDAY)));
		 * dayList.add(c.getString(c.getColumnIndexOrThrow
		 * (Database.WMPDay.COLUMN_NAME_THURSDAY)));
		 * dayList.add(c.getString(c.getColumnIndexOrThrow
		 * (Database.WMPDay.COLUMN_NAME_FRIDAY)));
		 * dayList.add(c.getString(c.getColumnIndexOrThrow
		 * (Database.WMPDay.COLUMN_NAME_SATURDAY)));
		 * 
		 * Log.d("" +
		 * c.getString(c.getColumnIndexOrThrow(Database.WMPDay.COLUMN_NAME_SUNDAY
		 * )), "Everything"); Log.d("" + c.getColumnCount(), "Column Count");
		 * Log.d("" + dayList.toString(), "Column Count");
		 * 
		 * return dayList;
		 */
		db.close();
		return c;
	}

	public/* ArrayList<String> */Cursor getMealTimes(String dayId) {
		SQLiteDatabase db = data.getWritableDatabase();

		String[] columns = new String[] { Database.MealTime._ID + ","
				+ Database.MealTime.COLUMN_NAME_BREAKFAST + ","
				+ Database.MealTime.COLUMN_NAME_LUNCH + ","
				+ Database.MealTime.COLUMN_NAME_DINNER };

		String selection = Database.MealTime._ID + " = ? ";

		String[] selectionArgs = { dayId };
		Cursor c = db.query(Database.MealTime.TABLE_NAME, columns, selection,
				selectionArgs, null, null, null);

		c.moveToFirst();
		/*
		 * ArrayList<String> mealTimeList = new ArrayList<String>();
		 * mealTimeList
		 * .add(c.getString(c.getColumnIndexOrThrow(Database.MealTime
		 * .COLUMN_NAME_BREAKFAST)));
		 * mealTimeList.add(c.getString(c.getColumnIndexOrThrow
		 * (Database.MealTime.COLUMN_NAME_LUNCH)));
		 * mealTimeList.add(c.getString(
		 * c.getColumnIndexOrThrow(Database.MealTime.COLUMN_NAME_DINNER)));
		 * 
		 * Log.d("" + c.getColumnCount(), "Column Count"); Log.d("" +
		 * mealTimeList.toString(), "Column Count");
		 * 
		 * return mealTimeList;
		 */
		return c;
	}

	public Cursor getWMPCursor(String weekId) {
		Log.d("Inside getWMPCursor", "Inside getWMPCursor");
		Cursor curReturn = null;

		// String week = getWeeklyMealPlanID(weekId);
		Log.d("getWeeklyMealPlanID", "");// + week);

		Log.d("Before days", "Before getEachWeekDayID");
		Cursor days = getEachWeekDayID(weekId);
		Log.d("days", days.getString(0));
		Log.d("days", days.getString(1));
		Log.d("days", days.getString(2));
		Log.d("days", days.getString(3));
		Log.d("days", days.getString(4));
		Log.d("days", days.getString(5));
		Log.d("days", days.getString(6));
		Log.d("days", days.getString(7));
		Log.d("days", "" + days.getCount());

		Log.d("Before while loop", "Before while loop");
		while (!days.isAfterLast()) {
			Log.d("Before assign curReturn", "");
			curReturn = getMealTimes(days.getString(0));
			Log.d("Before pop", "Assign" + getMealTimes(days.getString(0)));
			days.moveToNext();
			Log.d("Cursor Return", "" + curReturn.toString());
		}

		curReturn.moveToFirst();
		Log.d("Before pop", "CurReturn" + curReturn);
		return curReturn;
	}

	// This is being used

	// This is being used

	public void addMealTimeToWeekDay(int day, long l) {
		SQLiteDatabase db = data.getWritableDatabase();

		ContentValues values = new ContentValues();

		// Enter the id of the meal time into the day of the week
		// where 0 = Sunday and 6 = Saturday
		switch (day) {
		case 0:
			values.put(Database.WMPDay.COLUMN_NAME_SUNDAY, l);
			break;
		case 1:
			values.put(Database.WMPDay.COLUMN_NAME_MONDAY, l);
			break;
		case 2:
			values.put(Database.WMPDay.COLUMN_NAME_TUESDAY, l);
			break;
		case 3:
			values.put(Database.WMPDay.COLUMN_NAME_WEDNESDAY, l);
			break;
		case 4:
			values.put(Database.WMPDay.COLUMN_NAME_THURSDAY, l);
			break;
		case 5:
			values.put(Database.WMPDay.COLUMN_NAME_FRIDAY, l);
			break;
		case 6:
			values.put(Database.WMPDay.COLUMN_NAME_SATURDAY, l);
			break;
		}
	}

	public void addRecipeToMealTime(String dayTime, int recipe_id) {
		SQLiteDatabase db = data.getWritableDatabase();

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();

		if (dayTime == "Breakfast") {
			values.put(Database.MealTime.COLUMN_NAME_BREAKFAST, recipe_id);
		} else if (dayTime == "Lunch") {
			values.put(Database.MealTime.COLUMN_NAME_LUNCH, recipe_id);
		} else if (dayTime == "Dinner") {
			values.put(Database.MealTime.COLUMN_NAME_DINNER, recipe_id);
		}
	}

	public String getMealPlanTitle(String planId) {
		SQLiteDatabase db = data.getWritableDatabase();

		String[] projection = { Database.WMPDay.COLUMN_NAME_MEALPLAN_NAME,
				Database.WMPDay._ID };

		String selection = Database.WMPDay._ID + "= ? ";

		String[] selectionArgs = { planId };

		Cursor c = db.query(Database.WMPDay.TABLE_NAME, projection, selection,
				selectionArgs, null, null, null);
		return c.getString(0);
	}

	public Cursor returnAllMealPlans() {
		SQLiteDatabase db = data.getWritableDatabase();

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = { Database.WMPDay.COLUMN_NAME_MEALPLAN_NAME,
				Database.WMPDay._ID /*
									 * , Database.Recipe. COLUMN_NAME_METHOD,
									 * Database.Recipe .COLUMN_NAME_INGREDIENTS
									 */
		// ...
		};

		String selection = Database.WMPDay._ID + "= ? ";

		String[] selectionArgs = { "* " };

		// How you want the results sorted in the resulting Cursor
		String sortOrder = Database.WMPDay._ID + " DESC";

		Cursor c = db.query(Database.WMPDay.TABLE_NAME, // The table to query
				projection, // The columns to return
				selection, // The columns for the WHERE clause
				selectionArgs, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				sortOrder // The sort order
				);
		/*
		 * Android Dev Version cursor.moveToFirst(); long itemId =
		 * cursor.getLong( cursor.getColumnIndexOrThrow(FeedEntry._ID) );
		 */

		if (c != null)
			c.moveToFirst();

		return c;
	}

	public int read(String name) {
		SQLiteDatabase db = data.getWritableDatabase();

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = { Database.Recipe._ID,
				Database.Recipe.COLUMN_NAME_NAME /*
												 * , Database.Recipe.
												 * COLUMN_NAME_METHOD,
												 * Database.Recipe
												 * .COLUMN_NAME_INGREDIENTS
												 */
		// ...
		};

		String selection = Database.Recipe._ID + "= ? ";

		String[] selectionArgs = { name };

		// How you want the results sorted in the resulting Cursor
		String sortOrder = Database.Recipe._ID + " DESC";

		Cursor c = db.query(Database.Recipe.TABLE_NAME, // The table to query
				projection, // The columns to return
				selection, // The columns for the WHERE clause
				selectionArgs, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				sortOrder // The sort order
				);
		/*
		 * Android Dev Version cursor.moveToFirst(); long itemId =
		 * cursor.getLong( cursor.getColumnIndexOrThrow(FeedEntry._ID) );
		 */

		if (c != null)
			c.moveToFirst();

		return Integer.parseInt(c.getString(0));
	}

	public void delete(int rowId) {
		SQLiteDatabase db = data.getWritableDatabase();

		// Define 'where' part of query.
		String selection = Database.Recipe._ID + " LIKE ?";
		// Specify arguments in placeholder order.
		String[] selectionArgs = { String.valueOf(rowId) };
		// Issue SQL statement.
		db.delete(Database.Recipe.TABLE_NAME, selection, selectionArgs);
	}

	public void update(String name, int rowId) {
		SQLiteDatabase db = data.getWritableDatabase();

		// New value for one column
		ContentValues values = new ContentValues();
		values.put(Database.Recipe.COLUMN_NAME_NAME, name);

		// Which row to update, based on the ID
		String selection = Database.Recipe._ID + " LIKE ?";
		String[] selectionArgs = { String.valueOf(rowId) };

		int count = db.update(Database.Recipe.TABLE_NAME, values, selection,
				selectionArgs);
	}

	public String listRecipe() {
		SQLiteDatabase db = data.getWritableDatabase();
		List<String> recList = new ArrayList<String>();

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = { Database.Recipe._ID,
				Database.Recipe.COLUMN_NAME_NAME /*
												 * , Database.Recipe.
												 * COLUMN_NAME_METHOD,
												 * Database.Recipe
												 * .COLUMN_NAME_INGREDIENTS
												 */
		// ...
		};

		String selection = Database.Recipe._ID + "= ? ";

		String[] selectionArgs = { "* " };

		// How you want the results sorted in the resulting Cursor
		String sortOrder = Database.Recipe._ID + " DESC";

		Cursor c = db.query(Database.Recipe.TABLE_NAME, // The table to query
				projection, // The columns to return
				selection, // The columns for the WHERE clause
				selectionArgs, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				sortOrder // The sort order
				);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				/*
				 * Contact contact = new Contact();
				 * contact.setID(Integer.parseInt(cursor.getString(0)));
				 * contact.setName(cursor.getString(1));
				 * contact.setPhoneNumber(cursor.getString(2)); // Adding
				 * contact to list
				 */
				recList.add(c.getString(1));
			} while (c.moveToNext());
		}
		String listString = null;
		for (String s : recList) {
			listString += s + "\t";
		}

		return listString;
	}

}
