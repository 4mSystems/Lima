package app.te.lima_zola.domain.auth.use_case

import app.te.lima_zola.domain.auth.entity.request.*
import app.te.lima_zola.domain.auth.enums.AuthFieldsValidation
import app.te.lima_zola.domain.auth.repository.AuthRepository
import app.te.lima_zola.domain.profile.entity.UpdatePassword
import app.te.lima_zola.domain.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ChangePasswordUseCase @Inject constructor(
  private val authRepository: AuthRepository
) {

  @Throws(ChangePasswordValidationException::class)
  operator fun invoke(request: UpdatePassword): Flow<Resource<BaseResponse<*>>> = flow {
    if (request.password.isEmpty()) {
      throw ChangePasswordValidationException(AuthFieldsValidation.EMPTY_PASSWORD.value.toString())
    }

    if (request.password_confirmation.isEmpty()) {
      throw ChangePasswordValidationException(AuthFieldsValidation.EMPTY_CONFIRM_PASSWORD.value.toString())
    }

    emit(Resource.Loading)
    val result = authRepository.changePassword(request)
    emit(result)
  }.flowOn(Dispatchers.IO)

}