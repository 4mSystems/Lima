package app.te.lima_zola.presentation.documents.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.domain.videos_articles.entity.VideoData
import app.te.lima_zola.domain.videos_articles.use_case.*
import app.te.lima_zola.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DocumentDetailsViewModel @Inject constructor(
    private val articleDetailsUseCase: ArticleDetailsUseCase,
    val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _articleDetailsResponse =
        MutableStateFlow<Resource<BaseResponse<VideoData>>>(Resource.Default)
    val articleDetailsResponse = _articleDetailsResponse

    init {
        savedStateHandle.get<Int>("doc_id")?.let { catId ->
            getArticleDetails(catId)
        }
    }

    private fun getArticleDetails(cat_id: Int) {
        viewModelScope.launch {
            articleDetailsUseCase.invoke(cat_id).collect { result ->
                _articleDetailsResponse.value = result
            }
        }
    }

}