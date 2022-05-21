package app.te.lima_zola.domain.general.use_case

import app.te.lima_zola.domain.general.repository.GeneralRepository
import javax.inject.Inject


class CountriesUseCase @Inject constructor(
  private val generalRepository: GeneralRepository
) {

}
