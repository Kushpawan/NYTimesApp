package com.kush.nytimes.modules

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kush.nytimes.networking.ApiRepository
import com.kush.nytimes.views.home.MainApiRepo
import com.kush.nytimes.views.home.MainApiRepoImp
import com.kush.nytimes.views.home.MainViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// will define all the viewmodel dependency here
val viewModelModule = module {

    fun provideMainViewModel(
        apis: MainApiRepo,
        application: Application
    ): MainViewModel {
        return MainViewModel(apis, application)
    }

    single { provideMainViewModel(get(), get()) }
    single<MainApiRepo> { MainApiRepoImp(get()) }
}

val apiModule = module {
    fun provideRepoApi(retrofit: Retrofit): ApiRepository {
        return retrofit.create(ApiRepository::class.java)
    }

    single { provideRepoApi(get()) }
}

val retrofitModule = module {

    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(cache: Cache): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .cache(cache)

        okHttpClientBuilder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
            request.addHeader("Content-Type", "application/json")
            chain.proceed(request.build())
        }
        return okHttpClientBuilder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideCache(androidApplication()) }
    single { provideHttpClient(get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }

}