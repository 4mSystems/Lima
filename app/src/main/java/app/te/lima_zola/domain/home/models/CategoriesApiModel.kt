package app.te.lima_zola.domain.home.models


import androidx.annotation.Keep

@Keep
data class CategoriesApiModel(
  val name: String = "",
  val id: Int = 0,
  var image: String = "",
  var type: String = "",
)