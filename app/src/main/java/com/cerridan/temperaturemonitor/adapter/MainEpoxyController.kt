package com.cerridan.temperaturemonitor.adapter

import android.content.res.Resources
import com.airbnb.epoxy.TypedEpoxyController
import com.cerridan.temperaturemonitor.R
import com.cerridan.temperaturemonitor.adapter.MainEpoxyModel.Humidity
import com.cerridan.temperaturemonitor.adapter.MainEpoxyModel.Temperature
import com.cerridan.temperaturemonitor.adapter.MainEpoxyModel.UpdateTimestamp
import com.cerridan.temperaturemonitor.listitem.mainListItemView
import com.cerridan.temperaturemonitor.util.formatPhrase
import java.text.DateFormat
import kotlin.math.roundToInt

class MainEpoxyController(
  private val resources: Resources
) : TypedEpoxyController<List<MainEpoxyModel>>() {
  private val dateFormatter = DateFormat.getTimeInstance()

  override fun buildModels(data: List<MainEpoxyModel>) {
    data.forEach { model ->
      when (model) {
        is Temperature -> mainListItemView {
          id("temperature")
          nameRes(R.string.main_name_temperature)
          content(
              resources.formatPhrase(R.string.main_content_temperature) {
                put("temperature", "%1.1f".format(model.temperature))
                put("unit", model.unit)
              }
          )
        }

        is Humidity -> mainListItemView {
          id("humidity")
          nameRes(R.string.main_name_humidity)
          content(
              resources.formatPhrase(R.string.main_content_humidity) {
                put("humidity", model.percent.roundToInt())
              }
          )
        }

        is UpdateTimestamp -> mainListItemView {
          id("updated_at")
          nameRes(R.string.main_name_timestamp)
          content(dateFormatter.format(model.time))
        }
      }
    }
  }
}