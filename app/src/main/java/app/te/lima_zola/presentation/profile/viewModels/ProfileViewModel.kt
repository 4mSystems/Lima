package app.te.lima_zola.presentation.profile.viewModels

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import app.te.lima_zola.domain.account.use_case.UserLocalUseCase
import app.te.lima_zola.domain.auth.entity.model.UserResponse
import app.te.lima_zola.domain.profile.entity.UpdateProfileRequest
import app.te.lima_zola.domain.profile.use_case.ProfileUseCase
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseViewModel
import app.te.lima_zola.presentation.profile.ui_state.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
  private val userLocalUseCase: UserLocalUseCase,
  private val profileUseCase: ProfileUseCase
) : BaseViewModel() {

  @Bindable
  var request = UpdateProfileRequest()
  private val _profileResponse =
    MutableStateFlow<Resource<BaseResponse<UserResponse>>>(Resource.Default)
  val profileResponse = _profileResponse
  val userUiState = UserUiState(null, "")

  init {
    viewModelScope.launch {
      userLocalUseCase.invoke().collect { userLocal ->
        userUiState.userResponse = userLocal
        request.name = request.name.ifEmpty { userLocal.name }
        request.phone = userLocal.phone
      }
    }

  }

  fun updateProfile() {
    profileUseCase.invoke(request).catch { exception ->
      Log.e(
        "updateProfile",
        "updateProfile: ${exception.printStackTrace()}"
      )
    }.onEach { result ->
      _profileResponse.value = result
    }.launchIn(viewModelScope)
  }


}