package app.te.lima_zola.data.general.data_source.local

import app.te.lima_zola.domain.general.entity.countries.CountryModel
import javax.inject.Inject

class CountriesLocalDataSource @Inject constructor(private val countriesDao: CountriesDao) {

  fun countriesLocal() = countriesDao.getCountries()


  suspend fun addCountries(countryModel: List<CountryModel>) =
    countriesDao.addCountries(countryModel)

  fun deleteCountry() = countriesDao.deleteCountries()

}