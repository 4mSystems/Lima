package app.te.lima_zola.presentation.home.ui_state

import app.te.lima_zola.domain.home.models.CategoriesApiModel

data class CategoriesUiItemState(val categoriesApiModel: CategoriesApiModel) {
  fun id(): Int = categoriesApiModel.id
//  fun categoryId(): Int = categoriesApiModel.id
  fun categoryName(): String = categoriesApiModel.name
//  fun getOfferDate(): String = categoriesApiModel.date
//  fun subCategoryList(): List<CategoriesApiModel> = categoriesApiModel.sub_categories
  fun categoryImage(): String = categoriesApiModel.image
}