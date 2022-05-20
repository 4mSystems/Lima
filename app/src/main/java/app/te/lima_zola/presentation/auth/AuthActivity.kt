package app.te.lima_zola.presentation.auth

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import app.te.lima_zola.R
import app.te.lima_zola.presentation.base.BaseActivity
import app.te.lima_zola.databinding.ActivityAuthBinding
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