package app.te.lima_zola.data.account.data_source.remote

import app.te.lima_zola.data.remote.BaseRemoteDataSource
import app.te.lima_zola.domain.account.entity.request.SendFirebaseTokenRequest
import javax.inject.Inject

class AccountRemoteDataSource @Inject constructor(private val apiService: AccountServices) :
  BaseRemoteDataSource() {

  suspend fun sendFirebaseToken(request: SendFirebaseTokenRequest) = safeApiCall {
    apiService.sendFirebaseToken(request)
  }

  suspend fun logOut() = safeApiCall {
    apiService.logOut()
  }
}