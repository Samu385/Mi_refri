package com.example.mirefri

import Usuario
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetallesLugaresDeInteres : AppCompatActivity() {
    lateinit var user:Usuario
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_lugares_de_interes)
        user = intent.getParcelableExtra("Usuario") ?: Usuario()
        val lugar = intent.getParcelableExtra<Lugares>("Lugar")

        if(lugar != null){
            val nombre = findViewById<TextView>(R.id.Nombre_LI_Detalles)
            val tipo = findViewById<TextView>(R.id.Tipo_LI_Detalles)
            val direccion = findViewById<TextView>(R.id.Direccion_LI_Detalles)
            val descripcion = findViewById<TextView>(R.id.Descripcion_LI_Detalles)
            val imagen = findViewById<ImageView>(R.id.Imagen_LI_detalles)

            //Toast.makeText(this,"Nombre " + lugar.nombre ,Toast.LENGTH_SHORT).show()
            nombre.text = lugar.nombre
            tipo.text = lugar.tipo
            direccion.text = lugar.direccion
            descripcion.text = lugar.descripcion
            imagen.setImageResource(lugar.imagenId)
        }
        val atrasBtn = findViewById<Button>(R.id.Atras_btn_LI)

        atrasBtn.setOnClickListener(){
            var nextPage = Intent(this, LugaresDeInteres::class.java)
            nextPage.putExtra("Usuario",user)
            finish()
            startActivity(nextPage);

        }

    }
}