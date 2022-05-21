package app.te.lima_zola.data.settings.data_source.remote

import app.te.lima_zola.data.remote.BaseRemoteDataSource
import app.te.lima_zola.domain.settings.models.suggestions.SuggestionsRequest
import javax.inject.Inject

class SettingsRemoteDataSource @Inject constructor(private val apiService: SettingsServices) :
  BaseRemoteDataSource() {

  suspend fun about(page:String) = safeApiCall {
    apiService.about(page)
  }
   suspend fun sendSuggestions(suggestionsRequest: SuggestionsRequest) = safeApiCall {
    apiService.suggestions(suggestionsRequest)
  }

}