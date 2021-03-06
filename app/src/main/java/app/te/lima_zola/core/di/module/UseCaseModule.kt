package app.te.lima_zola.core.di.module

import app.te.lima_zola.domain.account.repository.AccountRepository
import app.te.lima_zola.domain.account.use_case.*
import app.te.lima_zola.domain.agents.repository.AgentsRepository
import app.te.lima_zola.domain.agents.use_case.AgentsUseCase
import app.te.lima_zola.domain.auth.repository.AuthRepository
import app.te.lima_zola.domain.auth.use_case.*
import app.te.lima_zola.domain.general.use_case.LanguageUseCase
import app.te.lima_zola.domain.general.use_case.GeneralUseCases
import app.te.lima_zola.domain.home.repository.HomeRepository
import app.te.lima_zola.domain.home.use_case.HomeUseCase
import app.te.lima_zola.domain.intro.repository.IntroRepository
import app.te.lima_zola.domain.intro.use_case.IntroUseCase
import app.te.lima_zola.domain.settings.repository.SettingsRepository
import app.te.lima_zola.domain.settings.use_case.AboutUseCase
import app.te.lima_zola.domain.settings.use_case.SuggestionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

  @Provides
  @Singleton
  fun provideLogInUseCase(
    authRepository: AuthRepository,
    userLocalUseCase: UserLocalUseCase
  ): LogInUseCase = LogInUseCase(authRepository, userLocalUseCase)

  @Provides
  @Singleton
  fun provideSocialLogInUseCase(
    authRepository: AuthRepository,
    userLocalUseCase: UserLocalUseCase
  ): SocialLogInUseCase = SocialLogInUseCase(authRepository, userLocalUseCase)

  @Provides
  @Singleton
  fun provideRegisterUseCase(
    authRepository: AuthRepository
  ): RegisterUseCase = RegisterUseCase(authRepository)

  @Provides
  @Singleton
  fun provideVerifyAccountUseCase(
    authRepository: AuthRepository,
    userLocalUseCase: UserLocalUseCase
  ): VerifyAccountUseCase = VerifyAccountUseCase(authRepository, userLocalUseCase)

  @Provides
  @Singleton
  fun provideChangePasswordUseCase(
    authRepository: AuthRepository,
  ): ChangePasswordUseCase = ChangePasswordUseCase(authRepository)


  @Provides
  @Singleton
  fun provideHomeUseCase(
    homeRepository: HomeRepository
  ): HomeUseCase = HomeUseCase(homeRepository)


  @Provides
  @Singleton
  fun provideIntroUseCase(
    introRepository: IntroRepository
  ): IntroUseCase = IntroUseCase(introRepository)

  @Provides
  @Singleton
  fun provideSettingsUseCase(
    settingsRepository: SettingsRepository
  ): AboutUseCase = AboutUseCase(settingsRepository)

  @Provides
  @Singleton
  fun provideSuggestionsUseCase(
    settingsRepository: SettingsRepository
  ): SuggestionsUseCase = SuggestionsUseCase(settingsRepository)

@Provides
  @Singleton
  fun provideAgentUseCase(
    agentsRepository: AgentsRepository
  ): AgentsUseCase = AgentsUseCase(agentsRepository)


  //public use cases
  @Provides
  @Singleton
  fun provideCheckFirstTimeUseCase(
    accountRepository: AccountRepository
  ): CheckFirstTimeUseCase = CheckFirstTimeUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideCheckLoggedInUserUseCase(
    accountRepository: AccountRepository
  ): CheckLoggedInUserUseCase = CheckLoggedInUserUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideSetFirstTimeUseCase(
    accountRepository: AccountRepository
  ): SetFirstTimeUseCase = SetFirstTimeUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideConfigUseCase(
    accountRepository: AccountRepository
  ): ConfigUseCase = ConfigUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideGeneralUseCases(
    checkFirstTimeUseCase: CheckFirstTimeUseCase,
    checkLoggedInUserUseCase: CheckLoggedInUserUseCase,
    setFirstTimeUseCase: SetFirstTimeUseCase,
    configUseCase: ConfigUseCase,
    languageUseCase: LanguageUseCase
  ): GeneralUseCases =
    GeneralUseCases(
      checkFirstTimeUseCase,
      checkLoggedInUserUseCase,
      setFirstTimeUseCase,
      configUseCase,
      languageUseCase
    )

  @Provides
  @Singleton
  fun provideLogOutUseCase(
    accountRepository: AccountRepository
  ): LogOutUseCase = LogOutUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideSendFirebaseTokenUseCase(
    accountRepository: AccountRepository
  ): SendFirebaseTokenUseCase = SendFirebaseTokenUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideSaveUserToLocalUseCase(
    accountRepository: AccountRepository
  ): UserLocalUseCase = UserLocalUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideClearPreferencesUseCase(
    accountRepository: AccountRepository
  ): LanguageUseCase = LanguageUseCase(accountRepository)

  @Provides
  @Singleton
  fun provideAccountUseCases(
    logOutUseCase: LogOutUseCase,
    sendFirebaseTokenUseCase: SendFirebaseTokenUseCase
  ): AccountUseCases = AccountUseCases(logOutUseCase, sendFirebaseTokenUseCase)

}