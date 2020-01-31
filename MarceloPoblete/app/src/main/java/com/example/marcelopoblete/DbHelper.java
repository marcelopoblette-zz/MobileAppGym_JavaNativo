package com.example.marcelopoblete;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DbHelper extends SQLiteOpenHelper {
    String tbl_usuarios = "CREATE TABLE tbl_usuarios ( id INTEGER PRIMARY KEY AUTOINCREMENT, username String, nombre String, apellido String, fechaNac String, clave String)";
    String tbl_evaluaciones = "CREATE TABLE tbl_evaluaciones ( id integer PRIMARY KEY AUTOINCREMENT, fecha String, altura numeric,peso numeric,imc numeric, id_usuario integer )";

    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
//Metodo que crea tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tbl_usuarios);
        db.execSQL(tbl_evaluaciones);

    }
//método que actualiza db
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

