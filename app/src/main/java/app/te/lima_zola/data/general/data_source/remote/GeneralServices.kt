package app.te.lima_zola.data.general.data_source.remote

import app.te.lima_zola.domain.general.entity.countries.CityModel
import app.te.lima_zola.domain.utils.BaseResponse
import retrofit2.http.GET

interface GeneralServices {
  @GET("v1/app/cities")
  suspend fun getCities(): BaseResponse<List<CityModel>>

}