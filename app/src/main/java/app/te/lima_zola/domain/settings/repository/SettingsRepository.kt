package app.te.lima_zola.domain.settings.repository

import app.te.lima_zola.domain.settings.models.AboutMain
import app.te.lima_zola.domain.settings.models.suggestions.SuggestionsRequest
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource

interface SettingsRepository {
  suspend fun about(): Resource<BaseResponse<AboutMain>>
  suspend fun sendSuggestions(suggestionsRequest: SuggestionsRequest): Resource<BaseResponse<*>>
}