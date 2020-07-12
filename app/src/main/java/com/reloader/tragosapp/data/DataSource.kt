package com.reloader.tragosapp.data

import com.reloader.tragosapp.data.model.Drink
import com.reloader.tragosapp.vo.Resource

class DataSource {

    private val generateTragosList = listOf(
        Drink(
            "https://www.recetas-argentinas.com/base/stock/Recipe/2-image/2-image_web.jpg",
            "Margarita",
            "con azucar, vodka"
        ),
        Drink(
            "https://www.deliargentina.com/image/cache/catalog/product/vino/americano-gancia-aperitivo/americano-gancia-aperitivo-600x315w.jpg",
            "Fernet",
            "con coca"
        ),
        Drink(
            "https://cdn.needish.com/prod-boxfish/7aff760a-76db-47b4-9594-6720a1b422b5-grpn/scale/900x600.jpg",
            "toro",
            "toro con pritty"
        ),
        Drink(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT3a1WiMh1-ei_ThaWm2QrxBu-KPBvrQLGkzg&usqp=CAU",
            "Gancia",
            "Gancia con Sprite"
        )

    )

    fun getTragosList(): Resource<List<Drink>> {

        return Resource.Success(generateTragosList)
    }


}