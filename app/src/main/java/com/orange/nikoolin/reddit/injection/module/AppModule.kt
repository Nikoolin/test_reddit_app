package com.orange.nikoolin.reddit.injection.module

import com.orange.nikoolin.reddit.utils.IRxSchedulers
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideRxSchedulers(): IRxSchedulers {
        return object : IRxSchedulers {
            override fun main() = AndroidSchedulers.mainThread()
            override fun io() = Schedulers.io()
        }
    }
}
