package app.te.lima_zola.presentation.home.ui_state

import app.te.lima_zola.domain.home.models.Slider

data class SliderUiItemState(val slider: Slider) {
  fun getSliderImage(): String = slider.image
  fun getSliderUrl(): String = slider.url
}