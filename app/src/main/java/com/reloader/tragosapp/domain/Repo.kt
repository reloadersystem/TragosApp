package com.reloader.tragosapp.domain

import com.reloader.tragosapp.data.model.Drink
import com.reloader.tragosapp.data.model.DrinkEntity
import com.reloader.tragosapp.vo.Resource

interface Repo {
    suspend fun getTragosList(tragoName: String): Resource<List<Drink>>

    //room
    suspend fun getTragosFavoritos():Resource<List<DrinkEntity>>
    suspend fun insertTrago(trago:DrinkEntity)
}