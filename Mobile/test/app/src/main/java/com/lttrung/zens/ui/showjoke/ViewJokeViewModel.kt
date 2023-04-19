package com.lttrung.zens.ui.showjoke

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lttrung.zens.domain.locals.room.entities.Joke
import com.lttrung.zens.domain.usecases.ViewJokeUseCase
import com.lttrung.zens.domain.usecases.VoteJokeUseCase
import com.lttrung.zens.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewJokeViewModel @Inject constructor(
    private val viewJokeUseCase: ViewJokeUseCase,
    private val voteJokeUseCase: VoteJokeUseCase
) : ViewModel() {
    private val _viewJokeLiveData: MutableLiveData<Resource<Joke>> by lazy {
        MutableLiveData<Resource<Joke>>()
    }
    internal val viewJokeLiveData get() = _viewJokeLiveData
    private var viewJokeDisposable: Disposable? = null
    private val composite: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private val viewJokeObserver: Consumer<Joke> by lazy {
        Consumer {
            _viewJokeLiveData.postValue(Resource.Success(it))
        }
    }

    internal fun viewJoke() {
        viewModelScope.launch(Dispatchers.IO) {
            _viewJokeLiveData.postValue(Resource.Loading())
            viewJokeDisposable?.let { composite.remove(it) }
            viewJokeDisposable = viewJokeUseCase.execute().observeOn(AndroidSchedulers.mainThread())
                .subscribe(viewJokeObserver) {
                    _viewJokeLiveData.postValue(Resource.Error(it))
                }
            viewJokeDisposable?.let { composite.add(it) }
        }
    }


    private val _voteJokeLiveData: MutableLiveData<Resource<Int>> by lazy {
        MutableLiveData<Resource<Int>>()
    }
    internal val voteJokeLiveData get() = _voteJokeLiveData
    private var voteJokeDisposable: Disposable? = null
    private val voteJokeObserver: Consumer<Int> by lazy {
        Consumer {
            _voteJokeLiveData.postValue(Resource.Success(it))
        }
    }

    internal fun voteJoke(jokeId: Int, isFunny: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _voteJokeLiveData.postValue(Resource.Loading())
            voteJokeDisposable?.let { composite.remove(it) }
            voteJokeDisposable =
                voteJokeUseCase.execute(jokeId, isFunny).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(voteJokeObserver) {
                        _voteJokeLiveData.postValue(Resource.Error(it))
                    }
            voteJokeDisposable?.let { composite.add(it) }
        }
    }
}