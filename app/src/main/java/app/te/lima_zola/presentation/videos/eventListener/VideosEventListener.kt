package app.te.lima_zola.presentation.videos.eventListener

import app.te.lima_zola.presentation.base.events.BaseContentEventListener

interface VideosEventListener : BaseContentEventListener {
  fun makeLike(itemId: Int)
  fun makeWishList(itemId: Int)
}