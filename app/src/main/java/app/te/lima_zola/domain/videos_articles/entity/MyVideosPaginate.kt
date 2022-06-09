package app.te.lima_zola.domain.videos_articles.entity

import androidx.annotation.Keep
import app.te.lima_zola.domain.general.paginate.Links
import app.te.lima_zola.domain.general.paginate.Meta
import app.te.lima_zola.domain.general.paginate.Paginate

@Keep
data class MyVideosPaginate(
    val data: List<VideoData>,
    val links: Links,
    val meta: Meta
)