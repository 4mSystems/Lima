package app.te.lima_zola.presentation.splash

import android.content.ContentResolver
import android.net.Uri
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentSplashBinding
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.getMyColor
import app.te.lima_zola.presentation.base.extensions.navigateSafe
import app.te.lima_zola.presentation.base.extensions.openActivityAndClearStack
import app.te.lima_zola.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(), SplashEventListener {

    private val viewModel: SplashViewModel by viewModels()

    override
    fun getLayoutId() = R.layout.fragment_splash


    override fun onResume() {
        super.onResume()
        binding.video.requestLayout()
        binding.video.setOnPreparedListener {
            it.isLooping = false
        }
        binding.video.setVideoURI(
            Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + requireActivity().packageName + "/" + R.raw.splash_video
            )
        )
        binding.video.start()
        binding.video.setOnCompletionListener {
            viewModel.viewModelScope.launch {
                viewModel.generalUseCases.checkFirstTimeUseCase().collect { isFirst ->
                    if (isFirst) {
                        openOnBoarding()
                    } else {
                        openHome()
                    }
                }
            }
        }
    }

    override fun openHome() {

        openActivityAndClearStack(HomeActivity::class.java)

    }

    override fun openOnBoarding() {
        setLanguage("ar")
        navigateSafe(SplashFragmentDirections.actionSplashFragmentToTutorialFragment())
    }

    override fun setupStatusBar() {
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getMyColor(R.color.splash)
    }
}