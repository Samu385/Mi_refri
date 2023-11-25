package com.example.mirefri.sampledata

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class RegistrosBD(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val idProducto: Int? = 0,
    val nombre: String? = "",
    val descripcion: String? = "",
    val dineroGastado: Int? = 0,
    val fecha: String? = Date().toString()
) : Parcelable {
    // Métodos para Parcelable
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(idProducto)
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
        parcel.writeValue(dineroGastado)
        parcel.writeString(fecha)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RegistrosBD> {
        override fun createFromParcel(parcel: Parcel): RegistrosBD {
            return RegistrosBD(parcel)
        }

        override fun newArray(size: Int): Array<RegistrosBD?> {
            return arrayOfNulls(size)
        }
    }

    // Constructor para Parcelable
    private constructor(parcel: Parcel) : this(
        id = parcel.readValue(Int::class.java.classLoader) as? Int,
        idProducto = parcel.readValue(Int::class.java.classLoader) as? Int,
        nombre = parcel.readString(),
        descripcion = parcel.readString(),
        dineroGastado = parcel.readValue(Int::class.java.classLoader) as? Int,
        fecha = parcel.readString()
    )
}
