package app.te.lima_zola.presentation.home.viewModels

import app.te.lima_zola.domain.account.use_case.UserLocalUseCase
import app.te.lima_zola.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeActivityViewModel @Inject constructor(
   val userLocalUseCase: UserLocalUseCase
) : BaseViewModel()