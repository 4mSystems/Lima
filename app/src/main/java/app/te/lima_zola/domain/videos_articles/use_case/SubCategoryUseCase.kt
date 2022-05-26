package app.te.lima_zola.domain.videos_articles.use_case

import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.domain.videos_articles.entity.SubCategory
import app.te.lima_zola.domain.videos_articles.repository.VideosArticlesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class SubCategoryUseCase @Inject constructor(
  private val videosArticlesRepository: VideosArticlesRepository
) {

  suspend operator fun invoke(cat_id: Int): Resource<BaseResponse<List<SubCategory>>> = videosArticlesRepository.getSubCategories(cat_id)
}