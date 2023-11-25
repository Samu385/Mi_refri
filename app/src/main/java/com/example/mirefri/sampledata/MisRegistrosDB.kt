package com.example.mirefri.sampledata

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RegistrosBD::class], version = 1)
abstract class MisRegistrosDB: RoomDatabase() {
    abstract fun registrosDao(): RegistrosDao
}