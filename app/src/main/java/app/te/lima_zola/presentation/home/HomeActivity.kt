package app.te.lima_zola.presentation.home

import android.view.MenuItem
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import app.te.lima_zola.presentation.base.BaseActivity
import app.te.lima_zola.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import app.te.lima_zola.R

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var nav: NavController

  override
  fun getLayoutId() = R.layout.activity_home

  override
  fun setUpBottomNavigation() {
    binding.bottomNavigationView.itemIconTintList = null
    setUpNavigationWithGraphs()
  }

  private fun setUpNavigationWithGraphs() {
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.fragment_host_container) as NavHostFragment
    nav = navHostFragment.findNavController()
    appBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.home_fragment,
        R.id.mother_fragment,
        R.id.accountFragment,
        R.id.moreFragment
      )
    )
    binding.bottomNavigationView.setupWithNavController(nav)
    navChangeListener()

  }

  private fun navChangeListener() {
    nav.addOnDestinationChangedListener { _, destination, _ ->
      if (destination.id == R.id.home_fragment
        || destination.id == R.id.mother_fragment
        || destination.id == R.id.accountFragment
        || destination.id == R.id.moreFragment
      ) {
        binding.bottomNavigationView.visibility = View.VISIBLE
      } else {
        binding.bottomNavigationView.visibility = View.GONE
      }
    }
  }

  override fun onSupportNavigateUp(): Boolean {
    return nav.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return item.onNavDestinationSelected(nav) || super.onOptionsItemSelected(item)
  }


}