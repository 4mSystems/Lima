package app.te.lima_zola.data.settings.data_source.remote

import app.te.lima_zola.domain.settings.models.AboutMain
import app.te.lima_zola.domain.settings.models.suggestions.SuggestionsRequest
import app.te.lima_zola.domain.utils.BaseResponse
import retrofit2.http.*

interface SettingsServices {
  @GET("app/about-us")
  suspend fun about(): BaseResponse<AboutMain>

  @POST("app/complains_suggestions")
  suspend fun suggestions(@Body suggestionsRequest: SuggestionsRequest): BaseResponse<*>


}