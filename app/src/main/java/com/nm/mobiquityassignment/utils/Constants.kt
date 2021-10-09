package com.nm.mobiquityassignment.utils

import com.nm.mobiquityassignment.network.NetworkApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Constants {
    companion object{
        const val BASE_URL = "https://simplifiedcoding.net/demos/"
        const val PREF_NAME = "gallery_pref"
        const val USER_NAME = "user_name"
        const val USER_PASSWORD = "user_password"
        const val REMEMBER_PWD = "remember_password"
        const val IS_SESSION_EXPIRED = "is_session_expired"
        const val IS_LOGGED_IN = "is_logged_in"
        const val SESSION_ID = "session_id"

        fun create() : NetworkApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(NetworkApi::class.java)

        }
    }
}