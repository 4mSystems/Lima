package app.te.lima_zola.data.auth.repository

import app.te.lima_zola.data.auth.data_source.remote.AuthRemoteDataSource
import app.te.lima_zola.domain.auth.entity.model.SubscriptionPaymentData
import app.te.lima_zola.domain.auth.entity.model.UserResponse
import app.te.lima_zola.domain.auth.entity.model.payments.PaymentMethods
import app.te.lima_zola.domain.auth.entity.model.payments.payment_result.PaymentResult
import app.te.lima_zola.domain.auth.entity.request.*
import app.te.lima_zola.domain.auth.repository.AuthRepository
import app.te.lima_zola.domain.profile.entity.UpdatePassword
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override
    suspend fun logIn(request: LogInRequest) = remoteDataSource.logIn(request)

    override suspend fun changePassword(request: UpdatePassword): Resource<BaseResponse<*>> =
        remoteDataSource.changePassword(request)

    override suspend fun authChangePassword(request: UpdatePassword): Resource<BaseResponse<*>> =
        remoteDataSource.authChangePassword(request)

    override suspend fun forgetPassword(request: ForgetPasswordRequest) =
        remoteDataSource.forgetPassword(request)

    override suspend fun register(request: RegisterRequest): Resource<BaseResponse<*>> =
        remoteDataSource.register(request)

    override suspend fun verifyAccount(request: RegisterRequest): Resource<BaseResponse<UserResponse>> =
        remoteDataSource.verifyAccount(request)
 override suspend fun verifyPasswordAccount(request: ForgetPasswordRequest): Resource<BaseResponse<*>> =
        remoteDataSource.verifyPasswordAccount(request)

    override suspend fun getSubscriptionsTypes(): Resource<BaseResponse<SubscriptionPaymentData>> =
        remoteDataSource.getSubscriptionsTypes()

    override suspend fun getPaymentMethods(): Resource<BaseResponse<PaymentMethods>> =
        remoteDataSource.getPaymentMethods()

    override suspend fun getPaymentResult(
        payment_id: Int,
        subscribe_id: Int
    ): Resource<BaseResponse<PaymentResult>> =
        remoteDataSource.getPaymentResult(payment_id, subscribe_id)

    override suspend fun payWithDelegate(
        subscribe_id: Int
    ): Resource<BaseResponse<*>> =
        remoteDataSource.payWithDelegate(subscribe_id)

}