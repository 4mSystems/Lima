package app.te.lima_zola.data.auth.data_source.remote

import app.te.lima_zola.data.remote.BaseRemoteDataSource
import app.te.lima_zola.domain.auth.entity.request.*
import app.te.lima_zola.domain.profile.entity.UpdatePassword
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val apiService: AuthServices) :
    BaseRemoteDataSource() {

    suspend fun logIn(request: LogInRequest) = safeApiCall {
        apiService.logIn(request)
    }


    suspend fun forgetPassword(request: ForgetPasswordRequest) = safeApiCall {
        apiService.forgetPassword(request)
    }

    suspend fun verifyAccount(request: RegisterRequest) = safeApiCall {
        apiService.verifyAccount(request)
    }

    suspend fun changePassword(request: UpdatePassword) = safeApiCall {
        apiService.changePassword(request)
    }

    suspend fun register(request: RegisterRequest) = safeApiCall {
        apiService.register(request)
    }

    suspend fun getSubscriptionsTypes() = safeApiCall {
        apiService.getSubscriptionsTypes()
    }

    suspend fun getPaymentMethods() = safeApiCall {
        apiService.getPaymentMethods()
    }

    suspend fun getPaymentResult(subscribe_id: Int, payment_id: Int) = safeApiCall {
        apiService.getPaymentMethodsResult(subscribe_id, payment_id)
    }


}