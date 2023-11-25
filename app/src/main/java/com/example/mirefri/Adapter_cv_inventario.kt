package com.example.mirefri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter_cv_inventario(private val productos: MutableList<Productos>):RecyclerView.Adapter<Adapter_cv_inventario.ViewHolder>() {

    var onItemClick :((Productos) -> Unit)? = null


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_inventario, viewGroup,false)
        return ViewHolder(v);
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.itemNombre.text = productos[i].nombre
        holder.itemDescripcion.text = productos[i].descripcion
        holder.itemImage.setImageResource(productos[i].imagenId)
        holder.itemCantidad.text = productos[i].cantidad.toString()

        holder.itemView.setOnClickListener(){
            onItemClick?.invoke(productos[i])
        }
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
            itemImage = itemView.findViewById(R.id.card_view_cantidad_inv_original)
            itemNombre = itemView.findViewById(R.id.card_view_nombre_inv_original)
            itemDescripcion = itemView.findViewById(R.id.card_view_descripcion_inv_original)
            itemCantidad = itemView.findViewById(R.id.card_view_cantidad_inv_original)

        }
    }


}