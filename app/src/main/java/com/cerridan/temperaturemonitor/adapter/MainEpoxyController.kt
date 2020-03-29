package com.cerridan.temperaturemonitor.adapter

import android.content.res.Resources
import com.airbnb.epoxy.TypedEpoxyController
import com.cerridan.temperaturemonitor.R
import com.cerridan.temperaturemonitor.adapter.MainEpoxyModel.Humidity
import com.cerridan.temperaturemonitor.adapter.MainEpoxyModel.Temperature
import com.cerridan.temperaturemonitor.listitem.mainListItemView
import com.cerridan.temperaturemonitor.util.formatPhrase

class MainEpoxyController(
  private val resources: Resources
) : TypedEpoxyController<List<MainEpoxyModel>>() {
  override fun buildModels(data: List<MainEpoxyModel>) {
    data.forEach { model ->
      when (model) {
        is Temperature -> mainListItemView {
          id("temperature")
          nameRes(R.string.main_name_temperature)
          content(
              resources.formatPhrase(R.string.main_content_temperature) {
                put("temperature", model.temperature)
                put("units", model.unit)
              }
          )
        }
        is Humidity -> mainListItemView {
          id("humidity")
          nameRes(R.string.main_name_humidity)
          content(
              resources.formatPhrase(R.string.main_content_humidity) {
                put("humidity", model.percent)
              }
          )
        }
      }
    }
  }
}