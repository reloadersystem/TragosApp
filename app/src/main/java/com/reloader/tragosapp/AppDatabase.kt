package com.reloader.tragosapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reloader.tragosapp.data.model.DrinkEntity
import com.reloader.tragosapp.domain.TragosDao


@Database(entities = arrayOf(DrinkEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tragoDao(): TragosDao

    //persiste en toda la aplicacion, y una sola instancia
    companion object {

        private var INSTANCE: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {
            //? si es nula crea el builder sino es null devolve la instancia
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "tabla_tragos"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
//https://www.youtube.com/watch?v=O87zO0Ostn0&list=PLvOkTKyNiH8aOtUnOItM4yoJDFM7-2zO5&index=7
}