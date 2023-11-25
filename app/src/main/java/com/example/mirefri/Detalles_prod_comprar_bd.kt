package com.example.mirefri

import MyToolbar
import Usuario
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.mirefri.R.string
import com.example.mirefri.sampledata.Categoria
import com.example.mirefri.sampledata.MisProductosBD
import com.example.mirefri.sampledata.MisRegistrosDB
import com.example.mirefri.sampledata.ProductosBD
import com.example.mirefri.sampledata.RegistrosBD
import java.util.Date

class Detalles_prod_comprar_bd : AppCompatActivity() {
    lateinit var productoBD: ProductosBD
    lateinit var user:Usuario
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_prod_comprar_bd)

        productoBD = intent.getParcelableExtra<ProductosBD>("ProductoBD") ?: return
        user = intent.getParcelableExtra("Usuario") ?: Usuario()

        val nombreTV = findViewById<TextView>(R.id.detalles_prod_comp_nombre)
        val idTV = findViewById<TextView>(R.id.detalles_prod_comp_id)
        val precioTV = findViewById<TextView>(R.id.detalles_prod_comp_precio)
        val categoriaTV = findViewById<TextView>(R.id.detalles_prod_comp_categoria)
        val descripcionTV = findViewById<TextView>(R.id.detalles_prod_comp_descripcion)


        nombreTV.text = getString(string.AIdefualtNombre) + " : " + productoBD.nombre
        idTV.text = getString(string.AIdefualtID) + " : " + productoBD.ID.toString()
        precioTV.text = getString(string.AIdefaultPrecio) + " : " + productoBD.precio.toString()
        categoriaTV.text = getString(string.AICategoria) + " : " + productoBD.categoria
        descripcionTV.text = getString(string.AIdefaultDescripcion) + " : " + productoBD.descripcion

        val dbRegistro = Room.databaseBuilder<MisRegistrosDB>(
            this,
            MisRegistrosDB::class.java, "database-registro2"
        ).allowMainThreadQueries().build()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_registro)
        val adapter = Adapter_cv_registro(dbRegistro.registrosDao().getAllByProductId(productoBD.ID))

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        MyToolbar().Show(this,this.getString(R.string.AcDetallesProducto))

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detalles_productos_opciones,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            var nextPage = Intent(this, ComprarActivity::class.java).putExtra("Usuario", user)
            finish()
            startActivity(nextPage);
            return true
        }

        if(item.itemId == R.id.editarOpcion){
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_edit_producto)
            val nombreET: EditText = dialog.findViewById(R.id.nombreDialogEditar)
            val descripcionET: EditText = dialog.findViewById(R.id.descripcionDialogEditar)
            val precioET: EditText = dialog.findViewById(R.id.precioDialogEditar)
            val categoriaS: Spinner = dialog.findViewById(R.id.categoriaSpinnerEditar)
            val submitButton: Button = dialog.findViewById(R.id.submit_buttonEditar)

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

                val id = productoBD.ID //calcular id
                val productoNuevo = ProductosBD(id,nombre,descripcion,nuevaCat, 0, precio.toInt())
                //Añadir a la base de datos

                Log.i("Producto",productoNuevo.ID.toString())
                Log.i("Producto",productoNuevo.nombre)
                Log.i("Producto",productoNuevo.descripcion)
                Log.i("Producto",productoNuevo.cantidad.toString())
                Log.i("Producto",productoNuevo.categoria.toString())

                dbProductos.prodDao().update(productoNuevo)

                val dbProductosInv = Room.databaseBuilder<MisProductosBD>(
                    this,
                    MisProductosBD::class.java, "database-ProductosInventario"
                ).allowMainThreadQueries().build()

                var nuevoProdInv = dbProductosInv.prodDao().getProductById(productoNuevo.ID)
                if(nuevoProdInv != null){
                    nuevoProdInv.cantidad = productoNuevo.cantidad
                    dbProductosInv.prodDao().upsert(nuevoProdInv)
                }

                val dbRegistro = Room.databaseBuilder<MisRegistrosDB>(
                    this,
                    MisRegistrosDB::class.java, "database-registro2"
                ).allowMainThreadQueries().build()

                val idRegistro = dbRegistro.registrosDao().getMaxId() + 1//calcular id registro
                val registro = RegistrosBD(idRegistro, productoNuevo.ID, productoNuevo.nombre + getString(R.string.RActualizarParam), getString(R.string.RActualizarParamDesc),0, Date().toString())

                dbRegistro.registrosDao().insert(registro)

                val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_registro)
                val adapter = Adapter_cv_registro(dbRegistro.registrosDao().getAllByProductId(productoBD.ID))

                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = adapter

                dialog.dismiss()
            }

            dialog.show()

        }
        if(item.itemId == R.id.eliminarOpcion){
            val dbProductos = Room.databaseBuilder<MisProductosBD>(
                this,
                MisProductosBD::class.java, "database-ProductoNevera2"
            ).allowMainThreadQueries().build()
            dbProductos.prodDao().delete(productoBD)
            var nextPage = Intent(this, ComprarActivity::class.java).putExtra("Usuario", user)
            finish()
            startActivity(nextPage);

        }
        return super.onOptionsItemSelected(item)
    }
}