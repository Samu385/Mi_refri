package com.example.mirefri

import Usuario
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi

class HomeActivity : AppCompatActivity() {
    lateinit var user:Usuario
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user = intent.getParcelableExtra("Usuario") ?: Usuario()
        Toast.makeText(this,"Hola " +user.nombre,Toast.LENGTH_LONG).show()
        //user = intent.getParcelableExtra("Usuario", Usuario)!!


        val inventariobtn = findViewById<Button>(R.id.inventario_btn)
        inventariobtn.setOnClickListener(){
            val nextPage = Intent(this, InventarioActivity::class.java).putExtra("Usuario", user)
            startActivity(nextPage);
        }
        val comprarbtn = findViewById<Button>(R.id.listaCompra_btn)
        comprarbtn.setOnClickListener(){
            val nextPage = Intent(this, ComprarActivity::class.java).putExtra("Usuario", user)
            startActivity(nextPage);
        }

    }
}