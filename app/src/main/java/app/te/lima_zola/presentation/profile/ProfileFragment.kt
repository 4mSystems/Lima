package app.te.lima_zola.presentation.profile

import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentProfileBinding
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.domain.utils.showCityPopUp
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import app.te.lima_zola.presentation.base.utils.showSuccessAlert
import app.te.lima_zola.presentation.home.HomeActivity
import app.te.lima_zola.presentation.profile.viewModels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(), ProfileEventListener {

    private val viewModel: ProfileViewModel by viewModels()

    override
    fun getLayoutId() = R.layout.fragment_profile

    override
    fun setBindingVariables() {
        binding.eventListener = this
    }


    override
    fun setupObservers() {
        lifecycleScope.launchWhenResumed {
            viewModel.updateProfileResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        hideKeyboard()
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        openHome(it.value.message)
                    }
                    is Resource.Failure -> {
                        hideLoading()
                        handleApiError(
                            it,
                            retryAction = { viewModel.updateProfile() })
                    }

                }
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.profileResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        hideKeyboard()
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        viewModel.updateRequest(it.value.data)
                        binding.request = viewModel.request
                    }
                    is Resource.Failure -> {
                        hideLoading()
                        handleApiError(
                            it,
                            retryAction = { viewModel.updateProfile() })
                    }

                }
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.citiesResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        hideKeyboard()
                        showLoading()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        viewModel.cities = it.value.data
                    }
                    is Resource.Failure -> {
                        hideLoading()
                        handleApiError(it, retryAction = { viewModel.getCities() })
                    }

                }
            }
        }

    }

    private fun openHome(message: String) {
        lifecycleScope.launch {
            delay(1000)
            showSuccessAlert(requireActivity(), message)
        }
        openActivityAndClearStack(HomeActivity::class.java)
    }

    override fun updateProfile() {
        viewModel.updateProfile()
    }

    override fun changePassword() {
        navigateSafe(ProfileFragmentDirections.actionProfileFragmentToChangePasswordFragment())
    }

    override fun setupStatusBar() {
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getMyColor(R.color.colorPrimary)
    }

    override fun back() {
        backToPreviousScreen()
    }

    override fun showCities() {
        if (viewModel.cities.isNotEmpty()) {
            val popUp = showCityPopUp(
                requireActivity(),
                binding.inputCity,
                viewModel.cities
            )
            popUp.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener,
                PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem): Boolean {
                    binding.city.setText(viewModel.cities[item.itemId].name)
                    viewModel.request.city_id = viewModel.cities[item.itemId].id.toString()
                    return true
                }

            })
        } else {
            viewModel.getCities()
        }
    }
}