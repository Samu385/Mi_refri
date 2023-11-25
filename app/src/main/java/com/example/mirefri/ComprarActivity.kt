package com.example.mirefri

import MyToolbar
import Usuario
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.mirefri.sampledata.Categoria
import com.example.mirefri.sampledata.MisProductosBD
import com.example.mirefri.sampledata.MisRegistrosDB
import com.example.mirefri.sampledata.ProductosBD
import com.example.mirefri.sampledata.RegistrosBD
import java.util.Date


//val adapter = Adapter_cv_compra()
class ComprarActivity : AppCompatActivity() {
    lateinit var user:Usuario
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: Adapter_cv_compra_bd
    override fun onCreate(savedInstanceState: Bundle?) {
        user = intent.getParcelableExtra("Usuario") ?: Usuario()
        //Toast.makeText(this,"Hola " + user.nombre, Toast.LENGTH_LONG).show()


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprar)

        val dbProductos = Room.databaseBuilder<MisProductosBD>(
            this,
            MisProductosBD::class.java, "database-ProductoNevera2"
        ).allowMainThreadQueries().build()

        setRecyclerView(dbProductos.prodDao().getAllProductos())

        adapter.onItemClick = {
            val intent = Intent(this, Detalles_prod_comprar_bd::class.java)
            intent.putExtra("ProductoBD", it)
            intent.putExtra("Usuario", user)
            startActivity(intent)
        }


        val comprar_btn = findViewById<Button>(R.id.comprar_btn)
        comprar_btn.setOnClickListener(){

            val dbProductos = Room.databaseBuilder<MisProductosBD>(
                this,
                MisProductosBD::class.java, "database-ProductosInventario"
            ).allowMainThreadQueries().build()

            for(prod in adapter.ObtenerProductos()){
                var productoEncontrado = dbProductos.prodDao().getProductById(prod.ID)
                if(productoEncontrado != null){
                    productoEncontrado.cantidad += prod.cantidad
                    dbProductos.prodDao().upsert(productoEncontrado)
                }else {
                    dbProductos.prodDao().upsert(prod)
                }
            }

            adapter.ResetCantidad()
            Toast.makeText(this, "Productos añadidos", Toast.LENGTH_SHORT).show()

        }


        MyToolbar().Show(this,this.getString(R.string.AcListaCompra))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.lista_compra_opciones,menu)
        return super.onCreateOptionsMenu(menu)
    }
    fun setRecyclerView(listaProductos: List<ProductosBD> ){
        recyclerView = findViewById<RecyclerView>(R.id.reclicleView_compra)
        adapter = Adapter_cv_compra_bd(listaProductos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home){
            var nextPage = Intent(this, HomeActivity::class.java).putExtra("Usuario", user)
            finish()
            startActivity(nextPage);
            return true
        }
        if(item.itemId == R.id.opcion1){
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialoga__add_producto)

            val nombreET: EditText = dialog.findViewById(R.id.nombreDialog)
            val descripcionET: EditText = dialog.findViewById(R.id.descripcionDialog)
            val precioET: EditText = dialog.findViewById(R.id.precioDialog)
            val categoriaS: Spinner = dialog.findViewById(R.id.categoriaSpinner)
            val submitButton: Button = dialog.findViewById(R.id.submit_button)


            submitButton.setOnClickListener {
                val nombre: String = nombreET.text.toString()
                val descripcion: String = descripcionET.text.toString()
                val precio: String = precioET.text.toString()
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
                    MisProductosBD::class.java, "database-ProductoNevera2"
                ).allowMainThreadQueries().build()

                val id = dbProductos.prodDao().getMaxId() + 1 //calcular id
                val productoNuevo = ProductosBD(id,nombre,descripcion,nuevaCat, 0, precio.toInt())
                //Añadir a la base de datos

                Log.i("Producto",productoNuevo.ID.toString())
                Log.i("Producto",productoNuevo.nombre)
                Log.i("Producto",productoNuevo.descripcion)
                Log.i("Producto",productoNuevo.cantidad.toString())
                Log.i("Producto",productoNuevo.categoria.toString())

                dbProductos.prodDao().insert(productoNuevo)

                val dbRegistro = Room.databaseBuilder<MisRegistrosDB>(
                    this,
                    MisRegistrosDB::class.java, "database-registro2"
                ).allowMainThreadQueries().build()

                val idRegistro = dbRegistro.registrosDao().getMaxId() + 1//calcular id registro
                val registro = RegistrosBD(idRegistro, productoNuevo.ID, productoNuevo.nombre + getString(R.string.RCrear),getString(R.string.RCrearDesc),0, Date().toString())

                dbRegistro.registrosDao().insert(registro)
                setRecyclerView(dbProductos.prodDao().getAllProductos())

                dialog.dismiss()
            }

            dialog.show()
        }
        if(item.itemId == R.id.ordenarPor_comp){
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
                    MisProductosBD::class.java, "database-ProductoNevera2"
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
                    MisProductosBD::class.java, "database-ProductoNevera2"
                ).allowMainThreadQueries().build()
                setRecyclerView(dbProductos.prodDao().getAllProductos())
                dialog.dismiss()
            }
            
            dialog.show()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        user = intent.getParcelableExtra("Usuario") ?: Usuario()
    }



}