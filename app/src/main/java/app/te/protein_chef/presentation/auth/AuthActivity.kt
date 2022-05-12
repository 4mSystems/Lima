package app.te.protein_chef.presentation.auth

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import app.te.protein_chef.R
import app.te.protein_chef.presentation.base.BaseActivity
import app.te.protein_chef.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>() {
  private lateinit var nav: NavController

  override
  fun getLayoutId() = R.layout.activity_auth
  override fun setUpViews() {
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
    nav = navHostFragment.findNavController()
  }

  override fun onSupportNavigateUp(): Boolean {
    return nav.navigateUp() || super.onSupportNavigateUp()
  }


}