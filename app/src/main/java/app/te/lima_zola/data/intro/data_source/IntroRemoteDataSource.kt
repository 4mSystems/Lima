package app.te.lima_zola.data.intro.data_source

import app.te.lima_zola.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class IntroRemoteDataSource @Inject constructor(private val apiService: IntroServices) :
  BaseRemoteDataSource() {

  suspend fun intro() = safeApiCall {
    apiService.intro()
  }
}