package com.cerridan.temperaturemonitor.util

import android.content.res.Resources
import androidx.annotation.StringRes
import com.squareup.phrase.Phrase

fun Resources.formatPhrase(@StringRes resId: Int, formatter: Phrase.() -> Unit): CharSequence =
  Phrase.from(this, resId).apply { formatter() }.format()