package com.mlhysrszn.analyticahousetestcase.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mlhysrszn.analyticahousetestcase.data.model.FavPlayerModel
import com.mlhysrszn.analyticahousetestcase.data.model.FavTeamModel
import com.mlhysrszn.analyticahousetestcase.util.DB_NAME

@Database(entities = [FavPlayerModel::class, FavTeamModel::class], version = 1)
abstract class FavoritesDatabase: RoomDatabase() {

    abstract fun favoritesDAO(): FavoritesDAO

    companion object {
        private var instance: FavoritesDatabase? = null

        fun getFavoritesDatabase(context: Context): FavoritesDatabase? {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context,
                        FavoritesDatabase::class.java,
                        DB_NAME)
                        .allowMainThreadQueries().build()
            }
            return instance
        }
    }
}