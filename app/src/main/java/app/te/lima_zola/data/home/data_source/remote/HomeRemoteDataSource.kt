package app.te.lima_zola.data.home.data_source.remote

import app.te.lima_zola.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(private val apiService: HomeServices) :
  BaseRemoteDataSource() {

  suspend fun homeStudent(cat_id: Int) = safeApiCall {
    apiService.homeStudent(cat_id)
  }
}