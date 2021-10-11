package com.nm.mobiquityassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nm.mobiquityassignment.repositories.LoginRepository
import com.nm.mobiquityassignment.utils.Constants.Companion.USER_NAME
import com.nm.mobiquityassignment.utils.Constants.Companion.USER_PASSWORD
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val isLoginSuccess = MutableLiveData<Boolean>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    fun login (userName : String? , userPassword : String?){
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val loginRepository = LoginRepository()
            val isUserNameCorrect = loginRepository.validateUserName(userName)
            val isUserPasswordCorrect = loginRepository.validateUserPassword(userPassword)
            withContext(Dispatchers.Main) {
                loading.postValue(false)
                if (isUserNameCorrect && isUserPasswordCorrect) {
                    isLoginSuccess.postValue(true)
                } else if (!isUserNameCorrect){
                    onError(USER_NAME)
                } else if (!isUserPasswordCorrect){
                    onError(USER_PASSWORD)
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        isLoginSuccess.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
    }
}