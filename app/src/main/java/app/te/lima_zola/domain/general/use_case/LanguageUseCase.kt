package app.te.lima_zola.domain.general.use_case

import app.te.lima_zola.domain.account.repository.AccountRepository
import javax.inject.Inject

class LanguageUseCase @Inject constructor(private val accountRepository: AccountRepository) {
  suspend fun invoke() = accountRepository.getLang()
  suspend fun invoke(lang: String) = accountRepository.setLang(lang)
}