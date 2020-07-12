package com.reloader.tragosapp.ui.viewmodel

import androidx.lifecycle.*
import com.reloader.tragosapp.domain.Repo
import com.reloader.tragosapp.vo.Resource
import kotlinx.coroutines.Dispatchers

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


}