package com.vinu.vinodassigment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vinu.vinodassigment.dao.ResponseDao
import com.vinu.vinodassigment.models.ResponseDataModel
import com.vinu.vinodassigment.utils.Converters
import com.vinu.vinodassigment.utils.EntryConverters

@Database(entities = [ResponseDataModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class, EntryConverters::class)
abstract class FeedRoomDatabase : RoomDatabase() {

    abstract fun newsDao(): ResponseDao

    companion object {
        var TEST_MODE = false

        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FeedRoomDatabase? = null

        fun getDatabase(context: Context): FeedRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance: FeedRoomDatabase?
                if (TEST_MODE) {
                    instance = Room.inMemoryDatabaseBuilder(context, FeedRoomDatabase::class.java)
                        .allowMainThreadQueries().build()
                } else {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FeedRoomDatabase::class.java,
                        "news_database"
                    ).build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}