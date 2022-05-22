package app.te.lima_zola.domain.settings.repository

import app.te.lima_zola.domain.settings.models.AboutData
import app.te.lima_zola.domain.settings.models.Teams
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource

interface SettingsRepository {
  suspend fun about(page: String): Resource<BaseResponse<AboutData>>
  suspend fun getTeam(): Resource<BaseResponse<List<Teams>>>
}