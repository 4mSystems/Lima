package app.te.lima_zola.data.profile.repository

import app.te.lima_zola.domain.profile.entity.UpdateProfileRequest
import app.te.lima_zola.domain.profile.repository.ProfileRepository
import app.te.lima_zola.data.profile.data_source.ProfileDataSource
import app.te.lima_zola.domain.auth.entity.model.UserResponse
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProfileDataSource
) : ProfileRepository {
    override suspend fun getProfile(): Resource<BaseResponse<UserResponse>> =
        remoteDataSource.getProfile()


    override suspend fun updateProfile(request: UpdateProfileRequest) =
        remoteDataSource.updateProfile(request)

}