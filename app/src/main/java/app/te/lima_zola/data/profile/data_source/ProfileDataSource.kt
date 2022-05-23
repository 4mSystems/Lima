package app.te.lima_zola.data.profile.data_source

import app.te.lima_zola.data.remote.BaseRemoteDataSource
import app.te.lima_zola.domain.profile.entity.UpdateProfileRequest
import javax.inject.Inject

class ProfileDataSource @Inject constructor(private val apiService: ProfileServices) :
  BaseRemoteDataSource() {

  suspend fun updateProfile(request: UpdateProfileRequest) = safeApiCall {
    apiService.updateProfile(request)
  }

}