package app.te.lima_zola.domain.videos_articles.use_case

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import app.te.lima_zola.data.remote.AbstractPagingSource
import app.te.lima_zola.domain.account.use_case.UserLocalUseCase
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.domain.videos_articles.repository.VideosArticlesRepository
import app.te.lima_zola.presentation.videos.ui_state.ContentEmptyUiState
import app.te.lima_zola.presentation.videos.ui_state.MainContentUiState
import app.te.lima_zola.presentation.videos.ui_state.VideoItemUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


class VideosUseCase @Inject constructor(
    private val videosArticlesRepository: VideosArticlesRepository,
    private val userLocalUseCase: UserLocalUseCase
) {
    var isSubscribed: Boolean = false
    var isLogged: Boolean = false

    operator fun invoke(cat_id: Int): Flow<PagingData<MainContentUiState>> {
        CoroutineScope(Dispatchers.IO).launch {
            userLocalUseCase.invoke().collect {
                isLogged = it.name.isNotEmpty()
                isSubscribed = it.subscriber == 1
            }
        }
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = {
                object : AbstractPagingSource<MainContentUiState>() {

                    override suspend fun fetchData(
                        pageSize: Int,
                        PageIndex: Int
                    ): List<MainContentUiState> {

                        Log.e("fetchData", "fetchData: after")
                        val result = videosArticlesRepository.videosArticles(cat_id, PageIndex)
                        val items = mutableListOf<MainContentUiState>()
                        if (result is Resource.Success) {
                            nextPage = result.value.data.links.next
                            val data = result.value.data.data

                            if (data.isEmpty()) {
                                items.add(ContentEmptyUiState())
                            } else {
                                data.map { orderData ->
                                    orderData.userLogged = isLogged
                                    orderData.subscribed = isSubscribed
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
}