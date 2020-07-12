package com.reloader.tragosapp.domain

import com.reloader.tragosapp.data.model.Drink
import com.reloader.tragosapp.data.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {


    // ya no pones un {} query hace un append es decir una variable
    @GET("search.php?")
    suspend fun getTragoByName(@Query("s") tragoName: String): DrinkList
//hasta q no termina  ese codigo retorna algo

}