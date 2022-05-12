package app.te.protein_chef.domain.account.use_case

import app.te.protein_chef.domain.account.repository.AccountRepository
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