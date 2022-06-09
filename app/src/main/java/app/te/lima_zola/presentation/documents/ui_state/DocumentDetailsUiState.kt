package app.te.lima_zola.presentation.documents.ui_state

import app.te.lima_zola.domain.videos_articles.entity.VideoData

class DocumentDetailsUiState(val videoData: VideoData) {
    val documentImage: String = videoData.image
    val documentTitle: String = videoData.name

}