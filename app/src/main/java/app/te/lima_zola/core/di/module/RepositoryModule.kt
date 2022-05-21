package app.te.lima_zola.core.di.module

import app.te.lima_zola.data.account.data_source.remote.AccountRemoteDataSource
import app.te.lima_zola.data.account.repository.AccountRepositoryImpl
import app.te.lima_zola.data.agents.data_source.remote.AgentsRemoteDataSource
import app.te.lima_zola.data.agents.repository.AgentsRepositoryImpl
import app.te.lima_zola.data.auth.data_source.remote.AuthRemoteDataSource
import app.te.lima_zola.data.auth.repository.AuthRepositoryImpl
import app.te.lima_zola.data.general.data_source.remote.GeneralRemoteDataSource
import app.te.lima_zola.data.general.repository.GeneralRepositoryImpl
import app.te.lima_zola.data.home.data_source.local.HomeLocalRemoteDataSource
import app.te.lima_zola.data.home.repository.local.HomeLocalRepositoryImpl
import app.te.lima_zola.data.home.data_source.remote.HomeRemoteDataSource
import app.te.lima_zola.data.home.repository.HomeRepositoryImpl
import app.te.lima_zola.data.intro.data_source.IntroRemoteDataSource
import app.te.lima_zola.data.intro.repository.IntroRepositoryImpl
import app.te.lima_zola.data.local.preferences.AppPreferences
import app.te.lima_zola.data.settings.data_source.remote.SettingsRemoteDataSource
import app.te.lima_zola.data.settings.repository.SettingsRepositoryImpl
import app.te.lima_zola.domain.account.repository.AccountRepository
import app.te.lima_zola.domain.agents.repository.AgentsRepository
import app.te.lima_zola.domain.auth.repository.AuthRepository
import app.te.lima_zola.domain.general.repository.GeneralRepository
import app.te.lima_zola.domain.home.repository.HomeRepository
import app.te.lima_zola.domain.home.repository.local.HomeLocalRepository
import app.te.lima_zola.domain.intro.repository.IntroRepository
import app.te.lima_zola.domain.settings.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

  @Provides
  @Singleton
  fun provideGeneralRepository(
    remoteDataSource: GeneralRemoteDataSource
  ): GeneralRepository =
    GeneralRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideAuthRepository(
    remoteDataSource: AuthRemoteDataSource,
  ): AuthRepository = AuthRepositoryImpl(remoteDataSource)


  @Provides
  @Singleton
  fun provideAccountRepository(
    remoteDataSource: AccountRemoteDataSource,
    appPreferences: AppPreferences
  ): AccountRepository = AccountRepositoryImpl(remoteDataSource, appPreferences)


  @Provides
  @Singleton
  fun provideHomeRepository(
    remoteDataSource: HomeRemoteDataSource
  ): HomeRepository = HomeRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideHomeLocalRepository(
    homeLocalRemoteDataSource: HomeLocalRemoteDataSource
  ): HomeLocalRepository = HomeLocalRepositoryImpl(homeLocalRemoteDataSource)

  @Provides
  @Singleton
  fun provideIntroRepository(
    remoteDataSource: IntroRemoteDataSource
  ): IntroRepository = IntroRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideSettingsRepository(
    remoteDataSource: SettingsRemoteDataSource
  ): SettingsRepository = SettingsRepositoryImpl(remoteDataSource)

  @Provides
  @Singleton
  fun provideAgentsRepository(
    remoteDataSource: AgentsRemoteDataSource
  ): AgentsRepository = AgentsRepositoryImpl(remoteDataSource)


}