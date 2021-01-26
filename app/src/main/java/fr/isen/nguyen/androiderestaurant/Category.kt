package fr.isen.nguyen.androiderestaurant

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Category(@SerializedName("name") val name:String, @SerializedName("dishes") val dishes:List<Dish>): Serializable {
}