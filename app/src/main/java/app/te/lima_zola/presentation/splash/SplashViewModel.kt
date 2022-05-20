package app.te.lima_zola.presentation.splash

import android.view.View
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import app.te.lima_zola.BR
import app.te.lima_zola.domain.general.use_case.GeneralUseCases
import app.te.lima_zola.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val generalUseCases: GeneralUseCases) :
  BaseViewModel() {
  lateinit var eventListener: SplashEventListener

  @Bindable
  var adImage: String = ""

  @Bindable
  var adImageVisibility: Int = View.GONE

  @Bindable
  var logoVisibility: Int = View.GONE

  init {
    viewModelScope.launch {
      generalUseCases.configUseCase.getSplash().collectLatest { splash ->
        updateSplashScreen(splash)
        if (splash.isNotEmpty()) {
          delay(6000)
        } else
          delay(3000)
        generalUseCases.checkFirstTimeUseCase().collect { isFirst ->
          if (isFirst) {
            eventListener.openOnBoarding()
          } else {
            eventListener.openHome()
          }
        }
      }

    }
  }

  private fun updateSplashScreen(adImage: String) {
    this.adImage = adImage
    if (adImage.isNotEmpty())
      adImageVisibility = View.VISIBLE
    else
      logoVisibility = View.VISIBLE
    notifyPropertyChanged(BR.adImageVisibility)
    notifyPropertyChanged(BR.logoVisibility)
    notifyPropertyChanged(BR.adImage)
  }
}