package app.te.lima_zola.domain.auth.use_case

import app.te.lima_zola.domain.auth.entity.model.payments.payment_result.PaymentResult
import app.te.lima_zola.domain.auth.repository.AuthRepository
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPaymentResultUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(
        subscribe_id: Int,
        payment_id: Int
    ): Flow<Resource<BaseResponse<PaymentResult>>> =
        flow {
            emit(Resource.Loading)
            val result = authRepository.getPaymentResult(payment_id, subscribe_id)
            emit(result)
        }.flowOn(Dispatchers.IO)
}