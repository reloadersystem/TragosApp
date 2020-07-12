package com.reloader.tragosapp.data

import com.reloader.tragosapp.data.model.Drink
import com.reloader.tragosapp.vo.Resource
import com.reloader.tragosapp.vo.RetrofitClient

class DataSource {


    suspend fun getTragoByName(tragoName: String): Resource<List<Drink>> {
        return Resource.Success(RetrofitClient.webService.getTragoByName(tragoName).drinkList)
    }

//    val generateTragosList = Resource.Success(
//        listOf(
//            Drink(
//                "https://www.recetas-argentinas.com/base/stock/Recipe/2-image/2-image_web.jpg",
//                "Margarita",
//                "con azucar, vodka"
//            ),
//            Drink(
//                "https://media.cnnchile.com/sites/2/2019/04/fernet-branca-740x430.jpg",
//                "Fernet",
//                "con coca"
//            ),
//            Drink(
//                "https://cdn.needish.com/prod-boxfish/7aff760a-76db-47b4-9594-6720a1b422b5-grpn/scale/900x600.jpg",
//                "toro",
//                "toro con pritty"
//            ),
//            Drink(
//                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT3a1WiMh1-ei_ThaWm2QrxBu-KPBvrQLGkzg&usqp=CAU",
//                "Gancia",
//                "Gancia con Sprite"
//            )
//        )
//
//    )


}