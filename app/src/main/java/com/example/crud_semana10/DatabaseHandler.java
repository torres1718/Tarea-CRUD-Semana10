package com.example.crud_semana10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

public DatabaseHandler( @Nullable Context context) {super(context, "sucursal.db", null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE tienda(codTienda TEXT PRIMARY KEY, nombre TEXT, direccion TEXT, ubicacion TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
