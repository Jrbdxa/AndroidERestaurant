package fr.isen.nguyen.androiderestaurant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class JsonRegisterResponse(
    @SerializedName("data") val data: User,
    @SerializedName("code") val code: String
)