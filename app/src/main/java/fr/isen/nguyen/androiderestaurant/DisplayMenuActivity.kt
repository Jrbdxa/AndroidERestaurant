package fr.isen.nguyen.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.nguyen.androiderestaurant.databinding.ActivityDisplayMenuBinding

private lateinit var binding: ActivityDisplayMenuBinding
private lateinit var linearLayoutManager: LinearLayoutManager

class DisplayMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_menu)

        val binding = ActivityDisplayMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.category.text = intent.getStringExtra("category").toString()

        linearLayoutManager = LinearLayoutManager(this)
        binding.dishList.layoutManager = linearLayoutManager


    }
}