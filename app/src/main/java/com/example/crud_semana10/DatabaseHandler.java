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
db.execSQL("DROP TABLE IF EXISTS tienda");
    }
    public Boolean insertData(String codTienda, String nombre, String direccion, String ubicacion){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("codTienda", codTienda);
        contentValues.put("nombre", nombre);
        contentValues.put("direccion", direccion);
        contentValues.put("ubicacion", ubicacion);
        long result=db.insert("tienda", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT*FROM tienda",null);
        return cursor;
    }
    public Boolean updateData(String codTienda, String nombre, String ubicacion, String direccion){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("codTienda", codTienda);
        contentValues.put("nombre", nombre);
        contentValues.put("direccion", direccion);
        contentValues.put("ubicacion", ubicacion);

        //registro actualizar
        Cursor cursor=db.rawQuery("SELECT * FROM persona WHERE codTienda=?", new String[]{codTienda});

        if(cursor.getCount()>0) {
            long result = db.update("persona", contentValues, "codTienda=?", new String[]{codTienda});
            if (result == -1) {
                return false;

            } else {
                return true;
            }
        }else{
            return false;
        }
    }
}
