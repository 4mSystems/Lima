package app.te.lima_zola.presentation.home.ui_state

import app.te.lima_zola.domain.home.models.HomeMainData

class HomeUiState(val homeMainData: HomeMainData) {

  fun setUpSlider(): List<SliderUiItemState> {
    return homeMainData.slider.map { SliderUiItemState(it) }
  }

  fun setUpCategories(): List<CategoriesUiItemState> {
    return homeMainData.categories_subcategories.map { CategoriesUiItemState(it) }
  }


}