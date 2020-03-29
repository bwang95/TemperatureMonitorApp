package com.cerridan.temperaturemonitor.api

import com.cerridan.temperaturemonitor.api.response.AmbientConditions
import com.cerridan.temperaturemonitor.util.Result
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.io
import retrofit2.Retrofit
import retrofit2.http.GET

class AmbientService(retrofit: Retrofit) {
  interface AmbientAPI {
    companion object {
      const val BASE_URL = "http://192.168.1.3/"
    }

    @GET("ambient")
    fun getAmbientConditions(): Single<AmbientConditions>
  }

  private val api = retrofit.newBuilder()
      .baseUrl(AmbientAPI.BASE_URL)
      .build()
      .create(AmbientAPI::class.java)

  fun getAmbientConditions(): Single<Result<AmbientConditions>> = api
      .getAmbientConditions()
      .subscribeOn(io())
      .observeOn(mainThread())
      .map { Result.Success(it) as Result<AmbientConditions> }
      .onErrorReturn { Result.Failure(it) }
}