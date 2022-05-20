package app.te.lima_zola.presentation.auth.sign_up

import androidx.lifecycle.viewModelScope
import app.te.lima_zola.domain.auth.entity.request.RegisterRequest
import app.te.lima_zola.domain.auth.use_case.RegisterUseCase
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
  BaseViewModel() {
  val registerRequest = RegisterRequest()
  private val _registerResponse = MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val registerResponse = _registerResponse

  fun register() {
    registerUseCase(registerRequest)
      .catch { exception -> }
      .onEach { result ->
        _registerResponse.value = result
      }
      .launchIn(viewModelScope)
  }
}