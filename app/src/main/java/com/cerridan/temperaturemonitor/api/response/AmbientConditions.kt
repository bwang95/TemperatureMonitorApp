package com.cerridan.temperaturemonitor.api.response

import com.squareup.moshi.Json

data class AmbientConditions(
  @Json(name = "temperature") val temperature: Double,
  @Json(name = "units") val unit: String,
  @Json(name = "humidity") val humidity: Double
)