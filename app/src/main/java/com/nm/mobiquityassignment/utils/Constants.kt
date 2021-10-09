package com.nm.mobiquityassignment.utils

import com.nm.mobiquityassignment.network.NetworkApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Constants {
    companion object{
        const val BASE_URL = "https://simplifiedcoding.net/demos/"

        fun create() : NetworkApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(NetworkApi::class.java)

        }
    }
}