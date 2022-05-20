package app.te.lima_zola.domain.settings.models

import com.google.gson.annotations.SerializedName

data class AboutMain(
  @SerializedName("about")
   var aboutData: AboutData = AboutData(),
  @SerializedName("news")
   val news: News? = null
)