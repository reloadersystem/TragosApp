package com.reloader.tragosapp.data

import com.reloader.tragosapp.AppDatabase
import com.reloader.tragosapp.data.model.Drink
import com.reloader.tragosapp.data.model.DrinkEntity
import com.reloader.tragosapp.vo.Resource
import com.reloader.tragosapp.vo.RetrofitClient

class DataSource(private val appDatabase: AppDatabase) {


    suspend fun getTragoByName(tragoName: String): Resource<List<Drink>> {
        return Resource.Success(RetrofitClient.webService.getTragoByName(tragoName).drinkList)
    }

    suspend fun insertTragoIntoRoom(trago: DrinkEntity) {
        appDatabase.tragoDao().insertFavorite(trago)
    }

    suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>> {
        return Resource.Success(appDatabase.tragoDao().getAllFavoriteDrink())

    }


}