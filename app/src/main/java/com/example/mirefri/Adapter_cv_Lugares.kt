package com.example.mirefri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter_cv_Lugares:RecyclerView.Adapter<Adapter_cv_Lugares.ViewHolderLugares>() {

    var lugares:MutableList<Lugares> = mutableListOf( Lugares(),
        Lugares("Paris","7 oriente 1 sur","Una tienda muy grnade con todo tipo de ropa","Tienda de ropa",1,R.drawable.clothes_shop),
        Lugares("La papa","5 oriente 1 sur","Una tienda ubicada en centro con una variadad de cafe","Tienda de café",2,R.drawable.coffee_shop),
        Lugares("Pan Pan","2 oriente 3 sur","Una tienda de pan que abre desde muy temprano","Tienda de pan",3,R.drawable.bakery),
        Lugares("Lider","8 oriente 2 norte","Una tienda con todo tipo de objetos","Supermercado",4,R.drawable.store),
        Lugares("Mercado de frutas","3 oriente 1 norte","Un mercado muy grnade con todo tipo de frutas y verduras","Verdulería",5,R.drawable.market)
    )
    var onItemClick :((Lugares) -> Unit)? = null
    class ViewHolderLugares(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView = itemView.findViewById(R.id.Imagen_CV_LI)
        var itemNombre: TextView = itemView.findViewById(R.id.Nombre_CV_LI)
        var itemTipo: TextView = itemView.findViewById(R.id.Tipo_CV_LI)
        var itemDireccion: TextView =itemView.findViewById(R.id.Direccion_CV_LI)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolderLugares {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_lugares_interes, viewGroup,false)
        return ViewHolderLugares(v);
    }

    override fun getItemCount(): Int {
        return lugares.size
    }

    override fun onBindViewHolder(holder: ViewHolderLugares, i: Int) {
        holder.itemNombre.text = lugares[i].nombre
        holder.itemDireccion.text = lugares[i].direccion
        holder.itemTipo.text = lugares[i].tipo
        holder.itemImage.setImageResource(lugares[i].imagenId)

        holder.itemView.setOnClickListener(){
            onItemClick?.invoke(lugares[i])
        }
    }
}