package com.example.mirefri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.room.Room.databaseBuilder
import com.example.mirefri.sampledata.MisRegistrosDB
import java.util.Date

class Registros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registros)

        val registro_btn = findViewById<Button>(R.id.pruebaRegistro)

        registro_btn.setOnClickListener{
            //val registrosViewModel = ViewModelProvider(this).get(RegistrosViewModel::class.java)
            //registrosViewModel.insertarRegistro("Registro", "Descripcion", 3000)

            val db = databaseBuilder(
                this,
                MisRegistrosDB::class.java, "database-name"
            ).allowMainThreadQueries().build()

            val nuevoRegistro = com.example.mirefri.sampledata.RegistrosBD(
                nombre = "primer registro",
                descripcion = "Descripcion primer registro",
                dineroGastado = 1000,
                fecha = Date().toString()
            )

            db.registrosDao().insert(nuevoRegistro)
        }

    }
}