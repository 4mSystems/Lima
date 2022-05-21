package app.te.lima_zola.presentation.account

import androidx.lifecycle.viewModelScope
import app.te.lima_zola.domain.account.use_case.AccountUseCases
import app.te.lima_zola.domain.account.use_case.UserLocalUseCase
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
  private val accountUseCases: AccountUseCases,
  private val userLocalUseCase: UserLocalUseCase
) : BaseViewModel() {

  private val _logOuResponse = MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val logOutResponse = _logOuResponse

  fun logOut() {
    accountUseCases.logOutUseCase()
      .onEach { result ->
        _logOuResponse.value = result
      }
      .launchIn(viewModelScope)
  }

  fun clearStorage() {
    viewModelScope.launch {
      userLocalUseCase.logOut()
    }
  }
}