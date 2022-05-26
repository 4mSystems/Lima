package app.te.lima_zola.presentation.videos.ui_state

import androidx.annotation.ColorRes
import androidx.databinding.Bindable
import app.te.lima_zola.BR
import app.te.lima_zola.R
import app.te.lima_zola.domain.videos_articles.entity.SubCategory
import app.te.lima_zola.presentation.base.BaseUiState

class SubCategoryItemUiState(private val subCategory: SubCategory) : BaseUiState() {

  @ColorRes
  @Bindable
  var strokeColor: Int = if (subCategory.selected) R.color.colorPrimary else R.color.white
  var itemPosition = 0
  fun id(): Int = subCategory.id
  fun name(): String = subCategory.name
  fun image(): String = subCategory.image

  fun updateItemStrokeColor(color: Int) {
    strokeColor = color
    notifyPropertyChanged(BR.strokeColor)
  }
}