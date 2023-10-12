package com.example.mirefri

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter_cv_compra: RecyclerView.Adapter<Adapter_cv_compra.ViewHolderComprar>() {

    var productos: MutableList<Productos> = mutableListOf(Productos(),
        Productos("Mermelada", "se come", 120, 0,1,R.drawable.jam),
        Productos("Queso", "Sale de la leche", 4000, 0,1,R.drawable.cheese),
        Productos("Harina", "se come", 5000, 0,2,R.drawable.flour),
        Productos("Bebida", "se come", 2000, 0,3,R.drawable.water),
        Productos("matequilla", "se come", 2300, 0,4,R.drawable.butter),
        Productos("Tomate", "se come", 5000, 0,5, R.drawable.tomato)
    )


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolderComprar {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_compra, viewGroup,false)
        return ViewHolderComprar(v);
    }

    override fun onBindViewHolder(holder: ViewHolderComprar, i: Int) {
        holder.itemNombre.text = productos[i].nombre
        holder.itemPrecio.text = productos[i].precio.toString()
        holder.itemDescripcion.text = productos[i].descripcion
        holder.itemImage.setImageResource(productos[i].imagenId)
        holder.btnMinus.setOnClickListener(){
            productos[i].cantidad = productos[i].cantidad - 1
            onBindViewHolder(holder,i)
        }
        holder.btnAdd.setOnClickListener(){
            productos[i].cantidad = productos[i].cantidad + 1
            onBindViewHolder(holder,i)
        }
        holder.itemCantidad.text = productos[i].cantidad.toString()

    }

    override fun getItemCount(): Int {
        return productos.size
    }
    fun CalcularToal():Int{
        var total = 0
        for (producto in productos){
            total += (producto.cantidad * producto.precio)
        }
        return total
    }
    fun GeTProductos():MutableList<Productos>{
        return productos
    }

    class ViewHolderComprar(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemNombre: TextView
        var itemPrecio: TextView
        var itemDescripcion: TextView
        var itemCantidad:TextView
        var btnMinus:Button
        var btnAdd:Button

        init{
            itemImage = itemView.findViewById(R.id.card_view_image)
            itemNombre = itemView.findViewById(R.id.card_view_nombre)
            itemPrecio = itemView.findViewById(R.id.card_view_precio)
            itemDescripcion = itemView.findViewById(R.id.card_view_descripcion)
            itemCantidad = itemView.findViewById(R.id.card_view_cant_comprar)
            btnMinus = itemView.findViewById(R.id.minus_btn)
            btnAdd = itemView.findViewById(R.id.add_btn)

        }
    }



}