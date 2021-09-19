package com.kush.nytimes.views

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.kush.nytimes.modules.BaseViewModel
import com.kush.nytimes.networking.Resource
import com.kush.nytimes.networking.Result
import kotlinx.coroutines.launch

class MainViewModel(private val apis: MainUseCase, application: Application) :
    BaseViewModel(application) {

    var articlesLiveData = MutableLiveData<Resource<ViewArticleResponse>>()


     fun getArticleCall() {
        scope.launch {
            articlesLiveData.postValue(Resource.loading())
            when (val userLogin = apis.getViewedArticlesCall()) {
                is Result.GenericError -> articlesLiveData.postValue(Resource.error(userLogin.error))
                is Result.Success -> articlesLiveData.postValue(Resource.success(userLogin.value))
            }
        }
    }
}