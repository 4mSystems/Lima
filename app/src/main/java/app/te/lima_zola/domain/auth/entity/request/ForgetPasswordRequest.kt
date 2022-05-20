package app.te.lima_zola.domain.auth.entity.request

import androidx.annotation.Keep
import app.te.lima_zola.presentation.base.utils.Constants

@Keep
data class ForgetPasswordRequest(
  var phone: String = "",
  var type: String = Constants.Verify,
)