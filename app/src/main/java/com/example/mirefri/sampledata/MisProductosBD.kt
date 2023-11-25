package com.example.mirefri.sampledata

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ProductosBD::class],
    version = 1
)
abstract class MisProductosBD: RoomDatabase() {
    abstract fun prodDao(): ProductosDao
}