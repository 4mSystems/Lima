package app.te.lima_zola.presentation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import app.te.lima_zola.presentation.base.BaseActivity
import app.te.lima_zola.databinding.ActivityHomeBinding
import app.te.lima_zola.databinding.NavigateHeaderBinding
import app.te.lima_zola.presentation.home.ui_state.navigation_uiState.HeaderUiState
import app.te.lima_zola.presentation.home.viewModels.HomeActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import app.te.lima_zola.R

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var nav: NavController
  private lateinit var headerBinding: NavigateHeaderBinding
  private val viewModel: HomeActivityViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.activity_home

  override
  fun setUpBottomNavigation() {
    updateHeaderLayout()
    setUpNavigationWithGraphs()
  }

  private fun updateHeaderLayout() {
    headerBinding =
      DataBindingUtil.inflate(
        LayoutInflater.from(this),
        R.layout.navigate_header,
        binding.navigationView,
        false
      )
    val uiState = HeaderUiState()
    lifecycleScope.launchWhenResumed {
      viewModel.userLocalUseCase.invoke().collectLatest {
//        uiState.updateUi(name = it.name, expireDate = it.expireAt)
        headerBinding.ui = uiState
      }
    }
    binding.navigationView.addHeaderView(headerBinding.root)
  }

  private fun setUpNavigationWithGraphs() {
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.fragment_host_container) as NavHostFragment
    nav = navHostFragment.findNavController()
    appBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.home_fragment,
        R.id.about_fragment,
        R.id.suggestions_fragment,
        R.id.contact_fragment,
        R.id.agents_fragment,
      ),
      binding.root
    )
    setSupportActionBar(binding.toolbar)
    setupActionBarWithNavController(nav, appBarConfiguration)
    binding.navigationView.setupWithNavController(nav)

    navChangeListener()
  }

  @SuppressLint("UseCompatLoadingForDrawables")
  private fun navChangeListener() {
    nav.addOnDestinationChangedListener { _, destination, _ ->
      if (destination.id == R.id.home_fragment
//        || destination.id == R.id.myMealsFragment
//        || destination.id == R.id.accountFragment
//        || destination.id == R.id.moreFragment
      ) {

//        binding.bottomNavigationView.visibility = View.VISIBLE
//        binding.toolbar.visibility = View.GONE
      } else {

//        binding.bottomNavigationView.visibility = View.GONE
//        binding.toolbar.visibility = View.VISIBLE
      }
    }
  }


  override fun onSupportNavigateUp(): Boolean {
    return nav.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.top_app_bar, menu)
    val itemCart = menu?.findItem(R.id.cart_fragment)
    val itemAbout = menu?.findItem(R.id.about_fragment)
    nav.addOnDestinationChangedListener { _, destination, _ ->
      if (destination.id == R.id.home_fragment) {
        itemCart?.isVisible = true
        itemAbout?.isVisible = false
      } else {
        itemCart?.isVisible = false
        itemAbout?.isVisible = true
      }

    }
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return item.onNavDestinationSelected(nav) || super.onOptionsItemSelected(item)
  }


}