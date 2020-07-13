package com.reloader.tragosapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(

    @SerializedName("idDrink")
    val tragoId: String = "",
    @SerializedName("strDrinkThumb") // mi modelo armado pero para no hacer jsonObject = getJsonObject al serializar , lo busca  y trae esos datos con ese nombre
    val imagen: String = "",
    @SerializedName("strDrink")
    val nombre: String = "",
    @SerializedName("strInstructions")
    val descripcion: String = "",

    @SerializedName("strAlcoholic")
    val hasAlcohol: String = "Non_Alcoholic"
) : Parcelable
// te permite enviar un objeto completo y lo podes utilizar para un bundle


data class DrinkList(
    @SerializedName("drinks") //  es el contenedor JSONArray
    val drinkList: List<Drink>
)

//room

//@Entity // x si deseo ponerle un nombre  diferente a la tabla y en el dao cambiamos el nombre
@Entity(tableName= "tragosEntity")
data class DrinkEntity(

    @PrimaryKey()
    val tragoId: String,

    @ColumnInfo(name="trago_imagen") // mi modelo armado pero para no hacer jsonObject = getJsonObject al serializar , lo busca  y trae esos datos con ese nombre
    val imagen: String = "",
    @ColumnInfo(name="trago_nombre") // name arguments creamos el entity con dataclass crea la tabla
    val nombre: String = "",
    @ColumnInfo(name="trago_description")
    val descripcion: String = "",

    @ColumnInfo(name="trago_has_acohol")
    val hasAlcohol: String = "Non_Alcoholic"

)
