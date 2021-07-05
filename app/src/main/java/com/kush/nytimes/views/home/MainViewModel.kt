package com.kush.nytimes.views.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.kush.nytimes.modules.BaseViewModel
import com.kush.nytimes.networking.Resource
import com.kush.nytimes.networking.Result
import com.kush.nytimes.views.ViewArticleResponse
import kotlinx.coroutines.launch

class MainViewModel(private val apis: MainApiRepo, application: Application) :
    BaseViewModel(application) {

    var articlesLiveData = MutableLiveData<Resource<ViewArticleResponse>>()


    fun getArticleCall() {
        scope.launch {
            articlesLiveData.postValue(Resource.loading())
            when (val data = apis.getViewedArticlesCall()) {
                is Result.GenericError -> articlesLiveData.postValue(Resource.error(data.error))
                is Result.Success -> articlesLiveData.postValue(Resource.success(data.value))
            }
        }
    }
}