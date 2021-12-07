package com.example.persistancedonnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by zemmari on 14/11/16.
 */
public class MaBaseSQLite extends SQLiteOpenHelper {

    private static final String TABLE_LIVRES = "livres";
    private static final String KEY_ID = "id";
    private static final String KEY_ISBN = "isbn";
    private static final String KEY_TITLE = "title";

    public MaBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LIVRES_TABLE = "CREATE TABLE " + TABLE_LIVRES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_ISBN + " INTEGER,"
                + KEY_TITLE + " TEXT" + ")";
        db.execSQL(CREATE_LIVRES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIVRES);

        onCreate(db);
    }
}