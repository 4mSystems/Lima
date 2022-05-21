package app.te.lima_zola.presentation.intro.tutorial

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.domain.intro.entity.AppTutorialModel
import app.te.lima_zola.appTutorial.AppTutorialHelper
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.R
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.handleApiError
import app.te.lima_zola.presentation.base.extensions.hideKeyboard
import app.te.lima_zola.databinding.FragmentTutorialBinding
import app.te.lima_zola.presentation.base.extensions.navigateSafe
import app.te.lima_zola.presentation.base.extensions.openActivityAndClearStack
import app.te.lima_zola.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TutorialFragment : BaseFragment<FragmentTutorialBinding>() {

  private val viewModel: TutorialViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_tutorial

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  private fun setUpAppTutorial(tutorialModelData: List<AppTutorialModel> = ArrayList()) {
    AppTutorialHelper.Builder(requireActivity(), lifecycle)
      .setTutorialData(tutorialModelData)
      .setTitleColor(R.color.black)
      .setContentColor(R.color.medium_color)
      .setSliderContainerResourceID(R.id.tutorial_container)
      .setActiveIndicatorColor(R.color.colorPrimaryDark)
      .setInActiveIndicatorColor(R.color.black_light)
      .setAutoScrolling(false)
      .setNextButtonTextColor(R.color.white)
      .setNextButtonBackground(R.color.colorPrimaryDark)
//      .setPreviousTextColor(R.color.black)
      .setOpenAppTextColor(R.color.white)
      .setSkipTutorialClick { openIntro() }
      .create()
  }

  override
  fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.appTutorialResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            setUpAppTutorial(it.value.data)
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
          else -> {
          }
        }
      }
    }
  }

  private fun openIntro() {
    viewModel.setFirstTime(false)
    navigateSafe(TutorialFragmentDirections.actionTutorialFragmentToLogInFragment())
//    requireActivity().openActivityAndClearStack(HomeActivity::class.java)
  }
}