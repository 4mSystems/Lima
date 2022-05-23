package app.te.lima_zola.data.profile.data_source

import app.te.lima_zola.domain.auth.entity.model.UserResponse
import app.te.lima_zola.domain.profile.entity.UpdateProfileRequest
import app.te.lima_zola.domain.utils.BaseResponse
import retrofit2.http.*

interface ProfileServices {

  @POST("v1/user/profile/update")
  suspend fun updateProfile(@Body request: UpdateProfileRequest): BaseResponse<UserResponse>

}