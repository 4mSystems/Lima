package app.te.lima_zola.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentHomeBinding
import app.te.lima_zola.domain.home.models.HomeMainData
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import app.te.lima_zola.presentation.base.utils.openBrowser
import app.te.lima_zola.presentation.home.adapters.CategoriesAdapter
import app.te.lima_zola.presentation.home.adapters.HomeSliderAdapter
import app.te.lima_zola.presentation.home.eventListener.HomeEventListener
import app.te.lima_zola.presentation.home.ui_state.HomeUiState
import app.te.lima_zola.presentation.home.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeEventListener {
  private val viewModel: HomeViewModel by viewModels()
  private val categoriesAdapter = CategoriesAdapter()
  private val sliderAdapter = HomeSliderAdapter(this)

  override
  fun getLayoutId() = R.layout.fragment_home

  override
  fun setBindingVariables() {
    binding.eventListener = this
  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.homeResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            setupUiState(it.value.data)
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }

  }

  private fun setupUiState(homeMainData: HomeMainData) {
    val homeUiState = HomeUiState(homeMainData)
    binding.uiState = homeUiState
    //setupSlider
    sliderAdapter.update(homeUiState.setUpSlider())
    binding.imageSlider.setSliderAdapter(sliderAdapter)
    // setCategories
    categoriesAdapter.differ.submitList(homeUiState.setUpCategories())
    binding.rcOffers.setUpAdapter(categoriesAdapter, "2", "1")
  }

  override fun openNotifications() {
//    navigateSafe(HomeFragmentDirections.actionHomeFragmentToNotificationsFragment())
  }

  override fun openMap() {
//    navigateSafe(HomeFragmentDirections.actionHomeFragmentToNavMap())
  }


  override fun openSliderUrl(url: String) {
    openBrowser(requireContext(), url)
  }

  override fun openPackageDetails(packageId: Int, title: String) {
//    navigateSafe(
//      HomeFragmentDirections.actionHomeFragmentToPackageCategoriesFragment(
//        title,
//        packageId
//      )
//    )
  }


}