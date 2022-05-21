package app.te.lima_zola.domain.settings.models

import com.google.gson.annotations.SerializedName

data class News(
  @SerializedName("id")
  private var id: Int = 0,
  @SerializedName("body")
  private val body: String = ""
)
