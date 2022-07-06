package app.te.lima_zola.presentation.auth.forgot_password

import app.te.lima_zola.presentation.base.events.BaseEventListener


interface ForgetPasswordEventListener : BaseEventListener {
  fun openConfirm()
  fun sendCode()
}