package app.te.lima_zola.data.remote

import app.te.lima_zola.data.local.preferences.AppPreferences
import javax.inject.Inject

class BaseRemoteRepositoryImpl @Inject constructor(
  private val appPreferences: AppPreferences
) : BaseRemoteRepository {
  override
  suspend fun clearPreferences() = appPreferences.clearPreferences()
}