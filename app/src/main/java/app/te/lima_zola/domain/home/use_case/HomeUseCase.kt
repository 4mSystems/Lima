package app.te.lima_zola.domain.home.use_case

import app.te.lima_zola.domain.home.repository.HomeRepository
import app.te.lima_zola.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class HomeUseCase @Inject constructor(
  private val homeRepository: HomeRepository) {
  fun homeService() = flow {
    emit(Resource.Loading)
    val result = homeRepository.getHome()
    emit(result)
  }.flowOn(Dispatchers.IO)

}
