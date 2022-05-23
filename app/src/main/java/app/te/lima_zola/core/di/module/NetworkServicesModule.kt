package app.te.lima_zola.core.di.module

import app.te.lima_zola.data.account.data_source.remote.AccountServices
import app.te.lima_zola.data.agents.data_source.remote.AgentsServices
import app.te.lima_zola.data.auth.data_source.remote.AuthServices
import app.te.lima_zola.data.general.data_source.remote.GeneralServices
import app.te.lima_zola.data.home.data_source.remote.HomeServices
import app.te.lima_zola.data.intro.data_source.IntroServices
import app.te.lima_zola.data.profile.data_source.ProfileServices
import app.te.lima_zola.data.settings.data_source.remote.SettingsServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServicesModule {

  @Provides
  @Singleton
  fun provideAuthServices(retrofit: Retrofit): AuthServices =
    retrofit.create(AuthServices::class.java)

  @Provides
  @Singleton
  fun provideAccountServices(retrofit: Retrofit): AccountServices =
    retrofit.create(AccountServices::class.java)

  @Provides
  @Singleton
  fun provideGeneralServices(retrofit: Retrofit): GeneralServices =
    retrofit.create(GeneralServices::class.java)


  @Provides
  @Singleton
  fun provideHomeServices(retrofit: Retrofit): HomeServices =
    retrofit.create(HomeServices::class.java)

  @Provides
  @Singleton
  fun provideIntroServices(retrofit: Retrofit): IntroServices =
    retrofit.create(IntroServices::class.java)

  @Provides
  @Singleton
  fun provideSettingsServices(retrofit: Retrofit): SettingsServices =
    retrofit.create(SettingsServices::class.java)

  @Provides
  @Singleton
  fun provideAgentsServices(retrofit: Retrofit): AgentsServices =
    retrofit.create(AgentsServices::class.java)

  @Provides
  @Singleton
  fun provideUpdateProfileServices(retrofit: Retrofit): ProfileServices =
    retrofit.create(ProfileServices::class.java)


}