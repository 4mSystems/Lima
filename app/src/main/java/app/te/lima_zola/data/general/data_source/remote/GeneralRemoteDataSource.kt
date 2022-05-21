package app.te.lima_zola.data.general.data_source.remote

import app.te.lima_zola.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class GeneralRemoteDataSource @Inject constructor(private val apiService: GeneralServices) :
  BaseRemoteDataSource() {
  suspend fun getCities() = safeApiCall {
    apiService.getCities()
  }
}