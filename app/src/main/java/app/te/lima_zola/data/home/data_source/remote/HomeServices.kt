package app.te.lima_zola.data.home.data_source.remote

import app.te.lima_zola.domain.home.models.HomeData
import app.te.lima_zola.domain.utils.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeServices {
  @GET("v1/app/home/categories/{cat_id}")
  suspend fun homeStudent(@Path("cat_id") cat_id: Int): BaseResponse<HomeData>

}