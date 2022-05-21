package app.te.lima_zola.presentation.home.viewModels

import androidx.lifecycle.viewModelScope
import app.te.lima_zola.domain.home.models.HomeMainData
import app.te.lima_zola.domain.home.use_case.HomeUseCase
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val homeUseCase: HomeUseCase
) : BaseViewModel() {
  private val _homeResponse =
    MutableStateFlow<Resource<BaseResponse<HomeMainData>>>(Resource.Default)
  val homeResponse = _homeResponse

  init {
    getHomeData()
  }

  fun getHomeData() {
    homeUseCase.homeService()
      .onEach { result ->
        _homeResponse.value = result
      }
      .launchIn(viewModelScope)
  }

}