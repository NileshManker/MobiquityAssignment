package com.nm.mobiquityassignment.db.model

data class UserModel(var userName: String,
                var userPassword: String){

    fun authenticateUser() : Boolean{
        return userName == "admin" && userPassword == "admin"
    }
}