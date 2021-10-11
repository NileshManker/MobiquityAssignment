package com.nm.mobiquityassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nm.mobiquityassignment.db.model.GalleryItem
import com.nm.mobiquityassignment.repositories.GalleryRepository
import kotlinx.coroutines.*

class GalleryViewModel(private val galleryRepository: GalleryRepository) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val galleryItems = MutableLiveData<List<GalleryItem>>()
    val perPageLimit = 10
    var currentIndex = 0
    val isDataStored = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun fetchGalleryItems() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val isDataInserted = galleryRepository.getGalleryDataFromServer()
            withContext(Dispatchers.Main) {
                if (isDataInserted) {
                    isDataStored.postValue(true)
                } else {
                    onError("Data not stored successfully")
                }
            }
        }
    }

    fun getGalleryItemsPerPage(){
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val nextGalleryItems: List<GalleryItem> = galleryRepository.getGalleryDataPerPage(perPageLimit, currentIndex)
            currentIndex += perPageLimit;
            withContext(Dispatchers.Main) {
                loading.postValue(false)
                galleryItems.value?.let {
                    galleryItems.postValue(it.plus(nextGalleryItems))
                } ?:let {
                    galleryItems.postValue(nextGalleryItems)
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
    }
}