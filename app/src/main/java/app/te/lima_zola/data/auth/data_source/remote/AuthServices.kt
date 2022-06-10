package app.te.lima_zola.data.auth.data_source.remote

import app.te.lima_zola.domain.auth.entity.model.UserResponse
import app.te.lima_zola.domain.auth.entity.request.*
import app.te.lima_zola.domain.profile.entity.UpdatePassword
import app.te.lima_zola.domain.utils.BaseResponse
import retrofit2.http.*

interface AuthServices {

    @POST("v1/auth/login")
    suspend fun logIn(@Body request: LogInRequest): BaseResponse<UserResponse>

    @POST("V1/auth/social-login")
    suspend fun socialLogIn(@Body request: SocialLogInRequest): BaseResponse<UserResponse>

    @POST("V1/auth/forget-password")
    suspend fun forgetPassword(@Body request: ForgetPasswordRequest): BaseResponse<*>

    @POST("v1/auth/verify_phone")
    suspend fun verifyAccount(@Body request: RegisterRequest): BaseResponse<UserResponse>

    @POST("v1/user/profile/update_password")
    suspend fun changePassword(@Body request: UpdatePassword): BaseResponse<*>

    @POST("v1/auth/register")
    suspend fun register(@Body request: RegisterRequest): BaseResponse<*>

}