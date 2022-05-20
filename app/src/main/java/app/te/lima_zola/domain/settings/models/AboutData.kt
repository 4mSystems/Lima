package app.te.lima_zola.domain.settings.models

import com.google.gson.annotations.SerializedName

data class AboutData(
  @SerializedName("whatsapp")
  var whatsapp: String = "",

  @SerializedName("monkez_recieve")
  val monkezRecieve: String? = "",

  @SerializedName("monkez_name")
  val monkezName: String = "",

  @SerializedName("facebook")
  val facebook: String = "",

  @SerializedName("created_at")
  val createdAt: String = "",

  @SerializedName("youtube")
  val youtube: String = "",

  @SerializedName("body")
  val body: String = "",

  @SerializedName("deleted_at")
  val deletedAt: Any = "",

  @SerializedName("monkez_pre_reserve")
  val monkezPreReserve: String = "",

  @SerializedName("twitter")
  val twitter: String = "",

  @SerializedName("updated_at")
  val updatedAt: String = "",

  @SerializedName("phone")
  val phone: String = "",

  @SerializedName("monkez_system")
  val monkezSystem: String = "",

  @SerializedName("id")
  val id: Int = 0,

  @SerializedName("email")
  val email: String = "",

  @SerializedName("monkez_phone")
  val monkezPhone: String = "",
)
