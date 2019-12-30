package com.orange.nikoolin.reddit.ui.posts

import android.content.Context
import com.orange.nikoolin.reddit.injection.module.BaseActivityModule
import com.orange.nikoolin.reddit.injection.qualifiers.ActivityContext
import com.orange.nikoolin.reddit.injection.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.android.support.DaggerAppCompatActivity

@Module(includes = [BaseActivityModule::class])
abstract class PostsListActivityModule {

    @Binds
    @ActivityContext
    abstract fun provideActivityContext(activity: PostsListActivity): Context

    @Binds
    @ActivityScope
    abstract fun provideActivity(postsListActivity: PostsListActivity): DaggerAppCompatActivity
}
