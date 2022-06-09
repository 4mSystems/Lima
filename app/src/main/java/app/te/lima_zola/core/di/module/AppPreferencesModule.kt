package app.te.lima_zola.core.di.module

import android.content.Context
import app.te.lima_zola.data.local.preferences.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppPreferencesModule {

  @Provides
  @Singleton
  fun providePreferences(@ApplicationContext context: Context) = AppPreferences(context)

}