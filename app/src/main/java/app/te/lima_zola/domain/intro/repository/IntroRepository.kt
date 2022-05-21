package app.te.lima_zola.domain.intro.repository

import app.te.lima_zola.domain.intro.entity.AppTutorialModel
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource

interface IntroRepository {
  suspend fun intro(): Resource<BaseResponse<List<AppTutorialModel>>>
}