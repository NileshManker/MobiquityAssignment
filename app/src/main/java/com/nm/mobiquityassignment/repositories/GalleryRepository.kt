package com.nm.mobiquityassignment.repositories

import com.nm.mobiquityassignment.db.database.GalleryDatabase
import com.nm.mobiquityassignment.db.model.GalleryItem
import com.nm.mobiquityassignment.network.NetworkApi

class GalleryRepository constructor(private val networkApi: NetworkApi,
                                    private val galleryDatabase: GalleryDatabase) {

    suspend fun getGalleryDataFromServer() :Boolean {
        networkApi.getGalleryItems()
        val response = networkApi.getGalleryItems()
        return if (response.isSuccessful) {
            galleryDatabase.galleryDao().insertGalleryData(response.body())
            true
        } else {
            false
        }
    }

    fun getGalleryDataPerPage(pageLimit: Int, offset: Int) : List<GalleryItem> =
        galleryDatabase.galleryDao().getGalleryItemPerPage(pageLimit, offset)
}