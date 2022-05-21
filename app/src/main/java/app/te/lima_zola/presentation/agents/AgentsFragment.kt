package app.te.lima_zola.presentation.agents

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.lima_zola.R
import app.te.lima_zola.databinding.FragmentAgentsBinding
import app.te.lima_zola.domain.utils.Resource
import app.te.lima_zola.presentation.agents.viewModel.AgentsViewModel
import app.te.lima_zola.presentation.base.BaseFragment
import app.te.lima_zola.presentation.base.extensions.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class AgentsFragment : BaseFragment<FragmentAgentsBinding>() {
  private val viewModel: AgentsViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_agents

  override
  fun setBindingVariables() {
//    binding.eventListener = this
  }

  override
  fun setupObservers() {

  }
}