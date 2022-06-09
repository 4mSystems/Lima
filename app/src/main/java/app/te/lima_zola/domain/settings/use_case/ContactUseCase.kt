package app.te.lima_zola.domain.settings.use_case

import app.te.lima_zola.domain.settings.models.ContactUs
import app.te.lima_zola.domain.settings.repository.SettingsRepository
import app.te.lima_zola.domain.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ContactUseCase @Inject constructor(
  private val settingsRepository: SettingsRepository
) {
  operator fun invoke(): Flow<Resource<BaseResponse<List<ContactUs>>>> = flow {
    emit(Resource.Loading)
    val result = settingsRepository.getContact()
    emit(result)
  }.flowOn(Dispatchers.IO)

}