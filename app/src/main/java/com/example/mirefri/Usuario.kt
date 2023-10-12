import android.os.Parcel
import android.os.Parcelable
import com.example.mirefri.Productos
import com.example.mirefri.R

data class Usuario(
    val nombre: String,
    val apellido: String,
    val Correo:String,
    val Clave:String,
    val id: Int,
    val listaDeProductos: MutableList<Productos>

):Parcelable  {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        mutableListOf<Productos>().apply {
            parcel.readList(this, Productos::class.java.classLoader)
        }
        //mutableListOf()
    ) {
    }

    //constructor():this("Samuel", "Quiroz", "samuel@gmail.com","clave123", 0,emptyList());
    constructor():this("Samuel", "Quiroz", "samuel@gmail.com","clave123", 0, mutableListOf(
        Productos("Mermelada", "se come", 120, 1,1, R.drawable.jam),
        Productos("Tomate", "se come", 5000, 1,5, R.drawable.tomato)
    ));
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeString(Correo)
        parcel.writeString(Clave)
        parcel.writeInt(id)
        parcel.writeList(listaDeProductos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }

}