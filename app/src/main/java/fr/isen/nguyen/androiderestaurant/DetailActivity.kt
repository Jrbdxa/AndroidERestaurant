package fr.isen.nguyen.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import fr.isen.nguyen.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.nguyen.androiderestaurant.model.Basket
import fr.isen.nguyen.androiderestaurant.model.Dish
import fr.isen.nguyen.androiderestaurant.model.Order
import java.io.File

private lateinit var binding: ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

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
                quantity = 0
            }
        }
    }

    fun saveBasket(dish: Dish, quantity: Int) {
        val order = Order(dish.title, quantity)
        val gson = Gson()
        val file = File(cacheDir.absolutePath + fileName)

        if (file.exists()) {
            var basket: Basket = gson.fromJson(file.readText(), Basket::class.java)
            basket.orders.firstOrNull{it.dishName == dish.title}?.let {
                it.quantity += quantity
            }?: run {
                basket.orders.plus(order)
            }
            file.writeText(gson.toJson(basket))
        }
        else {
            file.writeText(gson.toJson(Basket(listOf(order))))
        }

    }

}