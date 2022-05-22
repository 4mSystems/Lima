package app.te.lima_zola.presentation.settings

import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import app.te.lima_zola.R
import app.te.lima_zola.data.settings.mapToUiState
import app.te.lima_zola.databinding.FragmentTeamBinding
import app.te.lima_zola.domain.settings.models.Teams
import app.te.lima_zola.presentation.base.events.BaseEventListener
import app.te.lima_zola.presentation.settings.adapters.TeamAdapter
import app.te.lima_zola.presentation.settings.viewModels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TeamFragment : BaseFragment<FragmentTeamBinding>(), BaseEventListener {
  private val viewModel: SettingsViewModel by viewModels()
  private val teamAdapter = TeamAdapter()

  override
  fun getLayoutId() = R.layout.fragment_team
  override fun setBindingVariables() {
    binding.eventListener = this
    viewModel.getTeam()
  }

  override fun setupObservers() {
    lifecycleScope.launchWhenResumed {
      viewModel.team.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            updateUi(it.value.data)
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }
  }

  private fun updateUi(team: List<Teams>) {
    teamAdapter.differ.submitList(team)
    binding.rcTeam.setUpAdapter(teamAdapter, "2", "1")
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