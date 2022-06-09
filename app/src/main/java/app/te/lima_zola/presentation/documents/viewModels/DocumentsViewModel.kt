package app.te.lima_zola.presentation.documents.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import app.te.lima_zola.domain.account.use_case.UserLocalUseCase
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.domain.videos_articles.entity.SubCategory
import app.te.lima_zola.domain.videos_articles.use_case.*
import app.te.lima_zola.presentation.base.BaseViewModel
import app.te.lima_zola.presentation.videos.ui_state.MainContentUiState
import app.te.lima_zola.presentation.videos.ui_state.SubCategoryItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DocumentsViewModel @Inject constructor(
    private val articleUseCase: ArticleUseCase,
    private val subCategoryUseCase: SubCategoryUseCase,
    val userLocalUseCase: UserLocalUseCase,
    val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _subCategoryResponse =
        MutableStateFlow<Resource<BaseResponse<List<SubCategory>>>>(Resource.Default)
    val subCategoryResponse = _subCategoryResponse

    private val _articlesResponse =
        MutableStateFlow<PagingData<MainContentUiState>>(PagingData.empty())
    val articlesResponse = _articlesResponse

    init {
        savedStateHandle.get<Int>("cat_id")?.let { catId ->
            getData(catId)
        }
    }

    private fun getData(cat_id: Int) {
        viewModelScope.launch {
            _subCategoryResponse.emit(Resource.Loading)
            val subCategories =
                withContext(Dispatchers.IO) { async { subCategoryUseCase.invoke(cat_id) } }

            val videosArticlesData =
                withContext(Dispatchers.IO) { async { articleUseCase.invoke(cat_id) } }

            videosArticlesData.await().collect { result ->
                _articlesResponse.value = result
                _subCategoryResponse.value = subCategories.await()
            }
        }
    }

    fun getArticles(cat_id: Int) {
        viewModelScope.launch {
            articleUseCase.invoke(cat_id).collect { result ->
                _articlesResponse.value = result
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