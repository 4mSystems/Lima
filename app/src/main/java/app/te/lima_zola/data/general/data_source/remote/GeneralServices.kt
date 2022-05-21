package app.te.lima_zola.data.general.data_source.remote

import app.te.lima_zola.domain.general.entity.countries.CountryModel
import app.te.lima_zola.domain.utils.BaseResponse
import retrofit2.http.GET

interface GeneralServices {
  @GET("app/countries-govs-cities")
  suspend fun getCountries(): BaseResponse<List<CountryModel>>

}