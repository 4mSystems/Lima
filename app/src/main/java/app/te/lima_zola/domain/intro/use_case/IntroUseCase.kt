package app.te.lima_zola.domain.intro.use_case

import app.te.lima_zola.domain.intro.entity.AppTutorialModel
import app.te.lima_zola.domain.intro.repository.IntroRepository
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class IntroUseCase @Inject constructor(
  private val introRepository: IntroRepository
) {

  operator fun invoke(): Flow<Resource<BaseResponse<List<AppTutorialModel>>>> = flow {
    emit(Resource.Loading)
    val result = introRepository.intro()
    emit(result)
  }.flowOn(Dispatchers.IO)
}