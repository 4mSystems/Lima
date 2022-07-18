package app.te.lima_zola.presentation.documents.ui_state

import app.te.lima_zola.domain.videos_articles.entity.VideoData

class DocumentDetailsUiState(val videoData: VideoData) {
    val documentImage: String = videoData.image
    val documentTitle: String = videoData.name
    val documentBody: String = " <html> <head>" +
            "<meta name='viewport' content='user-scalable=no'/>" +
            "<style type='text/css'>" +
            "@font-face {" +
            "font-family: 'Cairo';" +
            "src:url('file:///android_asset/fonts/cairo_medium.ttf')" +
            "}" +
            "body {" +
            "font-family: 'Cairo';" +
            "}" +
            "</style> </head>" +
            "<body style='font-family: Cairo'>" + videoData.body +
            "</body>" +
            "</html>"
}