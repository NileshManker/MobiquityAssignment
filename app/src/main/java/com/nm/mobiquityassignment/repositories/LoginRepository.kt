package com.nm.mobiquityassignment.repositories

class LoginRepository{
    fun validateUserName(userName: String?): Boolean{
        return !isEmptyOrNull(userName) && userName.equals("admin")
    }

    fun validateUserPassword(userPassword: String?): Boolean{
        return !isEmptyOrNull(userPassword) &&  userPassword.equals("admin")
    }

    private fun isEmptyOrNull(text : String?) : Boolean{
        return text == null || text.isEmpty()
    }
}