package app.te.lima_zola.domain.profile.use_case

import app.te.lima_zola.domain.account.use_case.UserLocalUseCase
import app.te.lima_zola.domain.auth.entity.model.UserResponse
import app.te.lima_zola.domain.profile.entity.UpdateProfileRequest
import app.te.lima_zola.domain.profile.repository.ProfileRepository
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class UpdateProfileUseCase @Inject constructor(
  private val profileRepository: ProfileRepository,
  private val userLocalUseCase: UserLocalUseCase
) {

  operator fun invoke(
    request: UpdateProfileRequest
  ): Flow<Resource<BaseResponse<UserResponse>>> = flow {
    if (checkValidation(request)) {
      emit(Resource.Loading)
      val result = profileRepository.updateProfile(request)
      if (result is Resource.Success) {
        userLocalUseCase.invoke(result.value.data)
      }
      emit(result)
    }
  }.flowOn(Dispatchers.IO)

  private fun checkValidation(request: UpdateProfileRequest): Boolean {
    var isValid = true
    if (request.name.isEmpty()) {
      request.validation.nameError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.phone.isEmpty()) {
      request.validation.phoneError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.city_id.isEmpty()) {
      request.validation.cityError.set(Constants.EMPTY)
      isValid = false
    }

    return isValid
  }
}