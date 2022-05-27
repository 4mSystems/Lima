package app.te.lima_zola.presentation.base.events


interface BaseContentEventListener : BaseEventListener {
  fun changeSubCategoryItem(itemId: Int, currentPosition: Int)
  fun openContent(itemId: Int)
}