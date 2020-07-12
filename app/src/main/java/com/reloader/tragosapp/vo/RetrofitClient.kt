package com.reloader.tragosapp.vo

import com.google.gson.GsonBuilder
import com.reloader.tragosapp.domain.WebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    //lazy  lo inicializa con se usa
    val webService by lazy {

        Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}