package com.example.mirefri

import MyToolbar
import Usuario
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CrearCuentaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)


        val createbtn = findViewById<Button>(R.id.CreateUser_btn)


        createbtn.setOnClickListener{

            val nombre = findViewById<EditText>(R.id.NombreCA_IF).text.toString()
            //val apellido = findViewById<EditText>(R.id.ApellidoCA_IF).text.toString()
            val correo = findViewById<EditText>(R.id.CorreoAC_IF).text.toString()
            val clave = findViewById<EditText>(R.id.ClaveAC_IF).text.toString()
            val repetirClave = findViewById<EditText>(R.id.RepetirClaveAC_IF).text.toString()

            if (clave == repetirClave) {

                val usuario = Usuario(nombre, "", correo, clave, 1, mutableListOf(Productos()))
                val resultIntent = Intent()
                resultIntent.putExtra("Usuario", usuario)
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Las claves no coinciden", Toast.LENGTH_LONG).show()
            }
        }
        MyToolbar().Show(this,this.getString(R.string.LICrear))
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home){


            val resultIntent = Intent()
            setResult(RESULT_CANCELED, resultIntent)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}