package app.te.lima_zola.data.auth.data_source.remote

import app.te.lima_zola.domain.auth.entity.model.SubscriptionPaymentData
import app.te.lima_zola.domain.auth.entity.model.UserResponse
import app.te.lima_zola.domain.auth.entity.model.disounts.PromoCodeData
import app.te.lima_zola.domain.auth.entity.model.payments.PaymentMethods
import app.te.lima_zola.domain.auth.entity.model.payments.payment_result.PaymentResult
import app.te.lima_zola.domain.auth.entity.request.*
import app.te.lima_zola.domain.profile.entity.UpdatePassword
import app.te.lima_zola.domain.utils.BaseResponse
import retrofit2.http.*

interface AuthServices {

    @POST("v1/auth/login")
    suspend fun logIn(@Body request: LogInRequest): BaseResponse<UserResponse>

    @POST("v1/auth/forget-password")
    suspend fun forgetPassword(@Body request: ForgetPasswordRequest): BaseResponse<*>

    @POST("v1/auth/verify_phone")
    suspend fun verifyAccount(@Body request: RegisterRequest): BaseResponse<UserResponse>

    @POST("v1/auth/verify")
    suspend fun verifyPasswordAccount(@Body request: ForgetPasswordRequest): BaseResponse<*>

    @POST("v1/user/profile/update_password")
    suspend fun changePassword(@Body request: UpdatePassword): BaseResponse<*>

    @POST("v1/auth/change-password")
    suspend fun authChangePassword(@Body request: UpdatePassword): BaseResponse<*>

    @POST("v1/auth/register")
    suspend fun register(@Body request: RegisterRequest): BaseResponse<*>

    @GET("v1/user/subscription/payment_step_one")
    suspend fun getSubscriptionsTypes(): BaseResponse<SubscriptionPaymentData>

    @GET("v1/user/subscription/payment_step_one")
    suspend fun getPaymentMethods(): BaseResponse<PaymentMethods>

    @GET("v1/user/subscription/payment_step_two")
    suspend fun getPaymentMethodsResult(
        @Query("payment_method_id") payment_id: Int,
        @Query("subscribe_type_id") subscribe_id: Int,
        @Query("code") code: String?
    ): BaseResponse<PaymentResult>

    @POST("v1/user/subscription/store")
    suspend fun payWithDelegate(
        @Query("subscribe_type_id") subscribe_id: Int,
        @Query("type") type: String = "cash"
    ): BaseResponse<*>

    @POST("v1/user/coupon/apply")
    suspend fun activatePromoCode(
        @Body activatePromoCodeRequest: ActivatePromoCodeRequest
    ): BaseResponse<PromoCodeData>


}