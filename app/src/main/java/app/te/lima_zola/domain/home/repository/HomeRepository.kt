package app.te.lima_zola.domain.home.repository
import app.te.lima_zola.domain.home.models.HomeData
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource

interface HomeRepository {
  suspend fun getHome(cat_id: Int): Resource<BaseResponse<HomeData>>
}