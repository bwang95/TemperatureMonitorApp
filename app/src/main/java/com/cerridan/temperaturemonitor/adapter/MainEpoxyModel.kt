package com.cerridan.temperaturemonitor.adapter

sealed class MainEpoxyModel {
  class Temperature(
    val temperature: Int,
    val unit: String
  ) : MainEpoxyModel()

  class Humidity(val percent: Int) : MainEpoxyModel()
}