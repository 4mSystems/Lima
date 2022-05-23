package app.te.lima_zola.presentation.settings.viewModels

import androidx.lifecycle.viewModelScope
import app.te.lima_zola.domain.settings.models.AboutData
import app.te.lima_zola.domain.settings.models.ContactUs
import app.te.lima_zola.domain.settings.models.Teams
import app.te.lima_zola.domain.settings.use_case.AboutUseCase
import app.te.lima_zola.domain.settings.use_case.ContactUseCase
import app.te.lima_zola.domain.settings.use_case.TeamUseCase
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseViewModel
import app.te.lima_zola.presentation.contactus.ContactUsEventListeners
import app.te.lima_zola.presentation.contactus.ContactUsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
  private val aboutUseCase: AboutUseCase,
  private val teamUseCase: TeamUseCase,
  private val contactUseCase: ContactUseCase,
) : BaseViewModel() {
  private val _aboutResponse =
    MutableStateFlow<Resource<BaseResponse<AboutData>>>(Resource.Default)
  val aboutResponse = _aboutResponse
  private val _teamResponse =
    MutableStateFlow<Resource<BaseResponse<List<Teams>>>>(Resource.Default)
  val team = _teamResponse
  private val _contactResponse =
    MutableStateFlow<Resource<BaseResponse<List<ContactUs>>>>(Resource.Default)
  val contactResponse = _contactResponse

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

  fun getContactLinks() {
    contactUseCase.invoke()
      .onEach { result ->
        contactResponse.value = result
      }
      .launchIn(viewModelScope)
  }

  fun mapToContactUiState(
    data: List<ContactUs>
  ): List<ContactUsUiState> {
    val contactData: MutableList<ContactUsUiState> = mutableListOf()
    data.forEach { item ->
      contactData.add(ContactUsUiState(item.link, item.image, item.id))
    }
    return contactData
  }
}