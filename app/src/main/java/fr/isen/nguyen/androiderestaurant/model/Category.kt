package fr.isen.nguyen.androiderestaurant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Category(@SerializedName("name_fr") val name:String, @SerializedName("items") val dishes:List<Dish>): Serializable {
}