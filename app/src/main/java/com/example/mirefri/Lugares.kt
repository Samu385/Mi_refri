package com.example.mirefri

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes

class Lugares(
    val nombre: String,
    val direccion: String,
    val descripcion:String,
    val tipo:String,
    val id: Int,
    @DrawableRes
    val imagenId: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }
    constructor():this("Plaza de armas", "1 oriente, 1 sur", "Plaza de armas de Talca con muchas zonas verdes", "Plaza",0,R.drawable.park)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(direccion)
        parcel.writeString(descripcion)
        parcel.writeString(tipo)
        parcel.writeInt(id)
        parcel.writeInt(imagenId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Lugares> {
        override fun createFromParcel(parcel: Parcel): Lugares {
            return Lugares(parcel)
        }

        override fun newArray(size: Int): Array<Lugares?> {
            return arrayOfNulls(size)
        }
    }

}