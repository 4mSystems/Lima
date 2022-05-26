package app.te.lima_zola.domain.auth.entity.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UserResponse(
  @SerializedName("suspend")
  var suspend: String = "",

  @SerializedName("image")
  val image: String = "",

  @SerializedName("token_api")
  val jwt: String = "",

  @SerializedName("subscriber")
  val subscriber: Int = 0,
  @SerializedName("subscription_ended_at")
  val subscription_ended_at: String = "",

  @SerializedName("city_id")
  val city_id: Int = 0,

  @SerializedName("city_name")
  val city_name: String = "",

  @SerializedName("phone")
  val phone: String = "",

  @SerializedName("name")
  val name: String = "",

  @SerializedName("id")
  val id: Int = 0,

  @SerializedName("email")
  val email: String = "",

  )
