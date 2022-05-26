package app.te.lima_zola.presentation.videos.eventListener

import app.te.lima_zola.presentation.base.events.BaseEventListener

interface VideosEventListener : BaseEventListener {
  fun changeSubCategoryItem(itemId: Int, currentPosition: Int)
  fun makeLike(itemId: Int)
  fun makeWishList(itemId: Int)
}