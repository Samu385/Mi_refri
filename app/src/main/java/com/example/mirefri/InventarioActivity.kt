package com.example.mirefri

import MyToolbar
import Usuario
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.mirefri.sampledata.Categoria
import com.example.mirefri.sampledata.MisProductosBD
import com.example.mirefri.sampledata.ProductosBD

class InventarioActivity : AppCompatActivity() {
    lateinit var user:Usuario
    lateinit var recyclerView: RecyclerView
    lateinit var adapter:Adapter_cv_inventario_bd
    override fun onCreate(savedInstanceState: Bundle?) {
        user = intent.getParcelableExtra("Usuario") ?: Usuario()

        //Toast.makeText(this,"Hola " +user.nombre, Toast.LENGTH_LONG).show()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario)

        val dbProductos = Room.databaseBuilder<MisProductosBD>(
            this,
            MisProductosBD::class.java, "database-ProductosInventario"
        ).allowMainThreadQueries().build()

        setRecyclerView(dbProductos.prodDao().getAllProductos())


        adapter.onItemClick = {
            val intent = Intent(this, Detalles_prod_inventario_bd::class.java)
            intent.putExtra("ProductoBD", it)
            intent.putExtra("Usuario", user)
            //finish()
            startActivity(intent)
        }

        MyToolbar().Show(this,this.getString(R.string.AcInventario))
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.inventario_opciones,menu)
        return super.onCreateOptionsMenu(menu)
    }
    fun setRecyclerView(listaProductos: List<ProductosBD> ){
        recyclerView = findViewById<RecyclerView>(R.id.reclicleView_inventario)
        adapter = Adapter_cv_inventario_bd(listaProductos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home){
            val nextPage = Intent(this, HomeActivity::class.java).putExtra("Usuario", user)
            finish()
            startActivity(nextPage);
            return true
        }
        if(item.itemId ==R.id.ordenarPor_inv){
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_ordenar_por)


            val nombreET: EditText = dialog.findViewById(R.id.nombreDialogOrdenar)
            val categoriaS: Spinner = dialog.findViewById(R.id.categoriaSpinnerOrdenar)
            val submitButton: Button = dialog.findViewById(R.id.submit_buttonOrdenar)
            val submitButtonNoBuscar: Button = dialog.findViewById(R.id.submit_buttonNoOrdenar)

            submitButton.setOnClickListener {
                val nombre: String = nombreET.text.toString()
                val categoria: String = categoriaS.selectedItem.toString()

                var nuevaCat = Categoria.Bebestibles

                if(Categoria.Frutas.toString() == categoria){
                    nuevaCat = Categoria.Frutas
                }else if(Categoria.Vegetales.toString() == categoria){
                    nuevaCat = Categoria.Vegetales
                }else if(Categoria.Bebestibles.toString() == categoria){
                    nuevaCat = Categoria.Bebestibles
                }
                val dbProductos = Room.databaseBuilder<MisProductosBD>(
                    this,
                    MisProductosBD::class.java, "database-ProductosInventario"
                ).allowMainThreadQueries().build()

                if(nombre == ""){
                    setRecyclerView(dbProductos.prodDao().getProductosByCategoria(nuevaCat))
                }else{
                    setRecyclerView(dbProductos.prodDao().getProductosByCategoriaYNombre(nuevaCat, nombre))
                }
                dialog.dismiss()
            }
            submitButtonNoBuscar.setOnClickListener{
                val dbProductos = Room.databaseBuilder<MisProductosBD>(
                    this,
                    MisProductosBD::class.java, "database-ProductosInventario"
                ).allowMainThreadQueries().build()
                setRecyclerView(dbProductos.prodDao().getAllProductos())
                dialog.dismiss()
            }

            dialog.show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 2 && resultCode == RESULT_OK){
            val prodNuevosValores = data?.getParcelableExtra<Productos>("Producto")
            if(prodNuevosValores != null){
                for (misProductos in user.listaDeProductos){
                    if(misProductos.id == prodNuevosValores.id){
                        misProductos.cantidad = prodNuevosValores.cantidad
                        Toast.makeText(this,"Valor actualizado", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }else{
            Toast.makeText(this,"Algo salió mal", Toast.LENGTH_SHORT).show()
        }
    }
}