package app.te.lima_zola.data.settings.data_source.remote

import app.te.lima_zola.domain.settings.models.AboutData
import app.te.lima_zola.domain.settings.models.Teams
import app.te.lima_zola.domain.utils.BaseResponse
import retrofit2.http.*

interface SettingsServices {
  @GET("v1/app/pages/{page}")
  suspend fun about(@Path("page") page: String): BaseResponse<AboutData>

  @GET("v1/app/teams")
  suspend fun teams(): BaseResponse<List<Teams>>


}