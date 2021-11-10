package com.example.ladm_u3_practrica1_sqlite



import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.sql.DatabaseMetaData
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList

class Driver(p: Context) {

    var name = ""
    var address = ""
    var noLicense = ""
    var vence = ""
    var pointer = p



    fun insert(): Boolean{

        val datos = ContentValues()
        datos.put("NOMBRE",name)
        datos.put("DOMICILIO",address)
        datos.put("NOLICENCIA",noLicense)
        datos.put("VENCE",vence)
        if(DataBase(pointer,"LMN",null,1).writableDatabase.insert("CONDUCTOR",null,datos) == -1L)
            return false
        return true
    }//end insert


    fun delete(id: String): Boolean{
        if(DataBase(pointer,"LMN",null,1).writableDatabase.delete("CONDUCTOR","IDCONDUCTOR=?",arrayOf(id)) == 0)
            return false
        return true
    }//end delete


    fun update(id: String): Boolean{
        val datos = ContentValues()
        datos.put("NOMBRE",name)
        datos.put("DOMICILIO",address)
        datos.put("NOLICENCIA",noLicense)
        datos.put("VENCE",vence)
        val tabla = DataBase(pointer,"LMN",null,1).writableDatabase
        val result = tabla.update("CONDUCTOR",datos,"IDCONDUCTOR=?",arrayOf(id))
        if(result == 0)
            return false
        return true
    }//end update




    //---------------------------------CONSULTAS--------------------------------------------------------------------------


/*    fun selectNoVehiculo():ArrayList<String>{

        val result = ArrayList<String>()
        val query = "SELECT C.NOMBRE FROM CONDUCTOR C INNER JOIN VEHICULO V ON V.IDCONDUCTOR = C.IDCONDUCTOR WHERE C.IDCONDUCTOR NOT IN(SELECT IDCONDUCTOR FROM VEHICULO)"
        val cursor =  DataBase(pointer,"LMN",null,1).readableDatabase.rawQuery(query,null)

        if(cursor.moveToFirst()){
            do{
                result.add(cursor.getString(0))
            }while (cursor.moveToNext())
        }//if
        else {
            result.add("NO SE ENCONTRO DATOS")
        }//else
        return result

    }//end selectNoLicense */


   /* fun selectAsignado(): ArrayList<String>{
        val result = ArrayList<String>()
        val query = "SELECT V.PLACA, V.MARCA, V.MODELO, V.YEAR FROM CONDUCTOR C INNER JOIN VEHICULO V ON V.IDCONDUCTOR = C.IDCONDUCTOR WHERE C.NOMBRE = ${name}"
        val cursor =  DataBase(pointer,"LMN",null,1).readableDatabase.rawQuery(query,null)

        if(cursor.moveToFirst()){
            do{
                result.add(cursor.getString(0) + "\n" + cursor.getString(1) + "\n" + cursor.getString(2)+ "\n" + cursor.getInt(3))
            }while (cursor.moveToNext())
        }//if
        else {
            result.add("NO SE ENCONTRO DATOS")
        }//else

        return result
    }*/



    //REGRESA UN ARRAYLIST DE CADENAS CON TODOS LOS DATOS DE LA TABLA
    fun selectAll(): ArrayList<String>{

        val result = ArrayList<String>()
        val cursor =  DataBase(pointer,"LMN",null,1).readableDatabase.rawQuery("SELECT * FROM CONDUCTOR",null)

        if(cursor.moveToFirst()){
            do{ result.add(cursor.getString(0) + " ; " +cursor.getString(1) + " ; "  + cursor.getString(2)+ " ; "
                    + cursor.getString(3)+ " ; " + cursor.getString(4))
            }while (cursor.moveToNext())
        }//if
        else { result.add("NO SE ENCONTRO DATOS") }//else

        return result
    }//selectAll


    //Retorna todos los nombres y su ID de la tabla conductor
    fun selectAllName(): ArrayList<String> {

        val result = ArrayList<String>()
        val cursor = DataBase(pointer, "LMN", null, 1).readableDatabase.rawQuery("SELECT * FROM CONDUCTOR", null)

        if (cursor.moveToFirst()) {
            do { result.add(cursor.getString(0) + " ; " + cursor.getString(1))
            } while (cursor.moveToNext())
        }//if
        else { result.add("NO SE ENCONTRO DATOS") }//else

        return result
    }//selectAllName


    fun select(id: Int): Driver{
        var driver = Driver(pointer)
        val cursor = DataBase(pointer,"LMN",null,1).readableDatabase.rawQuery("SELECT * FROM CONDUCTOR WHERE IDCONDUCTOR = ${id}",null)
        if(cursor.moveToFirst()){
            driver.name = cursor.getString(1)
            driver.address = cursor.getString(2)
            driver.noLicense = cursor.getString(3)
            driver.vence = cursor.getString(4)
        }
        return driver
    }//end select(id)

    //Retorna a todos los conductores que su licencia esta vencida.
    fun selectNoLicense(): ArrayList<String> {

        val result = ArrayList<String>()
        val cursor = DataBase(pointer,"LMN",null,1).readableDatabase.rawQuery("SELECT * FROM CONDUCTOR WHERE VENCE < DATE('NOW')",null)

        if(cursor.moveToFirst()){
            do{ result.add(cursor.getString(0) + " ; " + cursor.getString(1) +  " ; " + cursor.getString(2)
                    + " ; " + cursor.getString(3) + " ; " + cursor.getString(4))
            }while (cursor.moveToNext())
        }//if
        else { result.add("NO SE ENCONTRO DATOS")}//else

        return result
    }//end select

}//class

