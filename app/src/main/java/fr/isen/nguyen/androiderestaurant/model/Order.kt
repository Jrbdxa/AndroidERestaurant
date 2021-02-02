package fr.isen.nguyen.androiderestaurant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Order(@SerializedName("dishName") val dishName: String, @SerializedName("quantity") var quantity: Int, @SerializedName("dishPrice") val dishPrice: Int): Serializable {
    fun getPrice() = Unit
}