package com.cerridan.temperaturemonitor

import com.cerridan.temperaturemonitor.api.AmbientService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object DependencyGraph {
  val retrofit by lazy {
    Retrofit.Builder()
      .client(okHttpClient)
      .addConverterFactory(MoshiConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()
  }

  val okHttpClient by lazy {
    OkHttpClient.Builder()
      .addInterceptor(
        HttpLoggingInterceptor()
          .apply { level = if (BuildConfig.DEBUG) BODY else NONE }
      )
      .build()
  }

  val ambientService by lazy { AmbientService(retrofit) }
}