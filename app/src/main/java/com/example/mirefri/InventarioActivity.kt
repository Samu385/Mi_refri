package com.example.mirefri

import Usuario
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InventarioActivity : AppCompatActivity() {
    lateinit var user:Usuario
    override fun onCreate(savedInstanceState: Bundle?) {
        user = intent.getParcelableExtra("Usuario") ?: Usuario()
        //Toast.makeText(this,"Hola " +user.nombre, Toast.LENGTH_LONG).show()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario)
        val recyclerView = findViewById<RecyclerView>(R.id.reclicleView)
        val adapter = Adapter_cv_inventario(user.listaDeProductos)
        //Toast.makeText(this,"tamaño " +user.listaDeProductos.size, Toast.LENGTH_LONG).show()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.onItemClick = {
            val intent = Intent(this, DetallesActivity::class.java)
            intent.putExtra("Producto", it)
            startActivityForResult(intent,2)
            //startActivity(intent)
        }


        val atras_btn = findViewById<Button>(R.id.IAtras_btn)

        atras_btn.setOnClickListener(){
            var nextPage = Intent(this, HomeActivity::class.java).putExtra("Usuario", user)
            finish()
            startActivity(nextPage);
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 2 && resultCode == RESULT_OK){
            val prodNuevosValores = data?.getParcelableExtra<Productos>("Producto")
            if(prodNuevosValores != null){
                for (misProductos in user.listaDeProductos){
                    if(misProductos.id == prodNuevosValores?.id){
                        misProductos.cantidad = prodNuevosValores.cantidad
                    }
                }
            }


        }
    }
}