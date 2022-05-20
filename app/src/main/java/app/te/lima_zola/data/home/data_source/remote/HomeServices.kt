package app.te.lima_zola.data.home.data_source.remote

import app.te.lima_zola.domain.home.models.HomeMainData
import app.te.lima_zola.domain.utils.BaseResponse
import retrofit2.http.GET

interface HomeServices {
  @GET("app/categories_subcategories")
  suspend fun homeStudent(): BaseResponse<HomeMainData>

}