package app.grand.tafwak.core.di.module

import app.grand.tafwak.domain.account.repository.AccountRepository
import app.grand.tafwak.domain.account.use_case.AccountUseCases
import app.grand.tafwak.domain.account.use_case.CheckFirstTimeUseCase
import app.grand.tafwak.domain.account.use_case.CheckLoggedInUserUseCase
import app.grand.tafwak.domain.account.use_case.LogOutUseCase
import app.grand.tafwak.domain.account.use_case.UserLocalUseCase
import app.grand.tafwak.domain.account.use_case.SendFirebaseTokenUseCase
import app.grand.tafwak.domain.account.use_case.SetFirstTimeUseCase
import app.grand.tafwak.domain.auth.repository.AuthRepository
import app.grand.tafwak.domain.auth.use_case.ChangePasswordUseCase
import app.grand.tafwak.domain.auth.use_case.LogInUseCase
import app.grand.tafwak.domain.auth.use_case.RegisterUseCase
import app.grand.tafwak.domain.auth.use_case.VerifyAccountUseCase
import app.grand.tafwak.domain.general.use_case.LanguageUseCase
import app.grand.tafwak.domain.general.use_case.GeneralUseCases
import app.grand.tafwak.domain.home.repository.HomeRepository
import app.grand.tafwak.domain.home.repository.local.HomeLocalRepository
import app.grand.tafwak.domain.home.use_case.HomeUseCase
import app.grand.tafwak.domain.intro.repository.IntroRepository
import app.grand.tafwak.domain.intro.use_case.IntroUseCase
import app.grand.tafwak.domain.meals.repository.MealsRepository
import app.grand.tafwak.domain.meals.use_case.MealsUseCase
import app.grand.tafwak.domain.my_coupons.repository.MyCouponsRepository
import app.grand.tafwak.domain.my_coupons.use_case.MyCouponsUseCase
import app.grand.tafwak.domain.my_locations.repository.MyLocationsRepository
import app.grand.tafwak.domain.my_locations.use_case.MyLocationsUseCase
import app.grand.tafwak.domain.packages_categories.repository.PackageCategoriesRepository
import app.grand.tafwak.domain.packages_categories.use_case.PackageCategoriesUseCase
import app.grand.tafwak.domain.profile.repository.ProfileRepository
import app.grand.tafwak.domain.profile.use_case.ProfileUseCase
import app.grand.tafwak.domain.settings.repository.SettingsRepository
import app.grand.tafwak.domain.settings.use_case.SettingsUseCase
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
  ): SettingsUseCase = SettingsUseCase(settingsRepository)


  @Provides
  @Singleton
  fun provideUpdateProfileUseCase(
    profileRepository: ProfileRepository,
    userLocalUseCase: UserLocalUseCase
  ): ProfileUseCase = ProfileUseCase(profileRepository, userLocalUseCase)

  @Provides
  @Singleton
  fun provideMyCouponsUseCase(
    myCouponsRepository: MyCouponsRepository
  ): MyCouponsUseCase = MyCouponsUseCase(myCouponsRepository)

  @Provides
  @Singleton
  fun provideMyLocationsUseCase(
    myLocationsRepository: MyLocationsRepository,
    userLocalUseCase: UserLocalUseCase
  ): MyLocationsUseCase = MyLocationsUseCase(myLocationsRepository, userLocalUseCase)

  @Provides
  @Singleton
  fun providePackageCategoriesUseCase(
    packageCategoriesRepository: PackageCategoriesRepository
  ): PackageCategoriesUseCase = PackageCategoriesUseCase(packageCategoriesRepository)

  @Provides
  @Singleton
  fun provideMealsUseCase(
    mealsRepository: MealsRepository
  ): MealsUseCase = MealsUseCase(mealsRepository)


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
  fun provideGeneralUseCases(
    checkFirstTimeUseCase: CheckFirstTimeUseCase,
    checkLoggedInUserUseCase: CheckLoggedInUserUseCase,
    setFirstTimeUseCase: SetFirstTimeUseCase,
    languageUseCase: LanguageUseCase
  ): GeneralUseCases =
    GeneralUseCases(
      checkFirstTimeUseCase,
      checkLoggedInUserUseCase,
      setFirstTimeUseCase,
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