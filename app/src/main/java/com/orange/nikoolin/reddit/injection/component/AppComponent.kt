package com.orange.nikoolin.reddit.injection.component

import android.content.Context
import com.orange.nikoolin.reddit.BaseApplication
import com.orange.nikoolin.reddit.injection.module.ActivityBindingModule
import com.orange.nikoolin.reddit.injection.module.AppModule
import com.orange.nikoolin.reddit.injection.module.NetworkModule
import com.orange.nikoolin.reddit.injection.module.ViewModelFactoryModule
import com.orange.nikoolin.reddit.injection.qualifiers.ApplicationContext
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelFactoryModule::class, AndroidSupportInjectionModule::class, ActivityBindingModule::class, NetworkModule::class])
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BaseApplication>() {
        @BindsInstance
        abstract fun appContext(@ApplicationContext context: Context)

        override fun seedInstance(instance: BaseApplication?) {
            appContext(instance!!.applicationContext)
        }
    }
}
