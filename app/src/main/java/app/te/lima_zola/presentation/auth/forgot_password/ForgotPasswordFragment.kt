package app.te.lima_zola.presentation.auth.forgot_password

import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentForgotPasswordBinding
import app.te.lima_zola.domain.auth.entity.request.ForgetPasswordRequest
import app.te.lima_zola.domain.auth.entity.request.RegisterRequest
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>(),
    ForgetPasswordEventListener {
    private val viewModel: ForgotPasswordViewModel by viewModels()

    override
    fun getLayoutId() = R.layout.fragment_forgot_password

    override
    fun setBindingVariables() {
        binding.request = viewModel.request
        binding.eventListener = this
    }

    override
    fun setupObservers() {
        lifecycleScope.launchWhenResumed {
            viewModel.forgetPasswordResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        hideKeyboard()
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        openConfirm()
                    }
                    is Resource.Failure -> {
                        hideLoading()
                        handleApiError(it, retryAction = { viewModel.sendCode() })
                    }
                    Resource.Default -> {
                    }
                }
            }
        }
    }

    override fun openConfirm() {
        navigateSafe(
            ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToConfirmCodeFragment(
                RegisterRequest(), viewModel.request
            )
        )
    }

    override fun setupStatusBar() {
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getMyColor(R.color.colorPrimary)
    }

    override fun sendCode() {
        viewModel.sendCode()
    }

    override fun back() {
        backToPreviousScreen()
    }


}