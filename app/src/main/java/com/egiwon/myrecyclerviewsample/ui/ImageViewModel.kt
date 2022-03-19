package com.egiwon.myrecyclerviewsample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egiwon.myrecyclerviewsample.data.ImageRepository
import com.egiwon.myrecyclerviewsample.ui.model.PhotoVO
import com.egiwon.myrecyclerviewsample.ui.model.Photos
import com.egiwon.myrecyclerviewsample.ui.model.RecyclerItem
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
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

    private val compositeDisposable = CompositeDisposable()

    fun loadRandomImages(count: Int = 1) {
        fetchRandomImages(count)
            .subscribeBy(
                onSuccess = {
                    _photos.value = it
                    _photos2.value = it
                },
                onError = {
                    _errorMessage.value = it.message
                }
            ).addTo(compositeDisposable)
    }

    private fun fetchRandomImages(count: Int) = repository.fetchRandomImage(count)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun loadImageRecyclerItems(count: Int) {
        fetchRandomImages(count)
            .subscribeBy(
                onSuccess = {
                    val recyclerItems = mutableListOf<RecyclerItem<*>>()
                    recyclerItems.add(RecyclerItem(ViewType.IMAGE_LIST.ordinal, Photos(it)))
                    recyclerItems.add(RecyclerItem(ViewType.USER_IMAGE_LIST.ordinal, Photos(it)))
                    _recyclerItems.value = recyclerItems
                },
                onError = {}
            ).addTo(compositeDisposable)
    }

    fun shuffleList() {
        val newList: List<PhotoVO> = _photos.value?.shuffled() ?: emptyList()
        _photos.value = newList
        _photos2.value = newList
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
