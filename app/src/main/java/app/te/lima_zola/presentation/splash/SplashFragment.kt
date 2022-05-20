package app.te.lima_zola.presentation.splash

import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import app.te.lima_zola.R
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.navigateSafe
import app.te.lima_zola.presentation.base.extensions.openActivityAndClearStack
import app.te.lima_zola.presentation.home.HomeActivity
import app.te.lima_zola.databinding.FragmentSplashBinding
import app.te.lima_zola.presentation.base.extensions.getMyColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(), SplashEventListener {

  private val viewModel: SplashViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_splash

  override
  fun setUpViews() {
    viewModel.eventListener = this
    binding.viewModel = viewModel
    viewModel.eventListener = this
  }


  override fun openHome() {
    openActivityAndClearStack(HomeActivity::class.java)
  }

  override fun openOnBoarding() {
    setLanguage("ar")
    navigateSafe(SplashFragmentDirections.actionSplashFragmentToLogInFragment())
//    navigateSafe(SplashFragmentDirections.actionSplashFragmentToTutorialFragment())
  }

  override fun setupStatusBar() {
    val window: Window = requireActivity().window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = getMyColor(R.color.white)
  }
}