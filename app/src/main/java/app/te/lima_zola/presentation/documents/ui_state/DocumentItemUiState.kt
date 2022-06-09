package app.te.lima_zola.presentation.documents.ui_state

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import app.te.lima_zola.R
import app.te.lima_zola.databinding.ItemDocumentBinding
import app.te.lima_zola.domain.videos_articles.entity.VideoData
import app.te.lima_zola.presentation.base.BaseUiState
import app.te.lima_zola.presentation.base.events.BaseContentEventListener
import app.te.lima_zola.presentation.base.utils.Constants
import app.te.lima_zola.presentation.videos.ui_state.MainContentUiState

class DocumentItemUiState(private val videoData: VideoData) : BaseUiState(), MainContentUiState {
    lateinit var baseContentEventListener: BaseContentEventListener
    private var itemPosition: Int = 0
    override fun getLayoutRes(): Int = R.layout.item_document

    override fun bind(
        item: View?,
        position: Int,
        context: Context,
        baseContentEventListener: BaseContentEventListener
    ) {
        item ?: return
        val binding = DataBindingUtil.bind<ItemDocumentBinding>(item)
        binding?.uiState = this
        this.baseContentEventListener = baseContentEventListener
        itemPosition = position

    }

    override fun getId(): Int = videoData.id

    val documentImage: String = videoData.image
    val documentTitle: String = videoData.name
    var documentLocked: Int = if (videoData.free == Constants.FREE) View.VISIBLE else View.GONE
    fun openDocument() {
        if (documentLocked == View.GONE)
            baseContentEventListener.openContent(getId(), "")
        else
            baseContentEventListener.openContent(Constants.FREE, "")
    }
}