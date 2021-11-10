package com.example.ladm_u3_practrica1_sqlite

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import com.example.ladm_u3_practrica1_sqlite.MainActivity as MainActivity

class Car(p: Context) {

    var licensePlate = ""
    var mark = ""
    var model = ""
    var year : Int = 0
    var idDriver : Int? = null
    var pointer = p


    fun insert():Boolean{

        val datos = ContentValues()
        datos.put("PLACA",licensePlate)
        datos.put("MARCA",mark)
        datos.put("MODELO",model)
        datos.put("YEAR",year)
        //datos.put("IDCONDUCTOR",idDriver)
        if(DataBase(pointer,"LMN",null,1).writableDatabase.insert("VEHICULO",null,datos) == -1L)
            return false

        return true
    }//end insert

    fun update(id:String):Boolean {

        val datos = ContentValues()
        datos.put("PLACA",licensePlate)
        datos.put("MARCA",mark)
        datos.put("MODELO",model)
        datos.put("YEAR",year)
        datos.put("IDCONDUCTOR",idDriver)
        if(DataBase(pointer,"LMN",null,1).writableDatabase.update("VEHICULO",datos,"IDVEHICULO=?", arrayOf(id)) == 0)
            return false
        return true

    }//update


    fun select(quantity: Int): ArrayList<String>{

        val result = ArrayList<String>()
        val cursor = DataBase(pointer,"LMN",null,1)
            .readableDatabase.rawQuery("SELECT * FROM VEHICULO WHERE (YEAR - ${year}) = ${quantity}",null)

        if(cursor.moveToFirst()){
            do{
                result.add(cursor.getString(1) + " ; " + cursor.getString(2) + " ; " + cursor.getString(3) + " ; " + cursor.getInt(4))
            }while (cursor.moveToNext())
        }//if
        else {
            result.add("NO SE ENCONTRO DATOS")
        }//else

        return result

    }//end select



    fun selectAll(): ArrayList<String>{

        val result = ArrayList<String>()
        val cursor = DataBase(pointer,"LMN",null,1)
            .readableDatabase.rawQuery("SELECT * FROM VEHICULO",null)

        if(cursor.moveToFirst()){
            do{
                result.add(cursor.getInt(0).toString() + " ; " + cursor.getString(1) + " ; " + cursor.getString(2) +
                        " ; " + cursor.getString(3) + " ; " + cursor.getInt(4) + " ; " + cursor.getInt(5))
            }while (cursor.moveToNext())
        }//if
        else {
            result.add("NO SE ENCONTRO DATOS")
        }//else

        return result

    }//end selecAll



    fun select(id: String): Car{
        val car = Car(pointer)
        val cursor = DataBase(pointer,"LMN",null,1).readableDatabase.rawQuery("SELECT * FROM VEHICULO WHERE IDVEHICULO = ${id}",null)
        if(cursor.moveToFirst()){
            car.licensePlate = cursor.getString(1)
            car.mark = cursor.getString(2)
            car.model = cursor.getString(3)
            car.year = cursor.getInt(4)
            car.idDriver = cursor.getInt(5)
        }//if
        return car
    }//end selecAll



}//end class