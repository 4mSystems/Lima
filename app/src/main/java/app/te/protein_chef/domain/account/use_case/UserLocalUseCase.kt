package app.te.protein_chef.domain.account.use_case

import app.te.protein_chef.domain.account.repository.AccountRepository
import app.te.protein_chef.domain.auth.entity.model.UserResponse
import com.structure.base_mvvm.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalUseCase @Inject constructor(private val accountRepository: AccountRepository) {

  suspend operator fun invoke(user: UserResponse) = accountRepository.saveUserToLocal(user)

  suspend operator fun invoke(): Flow<User> = accountRepository.getUserToLocal()

  suspend fun saveUserToken(value: String) =
    accountRepository.saveUserToken(value)

  suspend fun logOut() = accountRepository.clearPreferences()

}