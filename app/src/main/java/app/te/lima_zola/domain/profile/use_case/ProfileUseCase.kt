package app.te.lima_zola.domain.profile.use_case

import app.te.lima_zola.domain.auth.entity.model.UserResponse
import app.te.lima_zola.domain.profile.repository.ProfileRepository
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {

    operator fun invoke(): Flow<Resource<BaseResponse<UserResponse>>> = flow {
        emit(Resource.Loading)
        val result = profileRepository.getProfile()
        emit(result)
    }.flowOn(Dispatchers.IO)

}