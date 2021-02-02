package fr.isen.nguyen.androiderestaurant

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import fr.isen.nguyen.androiderestaurant.databinding.ActivityCartDetailBinding
import fr.isen.nguyen.androiderestaurant.model.Basket
import fr.isen.nguyen.androiderestaurant.model.Dish
import java.io.File

private lateinit var binding: ActivityCartDetailBinding

class CartDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadDataFromBasket()

        binding.orderButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadDataFromBasket() {
        val gson = Gson()
        val file = File(cacheDir.absolutePath + "basket.json")

        if(file.exists()) {
            val basket = gson.fromJson(file.readText(), Basket::class.java)
            val basketRecycler = binding.basketRecycler
            basketRecycler.adapter = BasketListAdapter(basket.orders) {
                basket.orders.remove(it)
                resetBasket(basket)
            }
            basketRecycler.layoutManager = LinearLayoutManager(this)
            basketRecycler.isVisible = true
        }
    }

    fun resetBasket(basket: Basket) {
        val file = File(cacheDir.absolutePath + "basket.json")
        val count = basket.orders.sumBy{ it.quantity }
        val sharedPreferences = getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt("quantity", count).apply()
        file.writeText(Gson().toJson(basket))
    }
}