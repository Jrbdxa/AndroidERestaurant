package fr.isen.nguyen.androiderestaurant

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.nguyen.androiderestaurant.databinding.ActivityOrderBinding
import fr.isen.nguyen.androiderestaurant.model.Basket
import fr.isen.nguyen.androiderestaurant.model.JsonRegisterResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.File

private lateinit var binding: ActivityOrderBinding

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        order()
    }

    private fun order() {
        val sharedPreferences = getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        val id = sharedPreferences.getString("id", "")

        val basket: Basket? = loadDataFromBasket()

        val postUrl = "http://test.api.catering.bluecodegames.com/user/order"
        val requestQueue = Volley.newRequestQueue(this)
        val postData = JSONObject()
        try {
            postData.put("id_shop", "1")
            postData.put("id_user", id)
            if(basket != null)
                postData.put("msg", Gson().toJson(basket))
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST,
                postUrl,
                postData,
                {
                    println(it)
                    binding.orderStatus.text = "Commande envoyée avec succès !"
                },
                { error ->
                    error.printStackTrace()
                    binding.orderStatus.text = "Echec de la requête. Réessayez plus tard."
                })
        requestQueue.add(jsonObjectRequest)
    }

    private fun loadDataFromBasket(): Basket? {
        val gson = Gson()
        val file = File(cacheDir.absolutePath + "basket.json")
        if(file.exists()) {
            var basket: Basket = gson.fromJson(file.readText(), Basket::class.java)
            return basket
        }
        else
            return null
    }
}