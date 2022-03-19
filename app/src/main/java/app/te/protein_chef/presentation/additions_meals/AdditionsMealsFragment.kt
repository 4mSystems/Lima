package app.te.protein_chef.presentation.additions_meals

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.protein_chef.domain.utils.Resource
import app.te.protein_chef.R
import app.te.protein_chef.presentation.base.BaseFragment
import app.te.protein_chef.presentation.base.extensions.*
import app.te.protein_chef.presentation.meals.adapters.MainMealsCategoriesAdapter
import app.te.protein_chef.presentation.meals.adapters.MealsAdapter
import app.te.protein_chef.presentation.meals.listeners.MealsListener
import app.te.protein_chef.presentation.meals.ui_state.MainMealsUiState
import app.te.protein_chef.presentation.meals.ui_state.MealsUiState
import app.te.protein_chef.presentation.meals.viewModels.MealsViewModel
import app.te.protein_chef.databinding.FragmentMealsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class AdditionsMealsFragment : BaseFragment<FragmentMealsBinding>(),
  MealsListener {
  private val viewModel: MealsViewModel by viewModels()
  private val adapter = MainMealsCategoriesAdapter(this)
  private val mealsAdapter = MealsAdapter(null, this)
  private val listOfMeals = mutableListOf<MutableList<MealsUiState>>()

  override
  fun getLayoutId() = R.layout.fragment_meals

  override fun setBindingVariables() {
    binding.eventListener = this
    getMeals(null)
  }

  private fun getMeals(mealTypeId: Int?) {
//    viewModel.getMenuMeals(
//      MealsFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).packageId,
//      MealsFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).selectedDate,
//      mealTypeId
//    )
  }

  override fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.menuResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            val mainMealsUiState: MainMealsUiState = it.value as MainMealsUiState
            bindUi(mainMealsUiState)

          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }

  }

  private fun bindUi(mainMealsUiState: MainMealsUiState) {
    // update package data
    binding.packageUiState = mainMealsUiState.mealTypeUiState

    // update main category adapter
    if (adapter.differ.currentList.size == 0) {
      adapter.differ.submitList(mainMealsUiState.categoryMenuUiItemList)
      binding.rcMainMeals.setUpAdapter(adapter, "1", "2")
    }
    //  update Meals
    listOfMeals.add(mainMealsUiState.mealsUiStateList)
    updateMealsAdapter(mainMealsUiState.mealsUiStateList)
  }

  private fun updateMealsAdapter(mealsUiStateList: MutableList<MealsUiState>) {
    mealsAdapter.differ.submitList(mealsUiStateList)
    binding.rcMeals.setUpAdapter(mealsAdapter, "1", "1")
  }

  override fun changeCategoryType(type: Int) {
    if (adapter.currentPosition > adapter.differ.currentList.size - 1) {
      continueOrdering(0, "")
    } else {
      adapter.changeSelected(type)
      if (listOfMeals.size < adapter.currentPosition) {
        getMeals(adapter.lastSelected)
      } else {
        updateMealsAdapter(listOfMeals[adapter.lastPosition])
      }
    }
  }

  override fun openItemDetails(meal_id: Int, meal_name: String) {
//    navigateSafe(
//      MealsFragmentDirections.actionMealsFragmentToMealDetailsFragment(
//        meal_id,
//        meal_name,
//        MealsFragmentArgs.fromSavedStateHandle(viewModel.savedStateHandle).title
//      )
//    )
  }

  override fun continueOrdering(meal_id: Int, meal_name: String) {
//    navigateSafe(MealsFragmentDirections.actionMealsFragmentToAdditionsDialog())
  }

}