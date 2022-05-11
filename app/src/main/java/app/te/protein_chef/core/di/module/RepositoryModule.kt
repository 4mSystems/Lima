package app.te.protein_chef.core.di.module

import app.te.protein_chef.data.account.data_source.remote.AccountRemoteDataSource
import app.te.protein_chef.data.account.repository.AccountRepositoryImpl
import app.te.protein_chef.data.auth.data_source.remote.AuthRemoteDataSource
import app.te.protein_chef.data.auth.repository.AuthRepositoryImpl
import app.te.protein_chef.data.general.data_source.remote.GeneralRemoteDataSource
import app.te.protein_chef.data.general.repository.GeneralRepositoryImpl
import app.te.protein_chef.data.home.data_source.local.HomeLocalRemoteDataSource
import app.te.protein_chef.data.home.repository.local.HomeLocalRepositoryImpl
import app.te.protein_chef.data.home.data_source.remote.HomeRemoteDataSource
import app.te.protein_chef.data.home.repository.HomeRepositoryImpl
import app.te.protein_chef.data.intro.data_source.IntroRemoteDataSource
import app.te.protein_chef.data.intro.repository.IntroRepositoryImpl
import app.te.protein_chef.data.local.preferences.AppPreferences
import app.te.protein_chef.domain.account.repository.AccountRepository
import app.te.protein_chef.domain.auth.repository.AuthRepository
import app.te.protein_chef.domain.general.repository.GeneralRepository
import app.te.protein_chef.domain.home.repository.HomeRepository
import app.te.protein_chef.domain.home.repository.local.HomeLocalRepository
import app.te.protein_chef.domain.intro.repository.IntroRepository
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
    remoteDataSource: GeneralRemoteDataSource,
    appPreferences: AppPreferences
  ): GeneralRepository = GeneralRepositoryImpl(remoteDataSource, appPreferences)

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




}