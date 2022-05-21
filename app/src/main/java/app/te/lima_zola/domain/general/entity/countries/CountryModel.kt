package app.te.lima_zola.domain.general.entity.countries

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CountryModel(
  @PrimaryKey(autoGenerate = true)
  val roomId: Int? = null,

  @SerializedName("image")
  var image: String = "",

  @SerializedName("name")
  val name: String = "",

  @SerializedName("id")
  val id: Int = 0,
  @SerializedName("govs")
  val govsData: List<GovernModel> = listOf(),

)

