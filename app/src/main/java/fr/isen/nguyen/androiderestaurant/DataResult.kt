package fr.isen.nguyen.androiderestaurant

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataResult(@SerializedName ("name") val data: String): Serializable