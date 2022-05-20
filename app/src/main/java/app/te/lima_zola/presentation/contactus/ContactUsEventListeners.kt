package app.te.lima_zola.presentation.contactus

interface ContactUsEventListeners {
  fun openContactUrl(url: String)
  fun openWhatsApp(number: String)
}