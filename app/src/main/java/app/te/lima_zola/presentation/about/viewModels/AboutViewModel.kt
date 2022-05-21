package app.te.lima_zola.presentation.about.viewModels

import androidx.lifecycle.viewModelScope
import app.te.lima_zola.domain.settings.models.AboutMain
import app.te.lima_zola.domain.settings.use_case.AboutUseCase
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
  private val aboutUseCase: AboutUseCase
) : BaseViewModel() {
  private val _aboutResponse =
    MutableStateFlow<Resource<BaseResponse<AboutMain>>>(Resource.Default)
  val aboutResponse = _aboutResponse

  init {
    about()
  }

  fun about() {
    aboutUseCase.aboutData()
      .onEach { result ->
        _aboutResponse.value = result
      }
      .launchIn(viewModelScope)
  }


}