package com.egiwon.myrecyclerviewsample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egiwon.myrecyclerviewsample.data.ImageRepository
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO
import com.egiwon.myrecyclerviewsample.ui.model.Photos
import com.egiwon.myrecyclerviewsample.ui.model.RecyclerItem
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel() {

    private val _photos = MutableLiveData<List<PhotoVO>>()
    val photos: LiveData<List<PhotoVO>> get() = _photos

    private val _photos2 = MutableLiveData<List<PhotoVO>>()
    val photos2: LiveData<List<PhotoVO>> get() = _photos2

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _recyclerItems = MutableLiveData<List<RecyclerItem<*>>>()
    val recyclerItems: LiveData<List<RecyclerItem<*>>> get() = _recyclerItems

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _errorMessage.value = throwable.message
    }

    fun loadRandomImages(count: Int = 1) = viewModelScope.launch(coroutineExceptionHandler) {
        repository.fetchRandomImage(count)
            .onSuccess {
                _photos.value = it
                _photos2.value = it
            }
            .onFailure {
                _errorMessage.value = it.message
            }
    }

    fun loadImageRecyclerItems(count: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        repository.fetchRandomImage(count)
            .onSuccess {
                val recyclerItems = mutableListOf<RecyclerItem<*>>()
                recyclerItems.add(RecyclerItem(ViewType.IMAGE_LIST.ordinal, Photos(it)))
                recyclerItems.add(RecyclerItem(ViewType.USER_IMAGE_LIST.ordinal, Photos(it)))
                _recyclerItems.value = recyclerItems
            }
            .onFailure {
                _errorMessage.value = it.message
            }
    }

    fun loadRandomImagesAndUserImages(count: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        val recyclerItems = mutableListOf<RecyclerItem<*>>()
        val randomImagesDeferred: Deferred<Result<List<PhotoVO>>> = async {
            repository.fetchRandomImage(count)
        }

        val randomImages: Result<List<PhotoVO>> = randomImagesDeferred.await()
        randomImages
            .onSuccess { photos ->
                if (photos.isNotEmpty()) {
                    recyclerItems.add(RecyclerItem(ViewType.IMAGE_LIST.ordinal, Photos(photos)))
                    recyclerItems.add(RecyclerItem(ViewType.USER_IMAGE_LIST.ordinal, Photos(photos)))

                    val deferred =
                        async { repository.fetchImageFromUser(photos.first().userNameId) }

                    deferred.await()
                        .onSuccess { userPhotos ->
                            recyclerItems.add(RecyclerItem(ViewType.IMAGE_LIST.ordinal, Photos(userPhotos)))
                            _recyclerItems.value = recyclerItems
                        }
                        .onFailure {
                            _errorMessage.value = it.message
                        }
                }
            }
            .onFailure {
                _errorMessage.value = it.message
            }
    }

    fun shuffleList() {
        val newList: List<PhotoVO> = _photos.value?.shuffled() ?: emptyList()
        _photos.value = newList
        _photos2.value = newList
    }

}
