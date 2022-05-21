package app.te.lima_zola.presentation.agents.viewModel

import app.te.lima_zola.domain.general.entity.countries.CountryModel
import app.te.lima_zola.domain.general.use_case.CountriesUseCase
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AgentsViewModel @Inject constructor(
  private val countriesUseCase: CountriesUseCase
) : BaseViewModel() {
  private val _countriesResponse =
    MutableStateFlow<Resource<List<CountryModel>>>(Resource.Default)
  val countryResponse = _countriesResponse


}