package com.example.mirefri.sampledata

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class ProductosBD(
    @PrimaryKey(autoGenerate = true)
    val ID: Int = 0,
    val nombre: String,
    val descripcion: String,
    val categoria: Categoria,
    var cantidad: Int,
    val precio: Int
) : Parcelable {
    // Métodos para Parcelable
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ID)
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
        parcel.writeSerializable(categoria)
        parcel.writeInt(cantidad)
        parcel.writeInt(precio)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductosBD> {
        override fun createFromParcel(parcel: Parcel): ProductosBD {
            return ProductosBD(parcel)
        }

        override fun newArray(size: Int): Array<ProductosBD?> {
            return arrayOfNulls(size)
        }
    }

    // Constructor para Parcelable
    private constructor(parcel: Parcel) : this(
        ID = parcel.readInt(),
        nombre = parcel.readString() ?: "",
        descripcion = parcel.readString() ?: "",
        categoria = parcel.readSerializable() as Categoria,
        cantidad = parcel.readInt(),
        precio = parcel.readInt()
    )
}

// Asegúrate de que Categoria implemente Parcelable
public enum class Categoria : Parcelable {
    Frutas,
    Vegetales,
    Bebestibles;

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ordinal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Categoria> {
        override fun createFromParcel(parcel: Parcel): Categoria {
            return values()[parcel.readInt()]
        }

        override fun newArray(size: Int): Array<Categoria?> {
            return arrayOfNulls(size)
        }
    }
}
