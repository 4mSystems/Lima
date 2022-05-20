package app.te.lima_zola.data.agents.data_source.remote

import app.te.lima_zola.domain.agents.entity.AgentModel
import app.te.lima_zola.domain.utils.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AgentsServices {
  @GET("app/get-agents")
  suspend fun getAgents(@Query("country_id") country_id: Int): BaseResponse<AgentModel>

}