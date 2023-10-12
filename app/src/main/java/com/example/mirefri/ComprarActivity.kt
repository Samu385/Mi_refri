package com.example.mirefri

import Usuario
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
val adapter = Adapter_cv_compra()
class ComprarActivity : AppCompatActivity() {
    lateinit var user:Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        user = intent.getParcelableExtra("Usuario") ?: Usuario()
        Toast.makeText(this,"Hola " +user.nombre, Toast.LENGTH_LONG).show()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprar)
        val recyclerView = findViewById<RecyclerView>(R.id.reclicleView)
        //val adapter = Adapter_cv_compra()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val totalTextView = findViewById<TextView>(R.id.Total_tv)

        totalTextView.text = adapter.CalcularToal().toString()
        Toast.makeText(this,"Click", Toast.LENGTH_LONG).show()

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
        val totalTextView = findViewById<TextView>(R.id.Total_tv)

        totalTextView.text = adapter.CalcularToal().toString()
        Toast.makeText(this,"Click", Toast.LENGTH_LONG).show()

    }


}