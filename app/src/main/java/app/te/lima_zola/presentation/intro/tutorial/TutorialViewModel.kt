package app.te.lima_zola.presentation.intro.tutorial

import androidx.lifecycle.viewModelScope
import app.te.lima_zola.domain.general.use_case.GeneralUseCases
import app.te.lima_zola.domain.intro.entity.AppTutorialModel
import app.te.lima_zola.domain.intro.use_case.IntroUseCase
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
class TutorialViewModel @Inject constructor(
  private val generalUseCases: GeneralUseCases,
  introUseCase: IntroUseCase
) : BaseViewModel() {
  private val _appTutorialResponse =
    MutableStateFlow<Resource<BaseResponse<List<AppTutorialModel>>>>(Resource.Default)
  val appTutorialResponse = _appTutorialResponse

  init {
    introUseCase.invoke().onEach { result ->
      println(result.toString())
      _appTutorialResponse.value = result
    }
      .launchIn(viewModelScope)
  }

  fun setFirstTime(isFirstTime: Boolean) {
    viewModelScope.launch {
      generalUseCases.setFirstTimeUseCase(isFirstTime)
    }
  }

}