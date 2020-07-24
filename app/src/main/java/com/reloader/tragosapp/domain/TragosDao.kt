package com.reloader.tragosapp.domain

import androidx.room.*
import com.reloader.tragosapp.data.model.Drink
import com.reloader.tragosapp.data.model.DrinkEntity

@Dao
interface TragosDao {
    //para room

    @Query("SELECT * FROM tragosEntity")
    suspend fun getAllFavoriteDrink(): List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // con onCoflict nos aseguramos que al insertar si el dato es igual con el mismo id que lo remplace
    suspend fun insertFavorite(trago: DrinkEntity)

    @Delete
    suspend fun deleteDrink(drink: DrinkEntity)


}