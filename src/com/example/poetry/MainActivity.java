package com.example.poetry;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	private MyDbHelper myDbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myDbHelper = new MyDbHelper(this, "BookStore.db", null, 1);
		db = myDbHelper.getWritableDatabase();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			doinsert();
			break;
		case R.id.button2:
			doQuery();
			break;
		case R.id.button3:
			doDelete();
			break;
		case R.id.button4:
			doUpdate();
			break;

		default:
			break;
		}
	}

	private void doinsert() {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put("name", "jane");
		cv.put("price", 12.8);
		db.insert("book", null, cv);

		cv = new ContentValues();
		cv.put("name", "thing");
		cv.put("price", 55.6);
		db.insert("book", null, cv);
	}

	private void doQuery() {
		// TODO Auto-generated method stub
		Cursor cursor = null;
		cursor = db.query("book", null, null, null, null, null, null);
		while (cursor.moveToNext()) {
			String id = cursor.getString(cursor.getColumnIndex("id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String price = cursor.getString(cursor.getColumnIndex("price"));
			Log.d("debug", "id : " + id);
			Log.d("debug", "name : " + name);
			Log.d("debug", "price : " + price);
		}
	}

	private void doDelete() {
		// TODO Auto-generated method stub
		db.delete("book", null, null);
	}

	private void doUpdate() {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put("price", 11.9);
		db.update("book", cv, "name = ?", new String[] { "jane" });
	}
}
