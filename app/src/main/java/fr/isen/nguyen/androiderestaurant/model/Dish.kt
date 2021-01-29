package fr.isen.nguyen.androiderestaurant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Dish (
    @SerializedName("name_fr") val title:String,
    @SerializedName("ingredients") val ingredients:List<Ingredient>,
    @SerializedName("images") val pictures:List<String>,
    @SerializedName("prices") val prices:List<Price>
): Serializable {

    fun getThumbnail(): String? {
        return if (pictures.isNotEmpty() && pictures[0].isNotEmpty())
            pictures[0]
        else
            null
    }

    fun getAllPictures() =
        if (pictures.isNotEmpty() && pictures.any{ it.isNotEmpty() })
            pictures.filter{ it.isNotEmpty() }
        else
            null


}