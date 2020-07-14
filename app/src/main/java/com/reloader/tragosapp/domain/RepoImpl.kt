package com.reloader.tragosapp.domain

import com.reloader.tragosapp.data.DataSource
import com.reloader.tragosapp.data.model.Drink
import com.reloader.tragosapp.data.model.DrinkEntity
import com.reloader.tragosapp.vo.Resource

class RepoImpl(private val dataSource: DataSource) : Repo {


    override suspend fun getTragosList(tragoName: String): Resource<List<Drink>> {

        return dataSource.getTragoByName(tragoName)
    }

    override suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>> {
return dataSource.getTragosFavoritos()
    }

    override suspend fun insertTrago(trago: DrinkEntity) {
        dataSource.insertTragoIntoRoom(trago)
    }
}