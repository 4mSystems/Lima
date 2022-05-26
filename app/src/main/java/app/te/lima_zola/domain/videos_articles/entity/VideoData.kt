package app.te.lima_zola.domain.videos_articles.entity

import androidx.annotation.Keep

@Keep
data class VideoData(
  val category: String,
  val category_id: Int,
  var fav: Boolean,
  val free: Int,
  val id: Int,
  val image: String,
  val likes: Long,
  var is_liked: Boolean,
  val name: String,
  val type: String,
  val views: Long
)