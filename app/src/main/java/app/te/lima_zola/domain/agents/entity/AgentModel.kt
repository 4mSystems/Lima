package app.te.lima_zola.domain.agents.entity

import com.google.gson.annotations.SerializedName

data class AgentModel(
  @SerializedName("image")
  private var image: String = "",

  @SerializedName("address")
  private val address: String = "",

  @SerializedName("phone")
  private val phone: String = "",

  @SerializedName("whatsapp")
  private val whatsapp: String = "",

  @SerializedName("name")
  private val name: String = "",

  @SerializedName("id")
  private val id: Int = 0
)
