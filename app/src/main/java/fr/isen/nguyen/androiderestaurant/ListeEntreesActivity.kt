package fr.isen.nguyen.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ListeEntreesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_entrees)

        val categoryName = intent.getStringExtra("category").toString()
        val pageTitle = findViewById<TextView>(R.id.category)
        pageTitle.text=categoryName
    }
}