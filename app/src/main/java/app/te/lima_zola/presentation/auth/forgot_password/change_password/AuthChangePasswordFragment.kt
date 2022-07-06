package app.te.lima_zola.presentation.auth.forgot_password.change_password

import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentAuthChangePasswordBinding
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.backToPreviousScreen
import app.te.lima_zola.presentation.base.extensions.getMyColor
import app.te.lima_zola.presentation.base.extensions.handleApiError
import app.te.lima_zola.presentation.base.extensions.hideKeyboard
import app.te.lima_zola.presentation.base.utils.showSuccessAlert
import app.te.lima_zola.presentation.profile.changePassword.ChangePasswordEventListener
import app.te.lima_zola.presentation.profile.changePassword.ChangePasswordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AuthChangePasswordFragment : BaseFragment<FragmentAuthChangePasswordBinding>(),
    ChangePasswordEventListener {

    private val viewModel: ChangePasswordViewModel by viewModels()
    val args: AuthChangePasswordFragmentArgs by navArgs()

    override
    fun getLayoutId() = R.layout.fragment_auth_change_password

    override
    fun setBindingVariables() {
        binding.request = viewModel.request
        binding.eventListener = this
    }

    override
    fun setupObservers() {
        lifecycleScope.launchWhenResumed {
            viewModel.changePasswordResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        hideKeyboard()
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        showSuccessAlert(requireActivity(), it.value.message)
                        backToPreviousScreen()
                    }
                    is Resource.Failure -> {
                        hideLoading()
                        handleApiError(it, retryAction = { viewModel.updatePassword() })
                    }
                    Resource.Default -> {
                    }
                }
            }
        }
    }

    override fun changePassword() {
        viewModel.changePassword(args.phone)
    }

    override fun setupStatusBar() {
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getMyColor(R.color.colorPrimary)
    }

    override fun back() {
        backToPreviousScreen()
    }
}