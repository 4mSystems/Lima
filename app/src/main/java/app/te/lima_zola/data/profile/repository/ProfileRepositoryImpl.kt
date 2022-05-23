package app.te.lima_zola.data.profile.repository

import app.te.lima_zola.domain.profile.entity.UpdateProfileRequest
import app.te.lima_zola.domain.profile.repository.ProfileRepository
import app.te.lima_zola.data.profile.data_source.ProfileDataSource
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
  private val remoteDataSource: ProfileDataSource
) : ProfileRepository {

  override
  suspend fun updateProfile(request: UpdateProfileRequest) = remoteDataSource.updateProfile(request)

}