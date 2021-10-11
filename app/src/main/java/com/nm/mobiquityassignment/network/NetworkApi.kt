package com.nm.mobiquityassignment.network

import com.nm.mobiquityassignment.db.model.GalleryItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface NetworkApi {

    @GET("photos")
    suspend fun getGalleryItems(): Response<List<GalleryItem>>

    companion object {

        var BASE_URL = "https://jsonplaceholder.typicode.com/"

        var retrofitService: NetworkApi? = null
        fun getInstance() : NetworkApi {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(NetworkApi::class.java)
            }
            return retrofitService!!
        }
    }
}