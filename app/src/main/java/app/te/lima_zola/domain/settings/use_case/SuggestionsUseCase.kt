package app.te.lima_zola.domain.settings.use_case

import app.te.lima_zola.domain.settings.models.suggestions.SuggestionsRequest
import app.te.lima_zola.domain.settings.repository.SettingsRepository
import app.te.lima_zola.domain.utils.*
import app.te.lima_zola.presentation.base.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class SuggestionsUseCase @Inject constructor(
  private val settingsRepository: SettingsRepository
) {
  operator fun invoke(
    request: SuggestionsRequest
  ): Flow<Resource<BaseResponse<*>>> = flow {

    if (checkValidation(request)) {
      emit(Resource.Loading)
      val result = settingsRepository.sendSuggestions(request)
      emit(result)
    }
  }.flowOn(Dispatchers.IO)

  private fun checkValidation(request: SuggestionsRequest): Boolean {
    var isValid = true
    if (request.name.isEmpty()) {
      request.validation.nameError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.phone.isEmpty()) {
      request.validation.phoneError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.address.isEmpty()) {
      request.validation.addressError.set(Constants.EMPTY)
      isValid = false
    }
    if (request.message.isEmpty()) {
      request.validation.messageError.set(Constants.EMPTY)
      isValid = false
    }

    return isValid
  }
}