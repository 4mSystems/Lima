package app.te.lima_zola.data.agents.data_source.remote

import app.te.lima_zola.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class AgentsRemoteDataSource @Inject constructor(private val apiService: AgentsServices) :
  BaseRemoteDataSource() {

  suspend fun getAgents(country_id: Int) = safeApiCall {
    apiService.getAgents(country_id)
  }

}