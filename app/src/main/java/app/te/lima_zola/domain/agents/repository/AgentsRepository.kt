package app.te.lima_zola.domain.agents.repository

import app.te.lima_zola.domain.agents.entity.AgentModel
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource

interface AgentsRepository {
  suspend fun getAgents(country_id: Int): Resource<BaseResponse<AgentModel>>
}