package app.te.lima_zola.presentation.videos.viewModels

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.domain.videos_articles.entity.SubCategory
import app.te.lima_zola.domain.videos_articles.entity.request.AddToWishListRequest
import app.te.lima_zola.domain.videos_articles.entity.request.LikeRequest
import app.te.lima_zola.domain.videos_articles.use_case.AddToWishListUseCase
import app.te.lima_zola.domain.videos_articles.use_case.LikeContentUseCase
import app.te.lima_zola.domain.videos_articles.use_case.SubCategoryUseCase
import app.te.lima_zola.domain.videos_articles.use_case.VideosUseCase
import app.te.lima_zola.presentation.base.BaseViewModel
import app.te.lima_zola.presentation.videos.ui_state.MainContentUiState
import app.te.lima_zola.presentation.videos.ui_state.SubCategoryItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    private val videosUseCase: VideosUseCase,
    private val subCategoryUseCase: SubCategoryUseCase,
    private val addToWishListUseCase: AddToWishListUseCase,
    private val likeContentUseCase: LikeContentUseCase
) : BaseViewModel() {

    private val _subCategoryResponse =
        MutableStateFlow<Resource<BaseResponse<List<SubCategory>>>>(Resource.Default)
    val subCategoryResponse = _subCategoryResponse

    private val _actionsResponse =
        MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
    val actionsResponse = _actionsResponse

    private val _videoArticlesResponse =
        MutableStateFlow<PagingData<MainContentUiState>>(PagingData.empty())
    val videoArticlesResponse = _videoArticlesResponse

     fun getData(cat_id: Int) {
        viewModelScope.launch {
            _subCategoryResponse.emit(Resource.Loading)
            val subCategories =
                withContext(Dispatchers.IO) { async { subCategoryUseCase.invoke(cat_id) } }

            val videosArticlesData =
                withContext(Dispatchers.IO) { async { videosUseCase.invoke(cat_id) } }

            videosArticlesData.await().collect { result ->
                _videoArticlesResponse.value = result
                _subCategoryResponse.value = subCategories.await()
            }
        }
    }

    fun getVideosArticles(cat_id: Int) {
        viewModelScope.launch {
            videosUseCase.invoke(cat_id).collect { result ->
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

    // mapping section
    fun setupSubCategory(
        data: List<SubCategory>,
        all: String
    ): MutableList<SubCategoryItemUiState> {
        val subCategory = SubCategory(id = 0, name = all, selected = true)
        val dataList = mutableListOf<SubCategoryItemUiState>()
        dataList.add(SubCategoryItemUiState(subCategory))
        dataList.addAll(data.map { catItem ->
            SubCategoryItemUiState(
                catItem
            )
        })
        return dataList
    }
}