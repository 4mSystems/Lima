package app.te.lima_zola.domain.auth.repository

import app.te.lima_zola.domain.auth.entity.model.UserResponse
import app.te.lima_zola.domain.auth.entity.request.*
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource

interface AuthRepository {

  suspend fun logIn(request: LogInRequest): Resource<BaseResponse<UserResponse>>
  suspend fun socialLogin(request: SocialLogInRequest): Resource<BaseResponse<UserResponse>>
  suspend fun changePassword(request: ChangePasswordRequest): Resource<BaseResponse<*>>
  suspend fun forgetPassword(request: ForgetPasswordRequest): Resource<BaseResponse<*>>
  suspend fun register(request: RegisterRequest): Resource<BaseResponse<*>>
  suspend fun verifyAccount(request: RegisterRequest): Resource<BaseResponse<UserResponse>>
}