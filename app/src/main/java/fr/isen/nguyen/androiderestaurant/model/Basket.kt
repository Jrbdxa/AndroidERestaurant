package fr.isen.nguyen.androiderestaurant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Basket(@SerializedName("orders") var orders: List<Order>, @SerializedName("totalQuantity") var totalQuantity: Int): Serializable {
}