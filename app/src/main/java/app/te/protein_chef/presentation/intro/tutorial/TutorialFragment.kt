package app.te.protein_chef.presentation.intro.tutorial

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.protein_chef.domain.intro.entity.AppTutorialModel
import app.te.protein_chef.appTutorial.AppTutorialHelper
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.R
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.handleApiError
import app.te.protein_chef.presentation.base.extensions.hideKeyboard
import app.te.protein_chef.presentation.base.extensions.navigateSafe
import app.te.protein_chef.databinding.FragmentTutorialBinding
import dagger.hilt.android.AndroidEntryPoint

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
      .setContentColor(R.color.black_light)
      .setSliderContainerResourceID(R.id.tutorial_container)
      .setActiveIndicatorColor(R.color.colorPrimaryDark)
      .setInActiveIndicatorColor(R.color.black_light)
      .setAutoScrolling(false)
      .setNextButtonTextColor(R.color.white)
      .setNextButtonBackground(R.color.colorPrimaryDark)
      .setBtnPreviousTextBackground(R.color.colorBlack)
      .setPreviousTextColor(R.color.white)
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
    navigateSafe(TutorialFragmentDirections.actionTutorialFragmentToLanguageFragment())
  }
}