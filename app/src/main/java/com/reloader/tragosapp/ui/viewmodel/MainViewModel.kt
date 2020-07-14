package com.reloader.tragosapp.ui.viewmodel

import androidx.lifecycle.*
import com.reloader.tragosapp.data.model.DrinkEntity
import com.reloader.tragosapp.domain.Repo
import com.reloader.tragosapp.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repo) : ViewModel() {

    private val tragosData = MutableLiveData<String>()

    fun setTrago(tragonName: String) {
        tragosData.value = tragonName
    }

    init {
        setTrago("margarita")
    }


    //distinct si esque ha cambiado la imformacion
    val fetchTragosList = tragosData.distinctUntilChanged().switchMap { nombreTrago ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getTragosList(nombreTrago))

            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    fun guardarTrago(trago: DrinkEntity) {
        //limpia  lo que haya en el  proceso ejecuta corrutina con context usamos mvvm
        viewModelScope.launch {
            repo.insertTrago(trago)
        }
    }

    fun getTragosFavoritos() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getTragosFavoritos())

        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }


}