package com.orange.nikoolin.reddit.injection.module

import com.orange.nikoolin.reddit.injection.scope.ActivityScope
import com.orange.nikoolin.reddit.ui.posts.PostsListActivity
import com.orange.nikoolin.reddit.ui.posts.PostsListActivityModule
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity

/**
 * To create dependencies for a specific activity, don't extend the required activity module with #ActivityModule, instead create a plain module and include #BaseActivityModule in the annotation.
 */
@Module
abstract class ActivityBindingModule {

  @ActivityScope
  @ContributesAndroidInjector(
      modules = [PostsListActivityModule::class]
  )
  internal abstract fun bindPostsListActivity(): PostsListActivity

}

@Module(includes = [BaseActivityModule::class])
abstract class ActivityModule<in T : DaggerAppCompatActivity> {
  @Binds
  @ActivityScope
  internal abstract fun bindActivity(activity: T): DaggerAppCompatActivity
}

/**
 * Activity specific common dependencies should be placed here
 */
@Module
open class BaseActivityModule {
  @ActivityScope
  @Provides internal fun provideRxPermissions(activity: DaggerAppCompatActivity) = RxPermissions(
      activity
  )
}