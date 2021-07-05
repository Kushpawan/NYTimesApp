package com.kush.nytimes

import android.app.Application
import com.kush.nytimes.modules.apiModule
import com.kush.nytimes.modules.retrofitModule
import com.kush.nytimes.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyTimesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyTimesApplication)

            // TODO Await fix for Koin and replace the explicit invocations
            //  of loadModules() and createRootScope() with a single call to modules()
            //  (https://github.com/InsertKoinIO/koin/issues/847)
            koin.loadModules(
                listOf(
                    viewModelModule,
                    apiModule,
                    retrofitModule
                )
            )
        }

    }
}