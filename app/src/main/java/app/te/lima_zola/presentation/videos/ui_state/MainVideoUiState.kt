package app.te.lima_zola.presentation.videos.ui_state

import android.content.Context
import android.view.View
import app.te.lima_zola.presentation.videos.eventListener.VideosEventListener

interface MainVideoUiState {
  fun getLayoutRes(): Int
  fun bind(item: View?, position: Int, context: Context, videosEventListener: VideosEventListener)
  fun getId(): Int
}