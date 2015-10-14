package com.example.poetry;

import android.R.integer;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContent extends ContentProvider {
	private MyDbHelper myDbHelper;
	private SQLiteDatabase db = null;

	public static final int BOOK_DIR = 0;
	public static final int BOOK_ITEM = 1;
	public static final int CATEGORY_DIR = 2;
	public static final int CATEGOTY_ITEM = 3;

	public static final String AUTHORITY = "com.example.databasetest.provider";

	public static UriMatcher uriMatcher;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
		uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
		uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
		uriMatcher.addURI(AUTHORITY, "category/#", CATEGOTY_ITEM);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		myDbHelper = new MyDbHelper(getContext(), "BookStore.db", null, 1);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		db = myDbHelper.getWritableDatabase();
		Cursor cursor = null;
		switch (uriMatcher.match(uri)) {
		case BOOK_DIR:
			cursor = db.query("book", projection, selection, selectionArgs,
					null, null, sortOrder);
			break;
		case BOOK_ITEM:
			String bookId = uri.getPathSegments().get(1);
			cursor = db.query("book", projection, "id=?",
					new String[] { bookId }, null, null, sortOrder);
			break;
		case CATEGORY_DIR:

			cursor = db.query("category", projection, selection, selectionArgs,
					null, null, sortOrder);
			break;
		case CATEGOTY_ITEM:

			String categoryId = uri.getPathSegments().get(1);
			cursor = db.query("book", projection, "id=?",
					new String[] { categoryId }, null, null, sortOrder);
			break;

		default:
			break;
		}
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (uriMatcher.match(uri)) {
		case BOOK_DIR:

			return "vnd.android.cursor.dir/vnd.com.example.app.databasetest.provider.book";
		case BOOK_ITEM:

			return "vnd.android.cursor.item/vnd.com.example.app.databasetest.provider.book";
		case CATEGORY_DIR:

			return "vnd.android.cursor.dir/vnd.com.example.app.databasetest.provider.category";
		case CATEGOTY_ITEM:

			return "vnd.android.cursor.item/vnd.com.example.app.databasetest.provider.category";

		default:
			break;
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
