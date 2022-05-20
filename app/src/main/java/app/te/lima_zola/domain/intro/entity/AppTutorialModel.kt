package app.te.lima_zola.domain.intro.entity

import androidx.annotation.Keep

@Keep
data class AppTutorialModel(var title: String, var body: String, var image: String)