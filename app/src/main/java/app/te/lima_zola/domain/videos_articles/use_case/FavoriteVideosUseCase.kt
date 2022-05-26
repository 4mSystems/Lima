package app.te.lima_zola.domain.videos_articles.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import app.te.lima_zola.data.remote.AbstractPagingSource
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.domain.videos_articles.repository.VideosArticlesRepository
import app.te.lima_zola.presentation.videos.ui_state.ContentEmptyUiState
import app.te.lima_zola.presentation.videos.ui_state.MainVideoUiState
import app.te.lima_zola.presentation.videos.ui_state.VideoItemUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class FavoriteVideosUseCase @Inject constructor(
  private val videosArticlesRepository: VideosArticlesRepository
) {

  operator fun invoke() =
    Pager(
      config = PagingConfig(pageSize = 10, enablePlaceholders = false),
      pagingSourceFactory = {
        object : AbstractPagingSource<MainVideoUiState>() {

          override suspend fun fetchData(
            pageSize: Int,
            PageIndex: Int
          ): List<MainVideoUiState> {
            val result = videosArticlesRepository.favoriteVideos(PageIndex)
            val items = mutableListOf<MainVideoUiState>()
            if (result is Resource.Success) {
              nextPage = result.value.data.links.next
              val data = result.value.data.data
              if (data.isEmpty()) {
                items.add(ContentEmptyUiState())
              } else {
                data.map { orderData ->
                  items.add(VideoItemUiState(orderData))
                }
              }

            }

            return items
          }

          override fun hasNextPages(): Boolean {
            return nextPage != null
          }
        }.getPagingSource()
      }
    ).flow.flowOn(Dispatchers.IO)

}