package com.example.mirefri

import MyToolbar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class CambiarIdioma : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambiar_idioma)


        MyToolbar().Show(this,this.getString(R.string.CITitulo))
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cambio_idioma_opciones,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home){
            var nextPage = Intent(this, LogInActivity::class.java)
            finish()
            startActivity(nextPage);
            return true
        }
        val idioma = ControladorIdioma(this)
        if(item.itemId == R.id.englishOpcion_idioma){
            idioma.updateResources("en")
            recreate()
        }
        if(item.itemId == R.id.españolOpcion_idioma){
            idioma.updateResources("es")
            recreate()
        }
        return super.onOptionsItemSelected(item)
    }


}