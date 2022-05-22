package app.te.lima_zola.presentation.settings.viewModels

import androidx.lifecycle.viewModelScope
import app.te.lima_zola.domain.settings.models.AboutData
import app.te.lima_zola.domain.settings.models.Teams
import app.te.lima_zola.domain.settings.use_case.AboutUseCase
import app.te.lima_zola.domain.settings.use_case.TeamUseCase
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
  private val aboutUseCase: AboutUseCase,
  private val teamUseCase: TeamUseCase
) : BaseViewModel() {
  private val _aboutResponse =
    MutableStateFlow<Resource<BaseResponse<AboutData>>>(Resource.Default)
  val aboutResponse = _aboutResponse
  private val _teamResponse =
    MutableStateFlow<Resource<BaseResponse<List<Teams>>>>(Resource.Default)
  val team = _teamResponse

  fun pages(page: String) {
    aboutUseCase.aboutData(page)
      .onEach { result ->
        _aboutResponse.value = result
      }
      .launchIn(viewModelScope)
  }


  fun getTeam() {
    teamUseCase.invoke()
      .onEach { result ->
        _teamResponse.value = result
      }
      .launchIn(viewModelScope)
  }


}