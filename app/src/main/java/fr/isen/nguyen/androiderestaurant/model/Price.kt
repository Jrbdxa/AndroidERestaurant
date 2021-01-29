package fr.isen.nguyen.androiderestaurant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Price (
        @SerializedName("price") val value:String,
        @SerializedName("size") val size:String
): Serializable {

}