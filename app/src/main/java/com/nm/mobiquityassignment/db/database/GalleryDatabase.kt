package com.nm.mobiquityassignment.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nm.mobiquityassignment.db.dao.GalleryDao
import com.nm.mobiquityassignment.db.model.GalleryItem

@Database(entities = [GalleryItem::class], version = 1, exportSchema = false)
abstract class GalleryDatabase : RoomDatabase(){
    abstract fun galleryDao() : GalleryDao

    companion object {
        private const val DB_NAME = "note_database.db"
        @Volatile private var instance: GalleryDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            GalleryDatabase::class.java,
            DB_NAME
        ).build()
    }
}