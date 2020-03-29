package com.cerridan.temperaturemonitor.adapter

import java.util.Date

sealed class MainEpoxyModel {
  class Temperature(
    val temperature: Double,
    val unit: String
  ) : MainEpoxyModel()

  class Humidity(val percent: Double) : MainEpoxyModel()

  class UpdateTimestamp(val time: Date) : MainEpoxyModel()
}