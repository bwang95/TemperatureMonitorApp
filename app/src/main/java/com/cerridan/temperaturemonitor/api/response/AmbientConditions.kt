package com.cerridan.temperaturemonitor.api.response

import com.squareup.moshi.Json

data class AmbientConditions(
  @Json(name = "temperature") val temperature: Int,
  @Json(name = "units") val unit: String,
  @Json(name = "humidity_percent") val humidity: Int
)