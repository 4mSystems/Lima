package app.te.lima_zola.domain.profile.repository

import app.te.lima_zola.domain.auth.entity.model.UserResponse
import app.te.lima_zola.domain.profile.entity.UpdateProfileRequest
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource

interface ProfileRepository {
  suspend fun updateProfile(request: UpdateProfileRequest): Resource<BaseResponse<UserResponse>>
}