package app.te.lima_zola.presentation.videos.ui_state

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import app.te.lima_zola.R
import app.te.lima_zola.databinding.ItemEmptyContentBinding
import app.te.lima_zola.presentation.videos.eventListener.VideosEventListener

class ContentEmptyUiState : MainVideoUiState {
  override fun getLayoutRes(): Int = R.layout.item_empty_content

  override fun bind(
    item: View?,
    position: Int,
    context: Context,
    videosEventListener: VideosEventListener
  ) {
    item ?: return
    val binding = DataBindingUtil.bind<ItemEmptyContentBinding>(item)
  }

  override fun getId(): Int = 0

}
