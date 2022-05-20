package app.te.lima_zola.domain.account.use_case

import app.te.lima_zola.domain.account.repository.AccountRepository
import javax.inject.Inject

class ConfigUseCase @Inject constructor(private val accountRepository: AccountRepository) {

  suspend fun saveWhatsApp(value: String) =
    accountRepository.saveWhatsAppValue(value)

  suspend fun getWhatsApp() =
    accountRepository.getWhatsAppValue()
  suspend fun saveSplash(value: String) =
    accountRepository.saveSplash(value)

  suspend fun getSplash() =
    accountRepository.getSplash()

}