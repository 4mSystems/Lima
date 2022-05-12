package app.te.protein_chef.domain.intro.repository

import app.te.protein_chef.domain.intro.entity.AppTutorialModel
import app.te.protein_chef.domain.utils.BaseResponse
import app.te.protein_chef.domain.utils.Resource

interface IntroRepository {
  suspend fun intro(): Resource<BaseResponse<List<AppTutorialModel>>>
}