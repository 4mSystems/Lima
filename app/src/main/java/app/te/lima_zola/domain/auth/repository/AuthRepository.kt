package app.te.lima_zola.domain.auth.repository

import app.te.lima_zola.domain.auth.entity.model.SubscriptionPaymentData
import app.te.lima_zola.domain.auth.entity.model.UserResponse
import app.te.lima_zola.domain.auth.entity.model.disounts.PromoCodeData
import app.te.lima_zola.domain.auth.entity.model.payments.PaymentMethods
import app.te.lima_zola.domain.auth.entity.model.payments.payment_result.PaymentResult
import app.te.lima_zola.domain.auth.entity.request.*
import app.te.lima_zola.domain.profile.entity.UpdatePassword
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource

interface AuthRepository {

    suspend fun logIn(request: LogInRequest): Resource<BaseResponse<UserResponse>>
    suspend fun changePassword(request: UpdatePassword): Resource<BaseResponse<*>>
    suspend fun authChangePassword(request: UpdatePassword): Resource<BaseResponse<*>>
    suspend fun forgetPassword(request: ForgetPasswordRequest): Resource<BaseResponse<*>>
    suspend fun register(request: RegisterRequest): Resource<BaseResponse<*>>
    suspend fun verifyAccount(request: RegisterRequest): Resource<BaseResponse<UserResponse>>
    suspend fun verifyPasswordAccount(request: ForgetPasswordRequest): Resource<BaseResponse<*>>
    suspend fun getSubscriptionsTypes(): Resource<BaseResponse<SubscriptionPaymentData>>
    suspend fun getPaymentMethods(): Resource<BaseResponse<PaymentMethods>>
    suspend fun payWithDelegate(subscribe_id: Int): Resource<BaseResponse<*>>
    suspend fun getPaymentResult(
        payment_id: Int,
        subscribe_id: Int,
        code: String?
    ): Resource<BaseResponse<PaymentResult>>

    suspend fun activatePromoCode(activatePromoCodeRequest: ActivatePromoCodeRequest): Resource<BaseResponse<PromoCodeData>>
}