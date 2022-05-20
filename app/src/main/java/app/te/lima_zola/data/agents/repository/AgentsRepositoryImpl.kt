package app.te.lima_zola.data.agents.repository

import app.te.lima_zola.data.agents.data_source.remote.AgentsRemoteDataSource
import app.te.lima_zola.domain.agents.entity.AgentModel
import app.te.lima_zola.domain.agents.repository.AgentsRepository
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import javax.inject.Inject

class AgentsRepositoryImpl @Inject constructor(private val remoteDataSource: AgentsRemoteDataSource) :
  AgentsRepository {
  override suspend fun getAgents(country_id: Int): Resource<BaseResponse<AgentModel>> =
    remoteDataSource.getAgents(country_id)
}