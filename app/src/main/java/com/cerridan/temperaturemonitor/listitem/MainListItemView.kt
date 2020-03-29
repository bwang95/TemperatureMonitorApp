package com.cerridan.temperaturemonitor.listitem

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.StringRes
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT
import com.cerridan.temperaturemonitor.R
import com.cerridan.temperaturemonitor.util.bindView

@ModelView(autoLayout = MATCH_WIDTH_WRAP_HEIGHT)
class MainListItemView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
  private val nameView: TextView by bindView(R.id.tv_main_item_name)
  private val contentView: TextView by bindView(R.id.tv_main_item_content)

  init {
    inflate(context, R.layout.list_item_main, this)

    val padding = resources.getDimensionPixelSize(R.dimen.global_padding)
    setPadding(padding, padding, padding, padding)
  }

  @ModelProp
  fun setNameRes(@StringRes nameRes: Int) = nameView.setText(nameRes)

  @ModelProp
  fun setName(name: CharSequence) { nameView.text = name }

  @ModelProp
  fun setContent(content: CharSequence) { contentView.text = content }
}