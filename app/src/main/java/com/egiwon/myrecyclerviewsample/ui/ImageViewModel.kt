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
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Deferred
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

    fun loadRandomImagesAndUserImages(count: Int) =
        viewModelScope.launch(coroutineExceptionHandler) {
            val recyclerItems = mutableListOf<RecyclerItem<*>>()
            val randomImagesDeferred: Deferred<Result<List<PhotoVO>>> = async {
                repository.fetchRandomImage(count)
            }

            val randomImages: Result<List<PhotoVO>> = randomImagesDeferred.await()
            randomImages
                .onSuccess { photos ->
                    val newPhotos = Photos(photos)
                    if (photos.isNotEmpty()) {
                        recyclerItems.add(RecyclerItem(ViewType.IMAGE_LIST.ordinal, newPhotos))

                        val deferred =
                            async { repository.fetchImageFromUser(photos.first().userNameId) }

                        deferred.await()
                            .onSuccess { userPhotos ->
                                val newUserPhotos =
                                    newPhotos.copy(photoList = newPhotos.photoList.map { photo ->
                                        if (photo.selected) photo.copy(userImages = userPhotos) else photo
                                    })
                                recyclerItems.add(
                                    RecyclerItem(
                                        ViewType.USER_IMAGE_LIST.ordinal,
                                        newUserPhotos
                                    )
                                )
                                recyclerItems.add(
                                    RecyclerItem(
                                        ViewType.IMAGE_LIST.ordinal,
                                        newPhotos
                                    )
                                )
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

    fun loadHighRandomImagesAndUserImages(
        count: Int
    ) = viewModelScope.launch(coroutineExceptionHandler) {
            val recyclerItems = mutableListOf<RecyclerItem<*>>()
            val randomImagesDeferred: Deferred<Result<List<PhotoVO>>> = async {
                repository.fetchRandomImage(count)
            }

            val randomImages: Result<List<PhotoVO>> = randomImagesDeferred.await()
            randomImages
                .onSuccess { photos ->
                    val newPhotos = Photos(photos)
                    if (photos.isNotEmpty()) {
                        newPhotos.photoList.forEach {
                            recyclerItems.add(RecyclerItem(ViewType.IMAGE_ITEM.ordinal, it))
                        }

                        val deferred =
                            async { repository.fetchImageFromUser(photos.first().userNameId) }

                        deferred.await()
                            .onSuccess { userPhotos ->
                                val newUserPhotos =
                                    newPhotos.copy(photoList = newPhotos.photoList.map { photo ->
                                        if (photo.selected) photo.copy(userImages = userPhotos) else photo
                                    })
                                recyclerItems.add(
                                    RecyclerItem(
                                        ViewType.USER_IMAGE_LIST.ordinal,
                                        newUserPhotos
                                    )
                                )

                                newPhotos.photoList.forEach {
                                    recyclerItems.add(
                                        RecyclerItem(ViewType.IMAGE_ITEM.ordinal, it)
                                    )
                                }

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

    fun selectProfile(selectedPosition: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        val recyclerItems: List<RecyclerItem<*>> = _recyclerItems.value ?: return@launch
        val userPhotos: Photos = recyclerItems.find {
            it.itemViewType == ViewType.USER_IMAGE_LIST.ordinal
        }?.item as? Photos ?: return@launch

        val selectedUserIdName: String = userPhotos.photoList[selectedPosition].userNameId
        val deferred =
            async { repository.fetchImageFromUser(selectedUserIdName) }

        deferred.await()
            .onSuccess { newUserPhotoList ->
                val newUserPhotos: Photos =
                    userPhotos.copy(photoList = userPhotos.photoList.mapIndexed { index, photoVO ->
                        photoVO.copy(
                            selected = index == selectedPosition,
                            userImages = if (index == selectedPosition) newUserPhotoList else emptyList()
                        )
                    })
                val newRecyclerItems: MutableList<RecyclerItem<*>> = mutableListOf()
                newRecyclerItems.addAll(
                    recyclerItems.map {
                        if (it.itemViewType == ViewType.USER_IMAGE_LIST.ordinal) {
                            RecyclerItem(it.itemViewType, newUserPhotos)
                        } else {
                            it
                        }
                    }
                )

                _recyclerItems.value = newRecyclerItems
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
