package app.te.lima_zola.data.general.repository

import app.te.lima_zola.data.general.data_source.local.CountriesLocalDataSource
import app.te.lima_zola.data.general.data_source.remote.GeneralRemoteDataSource
import app.te.lima_zola.data.local.preferences.AppPreferences
import app.te.lima_zola.domain.general.repository.GeneralRepository
import javax.inject.Inject

class GeneralRepositoryImpl @Inject constructor(
  private val remoteDataSource: GeneralRemoteDataSource,
  private val countriesLocalDataSource: CountriesLocalDataSource,
  private val appPreferences: AppPreferences
) : GeneralRepository