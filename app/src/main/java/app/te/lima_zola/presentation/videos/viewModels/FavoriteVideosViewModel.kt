package app.te.lima_zola.presentation.videos.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.domain.videos_articles.entity.request.AddToWishListRequest
import app.te.lima_zola.domain.videos_articles.entity.request.LikeRequest
import app.te.lima_zola.domain.videos_articles.use_case.AddToWishListUseCase
import app.te.lima_zola.domain.videos_articles.use_case.FavoriteVideosUseCase
import app.te.lima_zola.domain.videos_articles.use_case.LikeContentUseCase
import app.te.lima_zola.presentation.base.BaseViewModel
import app.te.lima_zola.presentation.videos.ui_state.MainVideoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FavoriteVideosViewModel @Inject constructor(
  private val favoriteVideosUseCase: FavoriteVideosUseCase,
  private val addToWishListUseCase: AddToWishListUseCase,
  private val likeContentUseCase: LikeContentUseCase
) : BaseViewModel() {


  private val _actionsResponse =
    MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val actionsResponse = _actionsResponse

  private val _videoArticlesResponse =
    MutableStateFlow<PagingData<MainVideoUiState>>(PagingData.empty())
  val videoArticlesResponse = _videoArticlesResponse

   fun getFavoriteVideos() {
    viewModelScope.launch {
      favoriteVideosUseCase.invoke().collect { result ->
        _videoArticlesResponse.value = result
      }
    }
  }

  fun addToWishList(addToWishListRequest: AddToWishListRequest) {
    viewModelScope.launch {
      addToWishListUseCase(addToWishListRequest).collect { result ->
        _actionsResponse.value = result
      }
    }
  }

  fun likeContent(likeRequest: LikeRequest) {
    viewModelScope.launch {
      likeContentUseCase(likeRequest).collect { result ->
        _actionsResponse.value = result
      }
    }
  }

}