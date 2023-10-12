package com.example.mirefri

import Usuario
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InventarioActivity : AppCompatActivity() {
    lateinit var user:Usuario
    override fun onCreate(savedInstanceState: Bundle?) {
        user = intent.getParcelableExtra("Usuario") ?: Usuario()
        Toast.makeText(this,"Hola " +user.nombre, Toast.LENGTH_LONG).show()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario)
        val recyclerView = findViewById<RecyclerView>(R.id.reclicleView)
        val adapter = Adapter_cv_inventario()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }
}