package com.example.mirefri.sampledata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Upsert

@Dao
interface ProductosDao {
    @Insert
    fun insert(producto: ProductosBD)
    @Upsert
    fun upsert(productos: ProductosBD)
    @Upsert
    fun upsertMany(productos: List<ProductosBD>)
    @Update
    fun update(producto: ProductosBD)
    @Delete
    fun delete(producto: ProductosBD)
    @Query("SELECT MAX(ID) FROM productos")
    fun getMaxId(): Int
    @Query("SELECT * FROM productos")
    fun getAllProductos(): List<ProductosBD>
    @Query("SELECT * FROM productos WHERE id = :productId")
    fun getProductById(productId: Int): ProductosBD?
    @Query("SELECT * FROM productos WHERE categoria = :categoria")
    fun getProductosByCategoria(categoria: Categoria): List<ProductosBD>
    @Query("SELECT * FROM productos WHERE categoria = :categoria AND nombre LIKE :nombrePrefix||'%'")
    fun getProductosByCategoriaYNombre(categoria: Categoria, nombrePrefix: String): List<ProductosBD>
    @Query("SELECT * FROM productos ORDER BY nombre ASC")
    fun getProductosByNameASC(): List<ProductosBD>

    @Query("SELECT * FROM productos ORDER BY nombre DESC")
    fun getProductosByNameDESC(): List<ProductosBD>
}
