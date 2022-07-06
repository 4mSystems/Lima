package app.te.lima_zola.presentation.auth.log_in

import app.te.lima_zola.presentation.base.events.BaseEventListener

interface LoginEventListener :BaseEventListener {
  fun login()
  fun toRegister()
  fun forgetPassword()
  fun openHome()
}