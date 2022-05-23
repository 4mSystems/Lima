package app.te.lima_zola.data.auth.repository

import app.te.lima_zola.data.auth.data_source.remote.AuthRemoteDataSource
import app.te.lima_zola.domain.auth.entity.model.UserResponse
import app.te.lima_zola.domain.auth.entity.request.*
import app.te.lima_zola.domain.auth.repository.AuthRepository
import app.te.lima_zola.domain.profile.entity.UpdatePassword
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
  private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {

  override
  suspend fun logIn(request: LogInRequest) = remoteDataSource.logIn(request)

  override suspend fun socialLogin(request: SocialLogInRequest): Resource<BaseResponse<UserResponse>> =
    remoteDataSource.socialLogIn(request)

  override suspend fun changePassword(request: UpdatePassword): Resource<BaseResponse<*>> =
    remoteDataSource.changePassword(request)

  override suspend fun forgetPassword(request: ForgetPasswordRequest) =
    remoteDataSource.forgetPassword(request)

  override suspend fun register(request: RegisterRequest): Resource<BaseResponse<*>> =
    remoteDataSource.register(request)

  override suspend fun verifyAccount(request: RegisterRequest): Resource<BaseResponse<UserResponse>> =
    remoteDataSource.verifyAccount(request)

}