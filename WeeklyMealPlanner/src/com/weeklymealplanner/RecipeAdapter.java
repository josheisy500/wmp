package com.weeklymealplanner;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class RecipeAdapter extends CursorAdapter {

	private LayoutInflater mInflater;
	DatabaseHelper dbHelper;
	Cursor mCursor;

	public RecipeAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);

		// sets up inflator to blow up each recipe in the list
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// get a handle on the database
		dbHelper = new DatabaseHelper(context);
		mCursor = c;
		Log.d("After constructor", "After constructor");
		// TODO Auto-generated constructor stub
	}

	// This method was generated when CursorAdapter was implemented
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.d("Inside newView RecipeAdapter", "Inside newView RecipeAdapter");

		// inflates each recipes view
		return mInflater.inflate(R.layout.each_recipe_layout, parent, false);
	}

	// This method was generated when CursorAdapter was implemented
	@Override
	public void bindView(View view, Context context, Cursor cursor) {

		// TODO Auto-generated method stub
		Log.d("Inside bindView RecipeAdapter", "Inside bindview RecipeAdapter");

		// Gets the information from the database similar to the bind in
		// DisplayCursorAdapter
		String name = cursor.getString(cursor
				.getColumnIndexOrThrow(Database.Recipe.COLUMN_NAME_NAME));
		String ingredients = cursor
				.getString(cursor
						.getColumnIndexOrThrow(Database.Recipe.COLUMN_NAME_INGREDIENTS));

		// Sets the information in the textboxs similar to DisplayCursorAdapter
		TextView recName = (TextView) view.findViewById(R.id.recipename);
		if (name != null) {
			recName.setText(name);
		}

		TextView ingre = (TextView) view.findViewById(R.id.recipeingredients);
		if (ingredients != null) {
			ingre.setText(ingredients);
		}

	}

	// check does this work
	@Override
	public long getItemId(int position) {
		Cursor cursor = getCursor();
		cursor.moveToPosition(position);
		return cursor.getLong(mCursor.getColumnIndex(Database.Recipe._ID));
	}

}
