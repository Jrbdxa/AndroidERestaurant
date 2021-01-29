package fr.isen.nguyen.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.nguyen.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.nguyen.androiderestaurant.model.Dish

private lateinit var binding: ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra("dishTitle") as? Dish
        val numPages = dish?.pictures?.size

        binding.detailName.text = dish?.title
        binding.detailDescription.text = dish?.ingredients?.map{ e -> e.title }?.joinToString(", " )

        if(dish?.getAllPictures() != null) {
            binding.imageSlider.adapter = ImageSliderAdapter(this, dish.getAllPictures()!!)
        }
    }

}