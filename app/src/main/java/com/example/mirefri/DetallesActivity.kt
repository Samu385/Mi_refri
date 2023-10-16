package com.example.mirefri

import Usuario
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetallesActivity : AppCompatActivity() {
    lateinit var user:Usuario
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        val producto = intent.getParcelableExtra<Productos>("Producto")
        user = intent.getParcelableExtra("Usuario") ?: Usuario()

        if(producto != null){
            val nombreTV = findViewById<TextView>(R.id.Nombre_detalles)
            val precioTV = findViewById<TextView>(R.id.Precio_detalles)
            val descripcionTV = findViewById<TextView>(R.id.Descripcion_detalles)
            val cantidadTV = findViewById<TextView>(R.id.Cantidad_int)
            val imagen = findViewById<ImageView>(R.id.imagen_detalles)


            nombreTV.text = producto.nombre;
            precioTV.text= producto.precio.toString()
            descripcionTV.text = producto.descripcion
            cantidadTV.text = producto.cantidad.toString()
            imagen.setImageResource(producto.imagenId)

            val guardarSalirbtn = findViewById<Button>(R.id.GuardarSalir_btn)
            val sumarbtn = findViewById<Button>(R.id.Sumar_btn)
            val restarbtn = findViewById<Button>(R.id.Restar_btn)
            val atrasbtn = findViewById<Button>(R.id.Salir_detalles_btn)

            atrasbtn.setOnClickListener(){
                val resultIntent = Intent(this, InventarioActivity::class.java)
                setResult(RESULT_CANCELED, resultIntent)
                resultIntent.putExtra("Usuario", user)
                finish()
                startActivity(resultIntent)
            }
            guardarSalirbtn.setOnClickListener(){
                val resultIntent = Intent(this, InventarioActivity::class.java)
                resultIntent.putExtra("Producto", producto)
                resultIntent.putExtra("Usuario", user)
                for(miProd in user.listaDeProductos){
                    if(miProd.id == producto.id){
                        miProd.cantidad =producto.cantidad
                    }
                }
                setResult(RESULT_OK, resultIntent)
                finish()
                startActivity(resultIntent)
            }
            sumarbtn.setOnClickListener(){
                producto.cantidad++;
                cantidadTV.text = producto.cantidad.toString()
            }
            restarbtn.setOnClickListener(){
                if(producto.cantidad - 1 >= 0){
                    producto.cantidad--
                    cantidadTV.text = producto.cantidad.toString()
                }
            }

        }


    }
}