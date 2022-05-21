package app.te.lima_zola.data.settings.repository

import app.te.lima_zola.data.settings.data_source.remote.SettingsRemoteDataSource
import app.te.lima_zola.domain.settings.models.AboutData
import app.te.lima_zola.domain.settings.models.AboutMain
import app.te.lima_zola.domain.settings.models.suggestions.SuggestionsRequest
import app.te.lima_zola.domain.settings.repository.SettingsRepository
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val remoteDataSource: SettingsRemoteDataSource) :
  SettingsRepository {
  override suspend fun about(page:String): Resource<BaseResponse<AboutData>> = remoteDataSource.about(page)
  override suspend fun sendSuggestions(suggestionsRequest: SuggestionsRequest): Resource<BaseResponse<*>> =remoteDataSource.sendSuggestions(suggestionsRequest)


}