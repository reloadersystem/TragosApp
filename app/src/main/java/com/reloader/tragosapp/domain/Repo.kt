package com.reloader.tragosapp.domain

import com.reloader.tragosapp.data.model.Drink
import com.reloader.tragosapp.vo.Resource

interface Repo {
    fun getTragosList(): Resource<List<Drink>>
}