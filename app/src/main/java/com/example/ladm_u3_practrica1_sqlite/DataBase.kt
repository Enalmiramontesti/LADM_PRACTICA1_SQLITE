package com.example.ladm_u3_practrica1_sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context,name,factory,version){

    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL("CREATE TABLE CONDUCTOR (IDCONDUCTOR INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE VARCHAR(200), DOMICILIO VARCHAR(200),NOLICENCIA VARCHAR(200), VENCE DATE)")
        db.execSQL("CREATE TABLE VEHICULO(IDVEHICULO INTEGER PRIMARY KEY AUTOINCREMENT, PLACA VARCHAR(200),MARCA VARCHAR(200),MODELO VARCHAR(200), YEAR INTEGER, IDCONDUCTOR INTEGER, FOREIGN KEY (IDCONDUCTOR) REFERENCES CONDUCTOR(IDCONDUCTOR))")

    }//end onCreate

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }//end onUpgrade

}//class DataBase