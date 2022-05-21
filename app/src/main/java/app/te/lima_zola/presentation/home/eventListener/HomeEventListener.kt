package app.te.lima_zola.presentation.home.eventListener

interface HomeEventListener {
  fun openNotifications()
  fun openMap()
  fun openSliderUrl(url: String)
  fun openPackageDetails(packageId: Int, title: String)
}