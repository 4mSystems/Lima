package app.te.lima_zola.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.te.lima_zola.data.home.data_source.local.HomeDao
import app.te.lima_zola.domain.home.models.HomeMainData
import app.te.lima_zola.domain.utils.Converters

@Database(
  entities = [HomeMainData::class],
  version = 2, exportSchema = false
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
  abstract val getHomeDao: HomeDao
}

