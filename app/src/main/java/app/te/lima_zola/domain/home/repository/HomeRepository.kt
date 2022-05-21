package app.te.lima_zola.domain.home.repository
import app.te.lima_zola.domain.home.models.HomeMainData
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource

interface HomeRepository {
  suspend fun getHome(): Resource<BaseResponse<HomeMainData>>
}