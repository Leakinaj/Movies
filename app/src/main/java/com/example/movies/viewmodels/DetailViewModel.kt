package com.example.movies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.model.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    private val _liveDataDetail = MutableLiveData<Result>()
    val liveDataDetail: LiveData<Result>
    get() = _liveDataDetail

    private val _loadingState = MutableLiveData<StateDetail>()
    val loadingState: LiveData<StateDetail>
    get() = _loadingState

    fun getMovieDetails(movieId: Int) {
        launch {
            _loadingState.value = StateDetail.ShowLoading
            var responseMovie = RetrofitService.getFilmApi(id = movieId)
            if (responseMovie.isSuccessful){
                _liveDataDetail.value = responseMovie.body()
            }
            _loadingState.value = StateDetail.Hideloading
            _loadingState.value = StateDetail.Finish
        }
    }

    sealed class StateDetail{
        object ShowLoading: StateDetail()
        object Hideloading: StateDetail()
        object Finish: StateDetail()
    }
}