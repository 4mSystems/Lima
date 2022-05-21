package app.te.lima_zola.data.intro.data_source

import app.te.lima_zola.domain.intro.entity.AppTutorialModel
import app.te.lima_zola.domain.utils.BaseResponse
import retrofit2.http.GET

interface IntroServices {

  @GET("app/app-explanation")
  suspend fun intro(): BaseResponse<List<AppTutorialModel>>

}