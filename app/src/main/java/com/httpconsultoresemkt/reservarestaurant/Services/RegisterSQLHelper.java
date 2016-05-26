package com.httpconsultoresemkt.reservarestaurant.Services;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sebastianchimal on 21/02/16.
 */

public class RegisterSQLHelper extends SQLiteOpenHelper {

    private static final String creacion = "CREATE TABLE Reservacion (name TEXT, telephone TEXT, email TEXT, people TEXT, arrival DATE, schedule DATE, motivo TEXT, restaurante TEXT)";


    public RegisterSQLHelper(Context context,String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,nombre,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creacion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table IF Exists " + "DBReservacion");
        onCreate(db);
    }
}
