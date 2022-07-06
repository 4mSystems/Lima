package app.te.lima_zola.domain.home.models

import androidx.annotation.Keep

@Keep
data class HomeData(
  val subscriber: Int = 0,
  val categories: List<CategoriesApiModel> = listOf(),
)