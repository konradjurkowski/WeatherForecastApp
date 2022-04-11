package com.konradjurkowski.weatherforecast.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.konradjurkowski.weatherforecast.model.Favorite

private const val DATABASE_VERSION = 1

@Database(
    entities = [Favorite::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class WeatherDatabase: RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "WeatherDatabase"

        fun buildDatabase(context: Context): WeatherDatabase {
            return Room.databaseBuilder(
                context,
                WeatherDatabase::class.java,
                DATABASE_NAME
            )
                .build()
        }
    }

    abstract fun weatherDao(): WeatherDao
}
