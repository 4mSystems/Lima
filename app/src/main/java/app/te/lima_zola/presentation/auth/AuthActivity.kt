package app.te.lima_zola.presentation.auth

import app.te.lima_zola.R
import app.te.lima_zola.presentation.base.BaseActivity
import app.te.lima_zola.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>() {
  override
  fun getLayoutId() = R.layout.activity_auth


}