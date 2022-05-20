package app.te.lima_zola.domain.account.use_case

import app.te.lima_zola.domain.account.repository.AccountRepository
import javax.inject.Inject

class CheckLoggedInUserUseCase @Inject constructor(private val accountRepository: AccountRepository) {

  suspend operator fun invoke() = accountRepository.getUserToLocal()
}