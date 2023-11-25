package com.example.mirefri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mirefri.sampledata.ProductosBD

class Adapter_cv_inventario_bd(private val productos: List<ProductosBD>): RecyclerView.Adapter<Adapter_cv_inventario_bd.ViewHolder_inventario_bd>() {

    var onItemClick :((ProductosBD) -> Unit)? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder_inventario_bd {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_inventario_bd, viewGroup,false)
        return Adapter_cv_inventario_bd.ViewHolder_inventario_bd(v);
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    override fun onBindViewHolder(holder: ViewHolder_inventario_bd, i: Int) {
        holder.itemNombre.text = productos[i].nombre
        holder.itemCategoria.text = productos[i].categoria.toString()
        holder.itemDescripcion.text = productos[i].descripcion
        holder.itemCantidad.text = productos[i].cantidad.toString()

        holder.itemView.setOnClickListener(){
            onItemClick?.invoke(productos[i])
        }
    }

    class ViewHolder_inventario_bd(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemNombre: TextView
        var itemCategoria: TextView
        var itemDescripcion: TextView

        var itemCantidad: TextView
        init{
            itemNombre = itemView.findViewById(R.id.card_view_nombre_inv_bd)
            itemCategoria = itemView.findViewById(R.id.card_view_categoria_inv_bd)
            itemDescripcion = itemView.findViewById(R.id.card_view_descripcion_inv_bd)
            itemCantidad = itemView.findViewById(R.id.card_view_cantidad_inv_bd)

        }
    }

}