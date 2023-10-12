package com.example.mirefri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.translation.ViewTranslationRequest
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter_cv_inventario:RecyclerView.Adapter<Adapter_cv_inventario.ViewHolder>() {

    var productos: MutableList<Productos> = mutableListOf(Productos(),
        Productos("Mermelada", "se come", 120, 0,R.drawable.jam),
        Productos("Queso", "Sale de la leche", 4000, 0,R.drawable.cheese),
        Productos("Harina", "se come", 5000, 0,R.drawable.flour),
        Productos("Bebida", "se come", 2000, 0,R.drawable.water),
        Productos("matequilla", "se come", 2300, 0,R.drawable.butter),
        )


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_inventario, viewGroup,false)
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.itemNombre.text = productos[i].nombre
        holder.itemDescripcion.text = productos[i].descripcion
        holder.itemImage.setImageResource(productos[i].imagenId)
        holder.itemCantidad.text = productos[i].cantidad.toString()

    }

    override fun getItemCount(): Int {
        return productos.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemNombre: TextView
        var itemDescripcion: TextView
        var itemCantidad:TextView

        init{
            itemImage = itemView.findViewById(R.id.card_view_image)
            itemNombre = itemView.findViewById(R.id.card_view_nombre)
            itemDescripcion = itemView.findViewById(R.id.card_view_descripcion)
            itemCantidad = itemView.findViewById(R.id.card_view_cantidad)

        }
    }

}