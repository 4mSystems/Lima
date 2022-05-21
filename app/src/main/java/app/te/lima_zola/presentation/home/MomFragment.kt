package app.te.lima_zola.presentation.home

import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentHomeBinding
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import app.te.lima_zola.presentation.home.adapters.CategoriesAdapter
import app.te.lima_zola.presentation.home.eventListener.HomeEventListener
import app.te.lima_zola.presentation.home.ui_state.CategoriesUiItemState
import app.te.lima_zola.presentation.home.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MomFragment : BaseFragment<FragmentHomeBinding>(), HomeEventListener {
  private val viewModel: HomeViewModel by viewModels()
  private val categoriesAdapter = CategoriesAdapter()

  override
  fun getLayoutId() = R.layout.fragment_home

  override
  fun setBindingVariables() {
    binding.eventListener = this
    viewModel.getHomeData(2)
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
            categoriesAdapter.differ.submitList(it.value.data.map { catItem ->
              CategoriesUiItemState(
                catItem
              )
            })
            binding.rcOffers.setUpAdapter(categoriesAdapter, "2", "1")
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }

  }

  override fun setupStatusBar() {
    val window: Window = requireActivity().window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = getMyColor(R.color.home_status_bar)
  }

  override fun openVideos() {
  }

  override fun openDocs() {
  }

}