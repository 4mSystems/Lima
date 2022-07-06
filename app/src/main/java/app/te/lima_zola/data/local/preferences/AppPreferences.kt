package app.te.lima_zola.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import app.te.lima_zola.data.remote.Keys
import app.te.lima_zola.domain.auth.entity.model.UserResponse
import app.te.lima_zola.domain.auth.entity.model.UserSerializer
import com.structure.base_mvvm.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppPreferences @Inject constructor(private val context: Context) {

    private val STORE_NAME = Keys.appDataStore()
    private val STORE_NAME_FIRST_TIME = Keys.appDataStoreFirstTime()
    private val USER_DATA_STORE_FILE_NAME = Keys.userDataStoreFileName()

    private val FIREBASE_TOKEN = stringPreferencesKey(Keys.firebaseToken())
    private val USER_TOKEN = stringPreferencesKey(Keys.userToken())
    private val FIRST_TIME = booleanPreferencesKey(Keys.firstTime())
    private val IS_LOGGED_IN = booleanPreferencesKey(Keys.isLoggedIn())
    private val LANG = stringPreferencesKey(Keys.lang())
    private val SPLASH_SCREEN = stringPreferencesKey(Keys.splash())


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
        it[FIREBASE_TOKEN] ?: "201000831080"
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
                .setName(user.name)
                .setImage(user.image ?: "")
                .setPhone(user.phone)
                .setJwt(user.jwt)
                .setSubscriber(user.subscriber)
                .setCityId(user.city_id)
                .setCityName(user.city_name)
                .build()
        }
    }

    fun getUser(): Flow<User> = context.userDataStore.data

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