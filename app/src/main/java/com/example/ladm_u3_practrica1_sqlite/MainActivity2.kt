package com.example.ladm_u3_practrica1_sqlite

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        //Declaración de variables
        var extra = intent.extras //Datos de Otro Activity
        var id = "" //Se almacenara el id mas adelante.
        var conductor = Driver(this)
        conductor.vence = SimpleDateFormat("yyyy-MM-dd").format(Date())

        //If para saber si se realiza un UPDATE O UN INSERT
        if(extra!!.getBoolean("Visible")){
            btnActualizar.visibility = View.VISIBLE
            btnGuardar.visibility = View.INVISIBLE
            id = extra!!.getString("IDC")!!
            try {
                conductor = Driver(this).select(extra!!.getString("IDC")!!.toInt())
                txtNombre.setText(conductor.name)
                txtDomicilio.setText(conductor.address)
                txtLicencia.setText(conductor.noLicense)
            }catch (e: Exception){
                Toast.makeText(this,"${e.message}",Toast.LENGTH_LONG).show()
            }

        }//if
        else {
            btnActualizar.visibility = View.INVISIBLE
            btnGuardar.visibility = View.VISIBLE
        }

        //Obtener La fecha del calendario seleccionado
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            conductor.vence = year.toString() + "-" + (month.toInt() + 1).toString() + "-" + dayOfMonth.toString()
        }//calendar

        // Guardar información
        btnGuardar.setOnClickListener {
            conductor.name = txtNombre.text.toString()
            conductor.address = txtDomicilio.text.toString()
            conductor.noLicense = txtLicencia.text.toString()
            if(conductor.insert())
                Toast.makeText(this,"Se Guardo exitosamente",Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this,"ERROR AL GUARDAR!!!",Toast.LENGTH_LONG).show()
            limpiarCampos()
        }//btnGuardar


        btnActualizar.setOnClickListener {
               val c = Driver(this)
               c.name = txtNombre.text.toString()
               c.address = txtDomicilio.text.toString()
               c.noLicense = txtLicencia.text.toString()
               c.vence = conductor.vence
               if(c.update(id))
                   Toast.makeText(this,"Actualización  exitosa",Toast.LENGTH_LONG).show()
               else
                   Toast.makeText(this,"Actualización NO EXITOSA",Toast.LENGTH_LONG).show()

                finish()
        }//btnActualizar

        btnCancelar.setOnClickListener {
            limpiarCampos()
        }
    }//onCreate


    fun limpiarCampos(){
        txtNombre.setText("")
        txtDomicilio.setText("")
        txtLicencia.setText("")
    }//LimpiarCampos

}//class