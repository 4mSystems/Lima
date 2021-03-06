package app.te.lima_zola.domain.account.use_case

import app.te.lima_zola.domain.account.repository.AccountRepository
import app.te.lima_zola.domain.utils.BaseResponse
import app.te.lima_zola.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val accountRepository: AccountRepository) {

  operator fun invoke(): Flow<Resource<BaseResponse<*>>> = flow {
    emit(Resource.Loading)

    val result = accountRepository.logOut()
    if (result is Resource.Success) {
      accountRepository.clearPreferences()
    }

    emit(result)
  }.flowOn(Dispatchers.IO)
}