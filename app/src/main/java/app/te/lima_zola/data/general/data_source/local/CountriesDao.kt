package app.te.lima_zola.data.general.data_source.local

import androidx.room.*
import app.te.lima_zola.domain.general.entity.countries.CountryModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CountriesDao {
  @Query("Select * from CountryModel")
  fun getCountries(): Flow<List<CountryModel>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addCountries(countryModel: List<CountryModel>)

  @Query("DELETE from CountryModel")
  fun deleteCountries()

}