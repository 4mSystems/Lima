package app.te.lima_zola.domain.general.use_case

import app.te.lima_zola.domain.account.use_case.CheckFirstTimeUseCase
import app.te.lima_zola.domain.account.use_case.CheckLoggedInUserUseCase
import app.te.lima_zola.domain.account.use_case.SetFirstTimeUseCase
import app.te.lima_zola.domain.account.use_case.ConfigUseCase

class GeneralUseCases(
  val checkFirstTimeUseCase: CheckFirstTimeUseCase,
  val checkLoggedInUserUseCase: CheckLoggedInUserUseCase,
  val setFirstTimeUseCase: SetFirstTimeUseCase,
  val configUseCase: ConfigUseCase,
  val languageUseCase: LanguageUseCase
)