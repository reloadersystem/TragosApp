package com.reloader.tragosapp.domain

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reloader.tragosapp.data.model.DrinkEntity

interface TragosDao {
    //para room

    @Query("SELECT * FROM tragosEntity")
    suspend fun getAllFavoriteDrink(): List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // con onCoflict nos aseguramos que al insertar si el dato es igual con el mismo id que lo remplace
    suspend fun insertFavorite(trago: DrinkEntity)


}