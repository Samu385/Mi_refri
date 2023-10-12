package com.example.mirefri

import Usuario
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LugaresDeInteres : AppCompatActivity() {
    lateinit var user:Usuario
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lugares_de_interes)
        user = intent.getParcelableExtra("Usuario") ?: Usuario()

        val recyclerView = findViewById<RecyclerView>(R.id.Reclycle_view_LI)
        val adapter = Adapter_cv_Lugares()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.onItemClick = {
            val intent = Intent(this, DetallesLugaresDeInteres::class.java)
            intent.putExtra("Lugar", it)
            intent.putExtra("Usuario", user)
            startActivity(intent)
        }

        val atras_btn = findViewById<Button>(R.id.LIAtras_btn)

        atras_btn.setOnClickListener(){
            var nextPage = Intent(this, HomeActivity::class.java)
            nextPage.putExtra("Usuario", user)
            //finish()
            startActivity(nextPage);
        }
    }
}