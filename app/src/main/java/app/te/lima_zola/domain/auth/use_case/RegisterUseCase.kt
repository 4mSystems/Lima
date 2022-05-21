package app.te.lima_zola.domain.auth.use_case

import app.te.lima_zola.domain.auth.entity.request.RegisterRequest
import app.te.lima_zola.domain.auth.repository.AuthRepository
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.presentation.base.utils.Constants
import app.te.lima_zola.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class RegisterUseCase @Inject constructor(
  private val authRepository: AuthRepository
) {

  operator fun invoke(request: RegisterRequest): Flow<Resource<BaseResponse<*>>> = flow {
    if (checkValidation(request)) {
      emit(Resource.Loading)
      val result = authRepository.register(request)
      emit(result)
    }
  }.flowOn(Dispatchers.IO)

  private fun checkValidation(request: RegisterRequest): Boolean {
    var isValid = true
    if (request.name.isEmpty()) {
      request.validation.nameError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.phone.isEmpty()) {
      request.validation.phoneError.set(Constants.EMPTY)
      isValid = false
    }

    if (request.password.isEmpty()) {
      request.validation.passwordError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.password.isEmpty()) {
      request.validation.passwordError.set(Constants.EMPTY)
      isValid = false
    }

    return isValid
  }
}