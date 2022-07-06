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
import app.te.lima_zola.presentation.base.utils.Constants
import app.te.lima_zola.presentation.home.adapters.CategoriesAdapter
import app.te.lima_zola.presentation.home.eventListener.HomeEventListener
import app.te.lima_zola.presentation.home.ui_state.CategoriesUiItemState
import app.te.lima_zola.presentation.home.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeEventListener {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var categoriesAdapter: CategoriesAdapter

    override
    fun getLayoutId() = R.layout.fragment_home

    override
    fun setBindingVariables() {
        categoriesAdapter = CategoriesAdapter(this)
        viewModel.getHomeData(1)
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
                        categoriesAdapter.differ.submitList(it.value.data.categories.map { catItem ->
                            CategoriesUiItemState(
                                catItem
                            )
                        })
                        binding.rcOffers.setUpAdapter(categoriesAdapter, "2", "1")
                        viewModel.updateUser(it.value.data.subscriber)
                    }
                    is Resource.Failure -> {
                        hideLoading()
                        handleApiError(it)
                    }
                }
            }
        }

    }

    private fun openSubscribe() {
//        navigateSafe(HomeFragmentDirections.actionHomeFragmentToSubscribeWarningDialog(Constants.FREE))
    }

    override fun limaWarningDialog(param: Int?) {
        navigateSafe(HomeFragmentDirections.actionHomeFragmentToLimaWarningDialog(param ?: 0))
    }

    override fun setupStatusBar() {
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getMyColor(R.color.home_status_bar)
    }

    override fun openVideos(cat_id: Int, type: String) {
        if (type == Constants.VIDEO_TYPE) {
            if (cat_id == 4)
                limaWarningDialog(cat_id)
            else
                navigateSafe(HomeFragmentDirections.actionFragmentToVideosFragment(cat_id))
        } else
            navigateSafe(HomeFragmentDirections.actionHomeFragmentToDocumentsFragment(cat_id))
    }


}