package com.example.mirefri.sampledata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegistrosDao {
    @Insert
    fun insert(registro: RegistrosBD)
    @Query("SELECT MAX(id) FROM RegistrosBD")
    fun getMaxId(): Int
    @Delete
    fun delete(registro: RegistrosBD)
    @Query("SELECT * FROM RegistrosBD ORDER BY fecha ASC")
    fun getAll(): List<RegistrosBD>
    @Query("SELECT * FROM RegistrosBD WHERE idProducto = :productId ORDER BY fecha ASC")
    fun getAllByProductId(productId: Int): List<RegistrosBD>
}