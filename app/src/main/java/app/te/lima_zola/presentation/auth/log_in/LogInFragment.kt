package app.te.lima_zola.presentation.auth.log_in

import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentLogInBinding
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import app.te.lima_zola.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>(), LoginEventListener {

    private val viewModel: LogInViewModel by viewModels()

    override
    fun getLayoutId() = R.layout.fragment_log_in

    override
    fun setBindingVariables() {
        binding.request = viewModel.request
        binding.eventListener = this
    }


    override
    fun setupObservers() {
        lifecycleScope.launchWhenResumed {
            viewModel.logInResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        hideKeyboard()
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        openHome()
                    }
                    is Resource.Failure -> {
                        hideLoading()
                        handleApiError(
                            it,
                            retryAction = { viewModel.onLogInClicked() })
                    }
                }
            }
        }
    }


    override fun openHome() {
        requireActivity().openActivityAndClearStack(HomeActivity::class.java)
    }

    override fun login() {
        viewModel.onLogInClicked()
    }

    override fun toRegister() {
        navigateSafe(LogInFragmentDirections.actionLogInFragmentToSignUpFragment())
    }

    override fun forgetPassword() {
        navigateSafe(LogInFragmentDirections.actionLogInFragmentToForgotPasswordFragment())
    }

    override fun back() {
        requireActivity().finish()
    }

    override fun setupStatusBar() {
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getMyColor(R.color.colorPrimaryDark)
    }
}