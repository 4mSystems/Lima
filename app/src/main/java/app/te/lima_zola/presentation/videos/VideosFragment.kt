package app.te.lima_zola.presentation.videos

import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentVideosBinding
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.domain.videos_articles.entity.request.AddToWishListRequest
import app.te.lima_zola.domain.videos_articles.entity.request.LikeRequest
import app.te.lima_zola.presentation.auth.AuthActivity
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import app.te.lima_zola.presentation.base.utils.Constants
import app.te.lima_zola.presentation.player.PlayerActivity
import app.te.lima_zola.presentation.videos.adapters.VideosAdapter
import app.te.lima_zola.presentation.videos.adapters.SubCategoriesAdapter
import app.te.lima_zola.presentation.videos.eventListener.VideosEventListener
import app.te.lima_zola.presentation.videos.viewModels.VideosViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class VideosFragment : BaseFragment<FragmentVideosBinding>(), VideosEventListener {
    private val viewModel: VideosViewModel by viewModels()
    private lateinit var subCategoriesAdapter: SubCategoriesAdapter
    private var adapter = VideosAdapter(this)

    override
    fun getLayoutId() = R.layout.fragment_videos

    override
    fun setBindingVariables() {
        subCategoriesAdapter = SubCategoriesAdapter(this)
        binding.eventListener = this
    }

    override
    fun setupObservers() {

        adapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading && !isDetached)
                binding.contentLoading.shimmerFrameLayout.visibility = View.VISIBLE
            else
                binding.contentLoading.shimmerFrameLayout.visibility = View.GONE

            if (loadState.source.refresh is LoadState.NotLoading &&
                loadState.append.endOfPaginationReached && (adapter.itemCount
                    ?: 0) < 1
            ) {
                // getting the error
                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }

                error?.let {
                    if (it.error.message != null)
                        if (it.error.message?.isNotEmpty() == true)
                            Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG)
                                .show()
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.videoArticlesResponse.collect {
                adapter.submitData(it)
                binding.layoutVideos.rcVideos.setUpAdapter(adapter, "1", "1")
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.subCategoryResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        hideKeyboard()
                        binding.layoutSubCategory.subCategoryLoading.shimmerFrameLayout.visibility =
                            View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.layoutSubCategory.subCategoryLoading.shimmerFrameLayout.visibility =
                            View.GONE
                        subCategoriesAdapter.differ.submitList(
                            viewModel.setupSubCategory(
                                it.value.data,
                                getString(R.string.all_cat)
                            )
                        )
                        binding.layoutSubCategory.rcSubCategories.setUpAdapter(
                            subCategoriesAdapter,
                            "1",
                            "2"
                        )
                    }
                    is Resource.Failure -> {
                        binding.layoutSubCategory.subCategoryLoading.shimmerFrameLayout.visibility =
                            View.GONE
                        handleApiError(it)
                    }
                }
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.actionsResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        hideKeyboard()
                    }
                    is Resource.Success -> {
                        makeActionSound(requireContext())
                    }
                    is Resource.Failure -> {
                        handleApiError(it)
                    }
                }
            }
        }
    }

    override fun setupStatusBar() {
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getMyColor(R.color.details_status_bar)
    }

    override fun changeSubCategoryItem(itemId: Int, currentPosition: Int) {
        lifecycleScope.launch {
            adapter.submitData(PagingData.empty())
            viewModel.getVideosArticles(itemId.takeIf { it != 0 }
                ?: VideosFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).catId) // all videos
        }
        subCategoriesAdapter.onItemChange(currentPosition = currentPosition)
    }

    override fun openContent(itemId: Int, content: String) {
        if (itemId != Constants.FREE) {
            val intent = PlayerActivity.getStartIntent(requireActivity(), content)
            requireActivity().startActivity(intent)
        } else
            checkIsSubscribed()
    }

    private fun checkIsSubscribed() {
        lifecycleScope.launch {
            viewModel.userLocalUseCase.invoke().collect { user ->
                if (user.name.isNotEmpty() && user.subscriber == Constants.FREE)
                    navigateSafe(VideosFragmentDirections.actionVideosFragmentToNavSubscribe())
                else
                    openIntentActivity(AuthActivity::class.java, R.id.logInFragment)

            }
        }
    }

    override fun makeLike(itemId: Int) {
        viewModel.likeContent(LikeRequest(itemId))
    }

    override fun makeWishList(itemId: Int) {
        viewModel.addToWishList(AddToWishListRequest(itemId))
    }

    override fun back() {
        backToPreviousScreen()
    }


}