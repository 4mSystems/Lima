package app.te.lima_zola.domain.agents.use_case

import app.te.lima_zola.domain.agents.repository.AgentsRepository
import app.te.lima_zola.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class AgentsUseCase @Inject constructor(
  private val agentsRepository: AgentsRepository
) {
  fun getAgents(countryId: Int) = flow {
    emit(Resource.Loading)
    val result = agentsRepository.getAgents(countryId)
    emit(result)
  }.flowOn(Dispatchers.IO)

}
