package app.te.lima_zola.domain.auth.use_case

import app.te.lima_zola.domain.account.use_case.UserLocalUseCase
import app.te.lima_zola.domain.auth.entity.model.UserResponse
import app.te.lima_zola.domain.auth.entity.request.RegisterRequest
import app.te.lima_zola.domain.auth.entity.request.VerifyAccountRequest
import app.te.lima_zola.domain.auth.repository.AuthRepository
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class VerifyAccountUseCase @Inject constructor(
  private val authRepository: AuthRepository,
  private val userLocalUseCase: UserLocalUseCase
) {

  operator fun invoke(request: RegisterRequest): Flow<Resource<BaseResponse<UserResponse>>> =
    flow {
      if (request.otp.isNotEmpty()) {
        emit(Resource.Loading)
        val result = authRepository.verifyAccount(request)
        if (result is Resource.Success) {
          userLocalUseCase.invoke(result.value.data)
          userLocalUseCase.saveUserToken(result.value.data.jwt)
        }
        emit(result)
      }
    }.flowOn(Dispatchers.IO)
}