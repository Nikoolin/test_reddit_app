package com.orange.nikoolin.reddit.ui.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.orange.nikoolin.reddit.NetworkResult
import com.orange.nikoolin.reddit.data.models.Post
import com.orange.nikoolin.reddit.data.services.RxJavaApiService
import com.orange.nikoolin.reddit.injection.scope.ActivityScope
import com.orange.nikoolin.reddit.repo.TopPostsRepo
import com.orange.nikoolin.reddit.ui.base.BaseViewModel
import com.orange.nikoolin.reddit.utils.IRxSchedulers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class PostsListViewModel @Inject constructor() : BaseViewModel() {

    companion object {
        const val DOWNLOAD_POST_LIMIT = 50
    }

    @Inject
    lateinit var topPostsRepo: TopPostsRepo
    @Inject
    lateinit var rxJavaApiService: RxJavaApiService
    @Inject
    lateinit var schedulers: IRxSchedulers


    var dataLoading: MutableLiveData<Boolean> = MutableLiveData()

    var dataPosts: MutableLiveData<List<Post>> = MutableLiveData()

    fun loadDataCoroutine() {
        dataLoading.value = true
        viewModelScope.launch {
            val topPostResult = topPostsRepo.getTopPosts(DOWNLOAD_POST_LIMIT)
            dataLoading.value = false
            when (topPostResult) {
                is NetworkResult.Success -> {
                    dataPosts.value = topPostResult.body.body.children.map { it.body }
                }
                is NetworkResult.Failure -> {
                    Timber.e("onError")
                }
            }
        }
    }

    fun loadDataRx() {
        dataLoading.value = true
        addDisposable(rxJavaApiService.getTopPosts(DOWNLOAD_POST_LIMIT)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .doFinally { dataLoading.value = false }
                .subscribe({ response ->
                    dataPosts.value = response.body.children.map { it.body }
                }, { Timber.e(it) })
        )
    }
}