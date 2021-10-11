package com.nm.mobiquityassignment.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nm.mobiquityassignment.db.model.GalleryItem

@Dao
interface GalleryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGalleryData(galleryItems : List<GalleryItem>?)

    @Query("SELECT * FROM gallery_item")
    fun getGalleryItems() : List<GalleryItem>

    @Query("SELECT * FROM gallery_item LIMIT :limit OFFSET :offset")
    fun getGalleryItemPerPage(limit: Int, offset: Int): List<GalleryItem>
}