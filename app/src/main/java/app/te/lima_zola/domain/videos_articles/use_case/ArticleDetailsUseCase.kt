package app.te.lima_zola.domain.videos_articles.use_case

import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.domain.videos_articles.entity.VideoData
import app.te.lima_zola.domain.videos_articles.repository.VideosArticlesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ArticleDetailsUseCase @Inject constructor(
    private val videosArticlesRepository: VideosArticlesRepository
) {

    operator fun invoke(doc_id: Int): Flow<Resource<BaseResponse<VideoData>>> =
        flow {
            val result = videosArticlesRepository.docDetails(doc_id)
            emit(result)
        }.flowOn(Dispatchers.IO)
}