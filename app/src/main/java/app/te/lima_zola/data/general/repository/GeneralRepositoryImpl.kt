package app.te.lima_zola.data.general.repository

import app.te.lima_zola.data.general.data_source.remote.GeneralRemoteDataSource
import app.te.lima_zola.data.local.preferences.AppPreferences
import app.te.lima_zola.domain.general.entity.countries.CityModel
import app.te.lima_zola.domain.general.repository.GeneralRepository
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import javax.inject.Inject

class GeneralRepositoryImpl @Inject constructor(
  private val remoteDataSource: GeneralRemoteDataSource
) : GeneralRepository {
  override suspend fun getCities(): Resource<BaseResponse<List<CityModel>>> =
    remoteDataSource.getCities()
}