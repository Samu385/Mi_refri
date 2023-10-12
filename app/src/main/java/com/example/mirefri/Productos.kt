package com.example.mirefri

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes

class Productos
    ( val nombre:String,
    val descripcion:String,
    val precio: Int,
    var cantidad : Int,
    @DrawableRes
    val imagenId: Int
    ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    constructor():this("Pan", "Alimento básico", 1600, 0,R.drawable.bread)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
        parcel.writeInt(precio)
        parcel.writeInt(cantidad)
        parcel.writeInt(imagenId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Productos> {
        override fun createFromParcel(parcel: Parcel): Productos {
            return Productos(parcel)
        }

        override fun newArray(size: Int): Array<Productos?> {
            return arrayOfNulls(size)
        }
    }
}