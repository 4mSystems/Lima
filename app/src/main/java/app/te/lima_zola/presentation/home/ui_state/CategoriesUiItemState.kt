package app.te.lima_zola.presentation.home.ui_state

import app.te.lima_zola.domain.home.models.CategoriesApiModel

data class CategoriesUiItemState(val categoriesApiModel: CategoriesApiModel) {
  fun id(): Int = categoriesApiModel.id
  fun categoryName(): String = categoriesApiModel.name
  fun categoryImage(): String = categoriesApiModel.image
  val contentType: String = categoriesApiModel.type
}