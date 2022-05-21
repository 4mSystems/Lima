package app.te.lima_zola.presentation.account

interface AccountEventListener {
  fun openProfile()
  fun openFavorite()
  fun openSubscribe()
  fun openChangeLanguage()
  fun logout()
}