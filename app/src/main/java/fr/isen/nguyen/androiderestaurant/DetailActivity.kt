package fr.isen.nguyen.androiderestaurant

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import fr.isen.nguyen.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.nguyen.androiderestaurant.model.Basket
import fr.isen.nguyen.androiderestaurant.model.Dish
import fr.isen.nguyen.androiderestaurant.model.Order
import java.io.File

private lateinit var binding: ActivityDetailBinding

class DetailActivity : BaseActivity() {

    val fileName = "basket.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var quantity: Int = 0
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra("dishTitle") as Dish
        binding.detailName.text = dish.title
        binding.detailDescription.text = dish.ingredients.map{ e -> e.title }.joinToString(", " )
        if(dish.getAllPictures() != null) {
            binding.imageSlider.adapter = ImageSliderAdapter(this, dish.getAllPictures()!!)
        }
        binding.order.text = "Order for " + (quantity*dish.prices[0].value.toInt()).toString() + " €"

        binding.plus.setOnClickListener {
            quantity += 1
            binding.amount.text = quantity.toString()
            binding.order.text = "Order for " + (quantity*dish.prices[0].value.toInt()).toString() +" €"
        }
        binding.minus.setOnClickListener {
            if(quantity > 0)
                quantity -= 1
            binding.amount.text = quantity.toString()
            binding.order.text = "Order for " + (quantity*dish.prices[0].value.toInt()).toString() +" €"
        }

        binding.order.setOnClickListener{
            if(quantity > 0) {
                saveBasket(dish, quantity)
                val snack = Snackbar.make(it, "Added " + quantity + " items to basket", Snackbar.LENGTH_LONG)
                snack.show()
                savePreferences(quantity)
                quantity = 0
            }
        }
    }

    fun saveBasket(dish: Dish, quantity: Int) {
        val order = Order(dish.title, quantity, dish.prices[0].value.toInt())
        val gson = Gson()
        val file = File(cacheDir.absolutePath + fileName)

        if (file.exists()) {
            var basket: Basket = gson.fromJson(file.readText(), Basket::class.java)
            basket.orders.firstOrNull{it.dishName == dish.title}?.let {
                it.quantity += quantity
            }?: run {
                basket.orders.add(order)
            }
            basket.totalQuantity += quantity
            file.writeText(gson.toJson(basket))
        }
        else {
            file.writeText(gson.toJson(Basket(mutableListOf(order), quantity)))
        }
    }

    private fun savePreferences(quantity: Int) {
        val preference = getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        val total = preference.getInt("quantity", 0)
        val editor = preference.edit()
        editor.putInt("quantity", total + quantity)
        editor.commit()
    }

}