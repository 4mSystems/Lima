package app.te.lima_zola.presentation.account

import android.content.Context
import android.view.View
import androidx.databinding.Bindable
import app.te.lima_zola.BR
import app.te.lima_zola.R
import app.te.lima_zola.presentation.base.BaseUiState
import com.structure.base_mvvm.User

class AccountUiState(val user: User) : BaseUiState() {
  @Bindable
  var updateSubscribeVisibility: Int = View.GONE

  @Bindable
  var updateProfileVisibility: Int = View.GONE

  fun updateUi() {
    updateSubscribeVisibility()
    updateProfileVisibility()
  }

  private fun updateSubscribeVisibility() {
    updateSubscribeVisibility = if (user.subscriber == 0 && user.name.isNotEmpty())
      View.VISIBLE
    else
      View.GONE
    notifyPropertyChanged(BR.updateSubscribeVisibility)
  }

  private fun updateProfileVisibility() {
    updateProfileVisibility = if (user.name.isNotEmpty())
      View.VISIBLE
    else
      View.GONE
    notifyPropertyChanged(BR.updateProfileVisibility)

  }

  fun getLogUser(context: Context): String =
    if (user.name.isNotEmpty()) context.getString(R.string.log_out) else context.getString(R.string.login)
}