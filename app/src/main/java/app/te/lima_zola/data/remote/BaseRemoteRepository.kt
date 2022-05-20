package app.te.lima_zola.data.remote

interface BaseRemoteRepository {
  suspend fun clearPreferences()
}