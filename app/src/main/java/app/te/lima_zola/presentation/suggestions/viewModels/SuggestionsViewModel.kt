package app.te.lima_zola.presentation.suggestions.viewModels

import androidx.lifecycle.viewModelScope
import app.te.lima_zola.domain.settings.models.suggestions.SuggestionsRequest
import app.te.lima_zola.domain.settings.use_case.SuggestionsUseCase
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SuggestionsViewModel @Inject constructor(
  private val suggestionsUseCase: SuggestionsUseCase
) : BaseViewModel() {
  val contactRequest = SuggestionsRequest()
  private val _suggestionsResponse =
    MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val suggestions = _suggestionsResponse

  fun sendSuggestions() {
    suggestionsUseCase.invoke(contactRequest)
      .onEach { result ->
        _suggestionsResponse.value = result
      }
      .launchIn(viewModelScope)
  }


}