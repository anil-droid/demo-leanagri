package com.demo.leanagri.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.demo.leanagri.core.Constants
import com.demo.leanagri.data.model.detail.MovieDetail

@Database(entities = [MovieDetail::class],
        version = 1, exportSchema = false)
@TypeConverters(AppConverters::class)
abstract class DemoAppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao


    companion object {

        @Volatile
        private var instance: DemoAppDatabase? = null

        fun getInstance(context: Context): DemoAppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): DemoAppDatabase {
            return Room.databaseBuilder(context, DemoAppDatabase::class.java, Constants.DB.NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                    })
                    .build()
        }
    }
}
