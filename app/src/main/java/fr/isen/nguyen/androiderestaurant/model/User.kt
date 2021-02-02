package fr.isen.nguyen.androiderestaurant.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: String,
    @SerializedName("id_shop") val id_shop: String,
    @SerializedName("email") val email: String,
    @SerializedName("firstname") val firstname: String,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("address") val address: String
)