package app.te.lima_zola.presentation.about

import com.google.gson.annotations.SerializedName

data class AboutDataUiState(
  @SerializedName("whatsapp")
  var whatsapp: String = "",

  @SerializedName("monkez_recieve")
  val monkezRecieve: String? = "",

  @SerializedName("monkez_name")
  val monkezName: String = "",

  @SerializedName("facebook")
  val facebook: String = "",

  @SerializedName("youtube")
  val youtube: String = "",

  @SerializedName("body")
  val body: String = "",

  @SerializedName("monkez_pre_reserve")
  val monkezPreReserve: String = "",

  @SerializedName("twitter")
  val twitter: String = "",

  @SerializedName("phone")
  val phone: String = "",

  @SerializedName("monkez_system")
  val monkezSystem: String = "",

  @SerializedName("email")
  val email: String = "",

  @SerializedName("monkez_phone")
  val monkezPhone: String = "",
)
