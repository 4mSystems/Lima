package app.te.lima_zola.domain.videos_articles.entity

import androidx.annotation.Keep
import app.te.lima_zola.domain.general.paginate.Paginate
@Keep
data class MyVideosPaginate(
  val data: List<VideoData>,
) : Paginate()