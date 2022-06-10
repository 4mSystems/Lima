package app.te.lima_zola.domain.auth.use_case

import app.te.lima_zola.domain.auth.entity.request.*
import app.te.lima_zola.domain.auth.enums.AuthFieldsValidation
import app.te.lima_zola.domain.auth.repository.AuthRepository
import app.te.lima_zola.domain.profile.entity.UpdatePassword
import app.te.lima_zola.domain.utils.*
import app.te.lima_zola.presentation.base.utils.Constants
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
        if (checkUpdatePasswordValidation(request)) {
            if (request.password_confirmation != request.password) {
                emit(
                    Resource.Failure(
                        FailureStatus.EMPTY,
                        message = Constants.NOT_MATCH_PASSWORD.toString(),
                    )
                )
            } else {
                emit(Resource.Loading)
                val result = authRepository.changePassword(request)
                emit(result)
            }
        }
    }.flowOn(Dispatchers.IO)

    private fun checkUpdatePasswordValidation(request: UpdatePassword): Boolean {
        var isValid = true
        if (!request.isForget && request.old_password.isEmpty()) {
            request.validation.oldPasswordError.set(Constants.EMPTY)
            isValid = false
        }
        if (request.password.isEmpty()) {
            request.validation.newPasswordError.set(Constants.EMPTY)
            isValid = false
        }
        if (request.password_confirmation.isEmpty()) {
            request.validation.newPasswordConfirmError.set(Constants.EMPTY)
            isValid = false
        }
        return isValid
    }
}