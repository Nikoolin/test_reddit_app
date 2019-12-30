package com.orange.nikoolin.reddit.injection.module

import com.orange.nikoolin.reddit.AppConstants
import com.orange.nikoolin.reddit.BuildConfig
import com.orange.nikoolin.reddit.data.services.CoroutineApiService
import com.orange.nikoolin.reddit.data.services.RxJavaApiService
import com.orange.nikoolin.reddit.repo.TopPostsRepo
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides @Singleton internal fun provideOkHttpClient(): OkHttpClient {
    val httpBuilder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
      val httpLoggingInterceptor = HttpLoggingInterceptor()
      httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
      httpBuilder.interceptors()
          .add(httpLoggingInterceptor)
    }
    return httpBuilder.build()
  }

  @Provides @Singleton @Named(AppConstants.COROUTINE_RETROFIT) internal fun provideCoroutineRestAdapter(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
  }

  @Provides @Singleton internal fun provideCoroutineApiService( @Named(AppConstants.COROUTINE_RETROFIT) restAdapter: Retrofit): CoroutineApiService {
    return restAdapter.create(CoroutineApiService::class.java)
  }

  @Provides @Singleton internal fun provideCoroutinePostsRepo(coroutineApiService: CoroutineApiService): TopPostsRepo {
    return TopPostsRepo(coroutineApiService)
  }

  @Provides @Singleton @Named(AppConstants.RX_RETROFIT) internal fun provideRxRestAdapter(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
  }

  @Provides @Singleton internal fun provideRxApiService( @Named(AppConstants.RX_RETROFIT) restAdapter: Retrofit): RxJavaApiService {
    return restAdapter.create(RxJavaApiService::class.java)
  }
}
