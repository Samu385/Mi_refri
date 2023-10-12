package com.example.mirefri

import Usuario
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//val adapter = Adapter_cv_compra()
class ComprarActivity : AppCompatActivity() {
    lateinit var user:Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        user = intent.getParcelableExtra("Usuario") ?: Usuario()
        //Toast.makeText(this,"Hola " + user.nombre, Toast.LENGTH_LONG).show()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprar)
        val recyclerView = findViewById<RecyclerView>(R.id.reclicleView)
        val adapter = Adapter_cv_compra()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val comprar_btn = findViewById<Button>(R.id.comprar_btn)

        comprar_btn.setOnClickListener(){
            var nuevaList: MutableList<Productos> = mutableListOf()
            var index = 0
            //Toast.makeText(this, "Nueva lista: " + nuevaList.size, Toast.LENGTH_SHORT).show()

            for(productoBase in adapter.GeTProductos()){
                var tieneElProducto = false
                for(misProductos in user.listaDeProductos){
                    if(productoBase.id == misProductos.id && productoBase.cantidad > 0 ){
                        //Toast.makeText(this, "Tiene "+productoBase.nombre, Toast.LENGTH_SHORT).show()
                        misProductos.cantidad += productoBase.cantidad
                        tieneElProducto = true
                        break;
                    }
                }
                if(tieneElProducto == false && productoBase.cantidad > 0){
                    nuevaList.add(productoBase)
                    index ++
                }
            }

            for(prodNuevo in nuevaList){
                user.listaDeProductos.add(prodNuevo)
            }
            Toast.makeText(this, "Productos añadidos", Toast.LENGTH_SHORT).show()

        }


        val atras_btn = findViewById<Button>(R.id.LCAtras_btn)

        atras_btn.setOnClickListener(){
            var nextPage = Intent(this, HomeActivity::class.java).putExtra("Usuario", user)
            finish()
            startActivity(nextPage);
        }
    }

    override fun onResume() {
        super.onResume()
        user = intent.getParcelableExtra("Usuario") ?: Usuario()
    }



}