package app.te.lima_zola.domain.general.repository

import app.te.lima_zola.domain.general.entity.countries.CityModel
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource


interface GeneralRepository {
  suspend fun getCities(): Resource<BaseResponse<List<CityModel>>>
}