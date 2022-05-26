package app.te.lima_zola.presentation.videos.ui_state

import android.content.Context
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.DataBindingUtil
import app.te.lima_zola.BR
import app.te.lima_zola.R
import app.te.lima_zola.databinding.ItemVideoBinding
import app.te.lima_zola.domain.videos_articles.entity.VideoData
import app.te.lima_zola.presentation.base.BaseUiState
import app.te.lima_zola.presentation.base.extensions.toStringMatch
import app.te.lima_zola.presentation.base.utils.Constants
import app.te.lima_zola.presentation.videos.eventListener.VideosEventListener

class VideoItemUiState(private val videoData: VideoData) : BaseUiState(), MainVideoUiState {
  lateinit var videosEventListener: VideosEventListener
  var itemPosition: Int = 0
  override fun getLayoutRes(): Int = R.layout.item_video

  override fun bind(
    item: View?,
    position: Int,
    context: Context,
    videosEventListener: VideosEventListener
  ) {
    item ?: return
    val binding = DataBindingUtil.bind<ItemVideoBinding>(item)
    binding?.uiState = this
    this.videosEventListener = videosEventListener
    itemPosition = position
    updateFavorite()
    updateLike()
  }

  override fun getId(): Int = videoData.id

  @Bindable
  var likeIcon: Int = R.drawable.ic_like

  @Bindable
  var favoriteIcon: Int = R.drawable.ic_icon_heart

  val videoImage: String = videoData.image
  val videoTitle: String = videoData.name
  var videoLikes: String = videoData.likes.toStringMatch()
  var videoViews: String = videoData.views.toStringMatch()
  var videoLocked: Int = if (videoData.free == Constants.FREE) View.VISIBLE else View.GONE
  var videoLikeVisibility: Int = if (videoData.free != Constants.FREE) View.VISIBLE else View.GONE

  private fun updateFavorite() {
    favoriteIcon = if (videoData.fav)
      R.drawable.ic_favorite
    else
      R.drawable.ic_icon_heart
    notifyPropertyChanged(BR.favoriteIcon)
  }

  private fun updateLike() {
    likeIcon = if (videoData.is_liked)
      R.drawable.ic_liked
    else
      R.drawable.ic_like
    notifyPropertyChanged(BR.likeIcon)
  }

  fun makeLike() {
    if (videoLocked == View.GONE) {
      videoData.is_liked = !videoData.is_liked
      updateLike()
      videosEventListener.makeLike(getId())
    }
  }

  fun makeWishList() {
    videoData.fav = !videoData.fav
    updateFavorite()
    videosEventListener.makeWishList(getId())
  }

}