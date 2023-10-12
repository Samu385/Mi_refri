package com.example.mirefri

import Usuario
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class LogInActivity : AppCompatActivity() {
    private lateinit var Usuarios : MutableList<Usuario>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        Usuarios = mutableListOf(
            Usuario(),
            Usuario("Manuel","Moscoso","mmoscos@utalca.cl", "clave123",1, emptyList())
        )
        //Default user
        val DefaultUser_btn = findViewById<Button>(R.id.UseDefaultUser_btn)

        DefaultUser_btn.setOnClickListener() {
        val usuario = Usuario();

        if(Check(usuario.nombre, usuario.Clave)){
            val nextPage = Intent(this, MainActivity::class.java).putExtra("Usuario", usuario.nombre);
            startActivity(nextPage);
        }
        }

        //Start
        val Start_btn = findViewById<Button>(R.id.Iniciar_btn)

        Start_btn.setOnClickListener(){
            val nombre = findViewById<EditText>(R.id.CorreoAC_IF).text.toString()
            val clave = findViewById<EditText>(R.id.ClaveAC_IF).text.toString()


            if(Check(nombre, clave)){
                val nextPage = Intent(this, MainActivity::class.java).putExtra("Usuario", GetUser(nombre))
                startActivity(nextPage);
            }else{
                var claveIncorrecta = false
                for (user in Usuarios){
                    if(nombre == user.nombre ){
                        Toast.makeText(this,"Clave incorrecta", Toast.LENGTH_LONG).show()
                        claveIncorrecta = true
                    }
                }
                if(claveIncorrecta){
                    Toast.makeText(this,"El nombre no existe", Toast.LENGTH_LONG).show()
                }
            }
        }


        //Create user

        val CreateUser_btn = findViewById<Button>(R.id.CreateUser_btn)

        CreateUser_btn.setOnClickListener(){
            val nextPage = Intent(this, CrearCuentaActivity::class.java)
            startActivityForResult(nextPage,1);
        }
    }
    fun Check(nombre:String, clave : String): Boolean {
        for (user in Usuarios){
            if(nombre == user.nombre && clave == user.Clave){
                return true;
            }
        }
        return false;
    }
    fun GetUser(nombre:String): Usuario? {
        for (user in Usuarios){
            if(nombre == user.nombre){
                return user;
            }
        }
        return Usuario()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == RESULT_OK){
            val nuevoUsuario = data?.getParcelableExtra<Usuario>("Usuario")
            //val nuevUsuario = data?.getParcelableExtra("Usuario",Usuario)

            Toast.makeText(this,"Nuevo usuario :" + nuevoUsuario!!.nombre,Toast.LENGTH_LONG).show()
            //Log.i("LoginActivity","Nuevo usuario :"+nuevoUsuario!!.nombre)

            Usuarios.add(Usuario(nuevoUsuario.nombre,nuevoUsuario.apellido,nuevoUsuario.Correo,nuevoUsuario.Clave,1,emptyList()
            ))
        }else{

            Toast.makeText(this,"Algo salió mal", Toast.LENGTH_LONG).show();

        }

    }
    //val nuevoUsuario = data?.getStringExtra("Usuario")
}