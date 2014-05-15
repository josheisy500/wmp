package com.weeklymealplanner;

import android.provider.BaseColumns;

public final class Database {

	// If you change the database schema, you must increment the database
	// version.
	public static final int DATABASE_VERSION = 12;
	public static final String DATABASE_NAME = "WMPDatabase.db";

	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";

	private Database() {
	}

	public static abstract class Recipe implements BaseColumns {

		public static final String TABLE_NAME = "recipe";
		public static final String COLUMN_NAME_NAME = "name";
		public static final String COLUMN_NAME_METHOD = "method";
		public static final String COLUMN_NAME_INGREDIENTS = "ingredients";
		public static final String COLUMN_NAME_CALORIES = "calories";
		public static final String COLUMN_NAME_PROTEIN = "protein";
		public static final String COLUMN_NAME_CARBOHYDRATES = "carbohydrates";
		public static final String COLUMN_NAME_FAT = "fat";
		public static final String COLUMN_NAME_COST = "cost";
		public static final String COLUMN_RECIPE_PLANID = "planrecipeid";

		public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
				+ Database.Recipe.TABLE_NAME + "(" + Database.Recipe._ID
				+ " INTEGER PRIMARY KEY," + Database.Recipe.COLUMN_NAME_NAME
				+ TEXT_TYPE + COMMA_SEP + Database.Recipe.COLUMN_NAME_METHOD
				+ TEXT_TYPE + COMMA_SEP
				+ Database.Recipe.COLUMN_NAME_INGREDIENTS + TEXT_TYPE
				+ COMMA_SEP + Database.Recipe.COLUMN_NAME_CALORIES + TEXT_TYPE
				+ COMMA_SEP + Database.Recipe.COLUMN_NAME_PROTEIN + TEXT_TYPE
				+ COMMA_SEP + Database.Recipe.COLUMN_NAME_CARBOHYDRATES
				+ TEXT_TYPE + COMMA_SEP + Database.Recipe.COLUMN_NAME_FAT
				+ TEXT_TYPE + COMMA_SEP + Database.Recipe.COLUMN_NAME_COST
				+ TEXT_TYPE + COMMA_SEP + Database.Recipe.COLUMN_RECIPE_PLANID
				+ TEXT_TYPE + " )";

		public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
				+ Database.Recipe.TABLE_NAME;

	}

	public static abstract class WMPDay implements BaseColumns {

		public static final String TABLE_NAME = "wmpday";
		public static final String COLUMN_NAME_MEALPLAN_NAME = "MealPlanName";
		public static final String COLUMN_NAME_SUNDAY = "Sunday";
		public static final String COLUMN_NAME_MONDAY = "Monday";
		public static final String COLUMN_NAME_TUESDAY = "Tuesday";
		public static final String COLUMN_NAME_WEDNESDAY = "Wednesday";
		public static final String COLUMN_NAME_THURSDAY = "Thursday";
		public static final String COLUMN_NAME_FRIDAY = "Friday";
		public static final String COLUMN_NAME_SATURDAY = "Saturday";

		public static final String SQL_CREATE_WMP_TABLE = "CREATE TABLE "
				+ Database.WMPDay.TABLE_NAME + " (" + Database.WMPDay._ID
				+ " INTEGER PRIMARY KEY,"
				+ Database.WMPDay.COLUMN_NAME_MEALPLAN_NAME + TEXT_TYPE
				+ COMMA_SEP + Database.WMPDay.COLUMN_NAME_SUNDAY + TEXT_TYPE
				+ COMMA_SEP + Database.WMPDay.COLUMN_NAME_MONDAY + TEXT_TYPE
				+ COMMA_SEP + Database.WMPDay.COLUMN_NAME_TUESDAY + TEXT_TYPE
				+ COMMA_SEP + Database.WMPDay.COLUMN_NAME_WEDNESDAY + TEXT_TYPE
				+ COMMA_SEP + Database.WMPDay.COLUMN_NAME_THURSDAY + TEXT_TYPE
				+ COMMA_SEP + Database.WMPDay.COLUMN_NAME_FRIDAY + TEXT_TYPE
				+ COMMA_SEP + Database.WMPDay.COLUMN_NAME_SATURDAY + TEXT_TYPE
				+ " )";

		public static final String SQL_DELETE_WMP_TABLE = "DROP TABLE IF EXISTS "
				+ Database.WMPDay.TABLE_NAME;

	}

	public static abstract class MealTime implements BaseColumns {

		public static final String TABLE_NAME = "mealtime";
		public static final String COLUMN_NAME_BREAKFAST = "breakfast";
		public static final String COLUMN_NAME_LUNCH = "lunch";
		public static final String COLUMN_NAME_DINNER = "dinner";
		public static final String COLUMN_MEAL_PLANID = "planmealid";

		public static final String SQL_CREATE_MEALTIME_TABLE = "CREATE TABLE "
				+ Database.MealTime.TABLE_NAME + " (" + Database.MealTime._ID
				+ " INTEGER PRIMARY KEY,"
				+ Database.MealTime.COLUMN_NAME_BREAKFAST + TEXT_TYPE
				+ COMMA_SEP + Database.MealTime.COLUMN_NAME_LUNCH + TEXT_TYPE
				+ COMMA_SEP + Database.MealTime.COLUMN_NAME_DINNER + TEXT_TYPE
				+ COMMA_SEP + Database.MealTime.COLUMN_MEAL_PLANID + TEXT_TYPE
				+ " )";

		public static final String SQL_DELETE_MEALTIME_TABLE = "DROP TABLE IF EXISTS "
				+ Database.MealTime.TABLE_NAME;

	}
}
