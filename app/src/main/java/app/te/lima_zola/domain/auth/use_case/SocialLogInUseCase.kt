package app.te.lima_zola.domain.auth.use_case

import app.te.lima_zola.domain.account.use_case.UserLocalUseCase
import app.te.lima_zola.domain.auth.entity.model.UserResponse
import app.te.lima_zola.domain.auth.entity.request.SocialLogInRequest
import app.te.lima_zola.domain.auth.repository.AuthRepository
import app.te.lima_zola.domain.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class SocialLogInUseCase @Inject constructor(
  private val authRepository: AuthRepository,
  private val userLocalUseCase: UserLocalUseCase
) {
//  operator fun invoke(
//    request: SocialLogInRequest
//  ): Flow<Resource<BaseResponse<UserResponse>>> = flow {
//    emit(Resource.Loading)
//    val result = authRepository.getSubscriptionsTypes(request)
//    if (result is Resource.Success) {
//      userLocalUseCase.saveUserToken(result.value.data.jwt)
//      userLocalUseCase.invoke(result.value.data)
//    }
//    emit(result)
//  }.flowOn(Dispatchers.IO)

}