package com.reloader.tragosapp.domain

import com.reloader.tragosapp.data.DataSource
import com.reloader.tragosapp.data.model.Drink
import com.reloader.tragosapp.vo.Resource

class RepoImpl(private val dataSource: DataSource) : Repo {

    override fun getTragosList(): Resource<List<Drink>> {

        return dataSource.generateTragosList
    }
}