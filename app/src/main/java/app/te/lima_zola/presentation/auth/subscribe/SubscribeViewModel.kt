package app.te.lima_zola.presentation.auth.subscribe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.te.lima_zola.domain.auth.entity.model.UserResponse
import app.te.lima_zola.domain.auth.entity.request.RegisterRequest
import app.te.lima_zola.domain.auth.use_case.VerifyAccountUseCase
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SubscribeViewModel @Inject constructor(
  private val verifyAccountUseCase: VerifyAccountUseCase,
  savedStateHandle: SavedStateHandle
) :
  BaseViewModel() {
  val uiState = SubscribeUiState()
  private val _verifyResponse =
    MutableStateFlow<Resource<BaseResponse<UserResponse>>>(Resource.Default)
  val verifyResponse = _verifyResponse


  fun verifyAccount() {
//    verifyAccountUseCase(request)
//      .onEach { result ->
//        _verifyResponse.value = result
//      }
//      .launchIn(viewModelScope)
  }

}