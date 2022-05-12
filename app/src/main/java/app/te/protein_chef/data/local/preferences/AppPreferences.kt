package app.te.protein_chef.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import app.te.protein_chef.domain.auth.entity.model.UserResponse
import app.te.protein_chef.domain.auth.entity.model.UserSerializer
import com.structure.base_mvvm.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppPreferences @Inject constructor(private val context: Context) {

  private val STORE_NAME = "app_data_store"
  private val STORE_NAME_FIRST_TIME = "app_data_store_first_time"
  private val USER_DATA_STORE_FILE_NAME = "user_store.pb"

  private val FIREBASE_TOKEN = stringPreferencesKey("FIREBASE_TOKEN")
  private val USER_TOKEN = stringPreferencesKey("USER_TOKEN")
  private val COUNTRY_ID = stringPreferencesKey("COUNTRY_ID")
  private val FIRST_TIME = booleanPreferencesKey("FIRST_TIME")
  private val IS_LOGGED_IN = booleanPreferencesKey("isLoggedIn")
  private val LANG = stringPreferencesKey("LANG")
  private val WHATSAPP_VALUE = stringPreferencesKey("WHATSAPP_VALUE")
  private val SPLASH_SCREEN = stringPreferencesKey("SPLASH_SCREEN")


  private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = STORE_NAME)
  private val Context.dataStoreFirstTime: DataStore<Preferences> by preferencesDataStore(name = STORE_NAME_FIRST_TIME)
  private val Context.userDataStore: DataStore<User> by dataStore(
    fileName = USER_DATA_STORE_FILE_NAME,
    serializer = UserSerializer
  )

  suspend fun saveFireBaseToken(token: String) {
    context.dataStore.edit {
      it[FIREBASE_TOKEN] = token
    }
  }

  fun getFireBaseToken() = context.dataStore.data.map {
    it[FIREBASE_TOKEN] ?: ""
  }

  suspend fun isFirstTime(isFirstTime: Boolean) {
    context.dataStoreFirstTime.edit {
      it[FIRST_TIME] = isFirstTime
    }
  }

  fun getIsFirstTime() = context.dataStoreFirstTime.data.map {
    it[FIRST_TIME] ?: true
  }

  suspend fun isLoggedIn(isLoggedIn: Boolean) {
    context.dataStore.edit {
      it[IS_LOGGED_IN] = isLoggedIn
    }
  }

  fun getIsLoggedIn() = context.dataStore.data.map {
    it[IS_LOGGED_IN] ?: false
  }

  suspend fun userToken(userToken: String) {
    context.dataStore.edit {
      it[USER_TOKEN] = userToken
    }
  }

  fun getUserToken() = context.dataStore.data.map {
    it[USER_TOKEN] ?: ""
  }


  suspend fun setLang(lang: String) {
    context.dataStore.edit {
      it[LANG] = lang
    }
  }

  fun getLang() = context.dataStore.data.map {
    it[LANG] ?: "ar"
  }

  suspend fun saveUser(user: UserResponse) {
    context.userDataStore.updateData { store ->
      store.toBuilder()
        .setId(user.id)
        .setEmail(user.email ?: "")
        .setName(user.name ?: "")
        .setImage(user.image ?: "")
        .setIsCompleted(user.is_completed)
        .setPhone(user.phone)
        .setGender(user.gender ?: "")
        .setToken(user.token)
        .setAge(user.age)
        .setWeight(user.weight)
        .setHeight(user.height)
        .setSocialToken(user.social_id)
        .build()

    }
  }

  fun getUser(): Flow<User> = context.userDataStore.data
  suspend fun saveWhatsAppValue(whatsAppValue: String) {
    context.dataStore.edit {
      it[WHATSAPP_VALUE] = whatsAppValue
    }
  }

  fun getWhatsAppValue() = context.dataStore.data.map {
    it[WHATSAPP_VALUE] ?: ""
  }

  suspend fun saveSplash(splashScreen: String) {
    context.dataStore.edit {
      it[SPLASH_SCREEN] = splashScreen
    }
  }

  fun getSplash() = context.dataStore.data.map {
    it[SPLASH_SCREEN] ?: ""
  }


  suspend fun clearPreferences() {
    context.dataStore.edit {
      it.clear()
    }
    context.userDataStore.updateData {
      it.toBuilder().clear().build()
    }
  }
}