package com.cerridan.temperaturemonitor

import android.util.Log
import androidx.lifecycle.ViewModel
import com.cerridan.temperaturemonitor.adapter.MainEpoxyModel
import com.cerridan.temperaturemonitor.adapter.MainEpoxyModel.Humidity
import com.cerridan.temperaturemonitor.adapter.MainEpoxyModel.Temperature
import com.cerridan.temperaturemonitor.api.response.AmbientConditions
import com.cerridan.temperaturemonitor.util.Result.Failure
import com.cerridan.temperaturemonitor.util.Result.Success
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

class MainViewModel : ViewModel() {
  companion object {
    private val TAG = MainViewModel::class.java.simpleName
  }

  private val ambientService = DependencyGraph.ambientService
  private val disposables = CompositeDisposable()
  private val epoxyModelSubject = BehaviorSubject.create<List<MainEpoxyModel>>()

  val epoxyModels: Observable<List<MainEpoxyModel>>
      get() = epoxyModelSubject.observeOn(mainThread())

  fun refresh() {
    ambientService
      .getAmbientConditions()
      .subscribe { result ->
        when (result) {
          is Success -> epoxyModelSubject.onNext(createEpoxyModels(result.value))
          is Failure -> Log.e(TAG, "Failed to get ambient conditions", result.throwable)
        }
      }
      .let(disposables::add)
  }

  override fun onCleared() {
    disposables.clear()
    super.onCleared()
  }

  private fun createEpoxyModels(
    ambientConditions: AmbientConditions
  ): List<MainEpoxyModel> = listOf(
    Temperature(ambientConditions.temperature, ambientConditions.unit),
    Humidity(ambientConditions.humidity)
  )
}