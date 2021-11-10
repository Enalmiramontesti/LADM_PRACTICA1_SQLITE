package com.example.ladm_u3_practrica1_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        //declaración de variables
        var idc: List<String>? = null
        var  v = Car(this)

        //LLena el spinner con datos del usuario
        //spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Driver(this).selectAllName())

        //Obtener datos del spinner
        spinner.setOnItemClickListener { parent, view, position, id ->
                idc = spinner.getItemAtPosition(position).toString().split(" ; ")
        }

        //Insertar Nuevo Vehiculo
        btnGuardar.setOnClickListener {
            v.licensePlate = txtPlaca.text.toString()
            v.mark = txtMarca.text.toString()
            v.model = txtModelo.text.toString()
            v.year = txtYear.text.toString().toInt()
            if(v.insert())
                Toast.makeText(this,"Se Guardo exitosamente",Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this,"ERROR AL GUARDAR!!!",Toast.LENGTH_LONG).show()
        }//btnGuardar

    }//onCreate
}//class