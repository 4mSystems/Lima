package app.te.lima_zola.presentation.auth.log_in

interface LoginEventListener {
  fun login()
  fun toRegister()
  fun openHome()
}