package com.orange.nikoolin.reddit.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.orange.nikoolin.reddit.injection.scope.ViewModelScope
import com.orange.nikoolin.reddit.ui.posts.PostsListViewModel
import com.orange.nikoolin.reddit.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

  @Binds
  @IntoMap
  @ViewModelScope(PostsListViewModel::class)
  abstract fun bindPostsListViewModel(postsListViewModel: PostsListViewModel): ViewModel

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
