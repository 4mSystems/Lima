package app.te.lima_zola.domain.auth.use_case

import app.te.lima_zola.domain.auth.repository.AuthRepository
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PayWithDelegateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(
        subscribe_id: Int
    ): Flow<Resource<BaseResponse<*>>> =
        flow {
            emit(Resource.Loading)
            val result = authRepository.payWithDelegate(subscribe_id)
            emit(result)
        }.flowOn(Dispatchers.IO)
}