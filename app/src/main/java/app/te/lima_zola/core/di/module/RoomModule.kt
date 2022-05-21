package app.te.lima_zola.core.di.module

import android.content.Context
import androidx.room.Room
import app.te.lima_zola.core.AppDatabase
import app.te.lima_zola.data.home.data_source.local.HomeLocalRemoteDataSource
import app.te.lima_zola.BuildConfig
import app.te.lima_zola.data.general.data_source.local.CountriesLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
  @Provides
  @Singleton
  fun provideMyDB(@ApplicationContext context: Context): AppDatabase {
    return Room.databaseBuilder(
      context,
      AppDatabase::class.java,
      BuildConfig.ROOM_DB
    ).build()
  }

  @Provides
  @Singleton
  fun provideHomeLocalRepository(db: AppDatabase): HomeLocalRemoteDataSource {
    return HomeLocalRemoteDataSource(db.getHomeDao)
  }

 @Provides
  @Singleton
  fun provideCountriesLocalRepository(db: AppDatabase): CountriesLocalDataSource {
    return CountriesLocalDataSource(db.getCountriesDao)
  }

}



