package app.te.protein_chef.data.intro.data_source

import app.te.protein_chef.domain.intro.entity.AppTutorialModel
import app.te.protein_chef.domain.utils.BaseResponse
import retrofit2.http.GET

interface IntroServices {

  @GET("app/app-explanation")
  suspend fun intro(): BaseResponse<List<AppTutorialModel>>

}