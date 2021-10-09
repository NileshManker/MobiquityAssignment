package com.nm.mobiquityassignment.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface NetworkApi {

    //@GET("photos")
    //fun getGalleryItems(): Call<List<?>?>?

    companion object {

        var BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun create() : NetworkApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(NetworkApi::class.java)

        }
    }
}