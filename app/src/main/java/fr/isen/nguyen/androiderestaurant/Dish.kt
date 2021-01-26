package fr.isen.nguyen.androiderestaurant

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Dish (
    @SerializedName("name_fr") val title:String,
    @SerializedName("ingredients") val ingredients:List<Ingredient>,
    @SerializedName("images") val pictures:List<String>,
    @SerializedName("prices") val prices:List<Price>,
): Serializable {



}