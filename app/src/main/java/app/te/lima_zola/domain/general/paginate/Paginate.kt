package app.te.lima_zola.domain.general.paginate

import androidx.annotation.Keep

@Keep
open class Paginate(
  val meta: Meta = Meta(),
  val links: Links = Links(),
)