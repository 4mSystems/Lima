package app.te.lima_zola.presentation.account

import android.util.Log
import android.view.View
import androidx.databinding.Bindable
import app.te.lima_zola.BR
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
    updateSubscribeVisibility = if (user.subscriber == 0)
      View.VISIBLE
    else
      View.GONE
    Log.e("updateSubscribeVisibility", "updateSubscribeVisibility: "+updateSubscribeVisibility)
    notifyPropertyChanged(BR.updateSubscribeVisibility)
  }

  private fun updateProfileVisibility() {
    updateProfileVisibility = if (user.name.isNotEmpty())
      View.VISIBLE
    else
      View.GONE
    notifyPropertyChanged(BR.updateProfileVisibility)

  }

}