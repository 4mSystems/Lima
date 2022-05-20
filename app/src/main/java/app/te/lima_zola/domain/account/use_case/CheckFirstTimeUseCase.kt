package app.te.lima_zola.domain.account.use_case

import app.te.lima_zola.domain.account.repository.AccountRepository
import javax.inject.Inject

class CheckFirstTimeUseCase @Inject constructor(private val accountRepository: AccountRepository) {

  suspend operator fun invoke() = accountRepository.isFirstTime()
}