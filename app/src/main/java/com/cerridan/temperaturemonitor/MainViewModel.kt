package com.cerridan.temperaturemonitor

import androidx.lifecycle.ViewModel
import com.cerridan.temperaturemonitor.adapter.MainEpoxyModel
import com.cerridan.temperaturemonitor.api.response.AmbientConditions
import com.cerridan.temperaturemonitor.util.Result.Failure
import com.cerridan.temperaturemonitor.util.Result.Success
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

class MainViewModel : ViewModel() {
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
          is Failure -> {}
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
  ): List<MainEpoxyModel> {
    return emptyList()
  }
}