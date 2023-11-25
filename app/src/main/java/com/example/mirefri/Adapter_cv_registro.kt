package com.example.mirefri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mirefri.sampledata.RegistrosBD

class Adapter_cv_registro(private val registrosbd: List<RegistrosBD>): RecyclerView.Adapter<Adapter_cv_registro.ViewHolderRegistroBD>() {

    class ViewHolderRegistroBD(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemNombre: TextView = itemView.findViewById(R.id.cv_registro_nombre)
        var itemDescripcion: TextView =itemView.findViewById(R.id.cv_registro_descripcion)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolderRegistroBD {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_registro_bd, viewGroup,false)
        return Adapter_cv_registro.ViewHolderRegistroBD(v);
    }

    override fun getItemCount(): Int {
        return registrosbd.size
    }

    override fun onBindViewHolder(holder: ViewHolderRegistroBD, i: Int) {
        holder.itemNombre.text = registrosbd[i].nombre
        holder.itemDescripcion.text = registrosbd[i].descripcion
    }

}