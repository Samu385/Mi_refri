package com.example.mirefri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mirefri.sampledata.ProductosBD

class Adapter_cv_compra_bd(private val productosbd: List<ProductosBD>): RecyclerView.Adapter<Adapter_cv_compra_bd.ViewHolderComprarBD>() {
    private val cantidadList: MutableList<Int> = MutableList(productosbd.size) { 0 }
    var onItemClick :((ProductosBD) -> Unit)? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): Adapter_cv_compra_bd.ViewHolderComprarBD {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_compra_bd, viewGroup,false)
        return Adapter_cv_compra_bd.ViewHolderComprarBD(v);
    }
    fun ObtenerProductos():List<ProductosBD>{

        val nuevaLista = mutableListOf<ProductosBD>()
        var i = 0
        for (producto in productosbd){
            if(cantidadList[i] > 0){
                val nuevoProd = ProductosBD(producto.ID,producto.nombre,producto.descripcion,producto.categoria,cantidadList[i],producto.precio)
                nuevaLista.add(nuevoProd)
            }
            i++
        }
        return nuevaLista
    }
    fun ResetCantidad(){
        cantidadList.fill(0)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolderComprarBD, i: Int) {
        holder.itemNombre.text = productosbd[i].nombre
        holder.itemPrecio.text = productosbd[i].precio.toString()
        holder.itemCategoria.text = productosbd[i].categoria.toString()
        holder.btnMinus.setOnClickListener(){
            if(cantidadList[i] - 1>= 0){
                cantidadList[i] -= 1
            }
            onBindViewHolder(holder,i)
        }
        holder.btnAdd.setOnClickListener(){
            cantidadList[i] += 1
            onBindViewHolder(holder,i)
        }
        holder.itemCantidad.text = cantidadList[i].toString()

        holder.itemView.setOnClickListener(){
            onItemClick?.invoke(productosbd[i])
        }
    }
    override fun getItemCount(): Int {
        return productosbd.size
    }
    class ViewHolderComprarBD(itemView: View):RecyclerView.ViewHolder(itemView){

        var itemNombre: TextView
        var itemPrecio: TextView
        var itemCategoria: TextView
        var itemCantidad: TextView
        var btnMinus: Button
        var btnAdd: Button

        init{

            itemNombre = itemView.findViewById(R.id.cv_compbd_nombre)
            itemPrecio = itemView.findViewById(R.id.cv_compbd_precio)
            itemCategoria = itemView.findViewById(R.id.cv_compbd_categoria)
            itemCantidad = itemView.findViewById(R.id.cv_compbd_cantidad)
            btnMinus = itemView.findViewById(R.id.cv_compbd_minus_btn)
            btnAdd = itemView.findViewById(R.id.cv_compbd_add_btn)

        }
    }
}