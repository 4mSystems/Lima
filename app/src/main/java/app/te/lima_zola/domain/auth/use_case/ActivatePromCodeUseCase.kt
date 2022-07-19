package app.te.lima_zola.domain.auth.use_case

import app.te.lima_zola.domain.auth.entity.model.disounts.PromoCodeData
import app.te.lima_zola.domain.auth.entity.request.ActivatePromoCodeRequest
import app.te.lima_zola.domain.auth.repository.AuthRepository
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ActivatePromCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(
        activatePromoCodeRequest: ActivatePromoCodeRequest
    ): Flow<Resource<BaseResponse<PromoCodeData>>> =
        flow {
            if (!activatePromoCodeRequest.code.isNullOrEmpty()) {
                emit(Resource.Loading)
                val result = authRepository.activatePromoCode(activatePromoCodeRequest)
                emit(result)
            }
        }.flowOn(Dispatchers.IO)
}