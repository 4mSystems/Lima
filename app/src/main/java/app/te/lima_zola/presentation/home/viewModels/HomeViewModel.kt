package app.te.lima_zola.presentation.home.viewModels

import androidx.lifecycle.viewModelScope
import app.te.lima_zola.domain.account.use_case.UserLocalUseCase
import app.te.lima_zola.domain.auth.entity.model.UserResponse
import app.te.lima_zola.domain.home.models.HomeData
import app.te.lima_zola.domain.home.use_case.HomeUseCase
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseViewModel
import app.te.lima_zola.presentation.base.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    val userLocalUseCase: UserLocalUseCase
) : BaseViewModel() {
    private val _homeResponse =
        MutableStateFlow<Resource<BaseResponse<HomeData>>>(Resource.Default)
    val homeResponse = _homeResponse

    fun getHomeData(cat_id: Int) {
        homeUseCase.homeService(cat_id)
            .onEach { result ->
                _homeResponse.value = result
            }
            .launchIn(viewModelScope)
    }

    fun updateUser(subscriber: Int) {
        viewModelScope.launch {
            userLocalUseCase.invoke().collect { user ->
                if (user.name.isNotEmpty() && user.subscriber == Constants.FREE) {
                    val userResponse =
                        UserResponse(
                            id = user.id,
                            email = user.email,
                            name = user.name,
                            image = user.image,
                            phone = user.phone,
                            jwt = user.jwt,
                            city_id = user.cityId,
                            city_name = user.cityName,
                            subscriber = subscriber
                        )
                    userLocalUseCase.invoke(userResponse)
                }
            }
        }
    }
}