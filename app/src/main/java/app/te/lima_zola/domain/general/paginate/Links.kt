package app.te.lima_zola.domain.general.paginate

data class Links(
  val next: String? = null,
  val last: String = "",
  val prev: String = "",
  val first: String = "",
)