package app.te.lima_zola.domain.home.models

import androidx.annotation.Keep

@Keep
data class Slider(
  val id: Int = 0,
  val image: String = "",
  val url: String = ""
)