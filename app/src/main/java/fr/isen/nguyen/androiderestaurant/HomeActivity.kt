package fr.isen.nguyen.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import fr.isen.nguyen.androiderestaurant.databinding.ActivityHomeBinding

private lateinit var binding: ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.entrees.setOnClickListener{
            val intent = Intent(this, ListeEntreesActivity::class.java)
            startActivity(intent)
        }
    }




}