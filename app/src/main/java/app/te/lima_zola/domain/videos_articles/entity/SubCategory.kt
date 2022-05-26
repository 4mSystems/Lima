package app.te.lima_zola.domain.videos_articles.entity

import androidx.annotation.Keep

@Keep
data class SubCategory(
  val id: Int = 0,
  val image: String = "",
  val name: String = "",
  val selected: Boolean = false
)