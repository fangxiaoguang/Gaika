package com.game.gaika.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class GaikaDBOpenHelper extends SQLiteOpenHelper {

	public GaikaDBOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {

//		Log.d("Gaika", "src.db onCreate  begin");

//		Log.d("Gaika", "src.db onCreate end");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//		Log.d("Gaika", "src.db onUpgrade  begin");

//		Log.d("Gaika", "src.db onUpgrade end");
	}

}
