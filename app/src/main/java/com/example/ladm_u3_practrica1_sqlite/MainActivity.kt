package com.example.ladm_u3_practrica1_sqlite

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Agegar a lisview los Datos Existentes
        conductor.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Driver(this).selectAll())
        vehiculo.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Car(this).selectAll())

        //declaración de variables
        var datoV:List<String>? = null
        var datoC: List<String>? = null

        //ABRE EL ACTIVITY2 para insertar un conductor
        btnIC.setOnClickListener {
            val ventana2 = Intent(this,MainActivity2::class.java).putExtra("Visible",false)
            ventana2.putExtra("Visible",false)
            startActivity(ventana2)
        }//btnIC

        //obtener los datos de lisView
        conductor.setOnItemClickListener { parent, view, position, id ->
            datoC = conductor.getItemAtPosition(position).toString().split(" ; ")
        }//conductor

        btnAC.setOnClickListener {
            val venta2 = Intent(this,MainActivity2::class.java)
            venta2.putExtra("IDC", datoC!![0])
            venta2.putExtra("Visible", true)
            startActivity(venta2)
        }//btnAC

        //Elminar un elemento de la base de datos
        btnEC.setOnClickListener {
            if(Driver(this).delete(datoC!![0]))
                Toast.makeText(this,"Eliminación exitosa",Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this,"Error al eliminar",Toast.LENGTH_LONG).show()
            conductor.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Driver(this).selectAll())
        }


        //Muestra Los conductores que TIENE LA LICENCIA VENCIDA
        btnNolicencia.setOnClickListener {
            conductor.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Driver(this).selectNoLicense())
        }//btnNoLicencia



        //-----------------------------------VEHICULO----------------------------------------------------------

        vehiculo.setOnItemClickListener { parent, view, position, id ->
            datoV = vehiculo.getItemAtPosition(position).toString().split(" ; ")
        }//vehiculo

        //Activity3 para insertar automovil
        btnIV.setOnClickListener {
            val v3 = Intent(this,MainActivity3::class.java)
            v3.putExtra("VI", false)
            startActivity(v3)
        }//btnIV

        btnAV.setOnClickListener {

            val ventana3 = Intent(this, MainActivity3::class.java)
            ventana3.putExtra("IDV", datoV!![0])
            ventana3.putExtra("VI", true)
            startActivity(ventana3)
        }//btnAV

    }


}