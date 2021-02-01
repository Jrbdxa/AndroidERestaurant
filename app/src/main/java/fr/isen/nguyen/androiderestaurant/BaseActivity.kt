package fr.isen.nguyen.androiderestaurant

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.isen.nguyen.androiderestaurant.databinding.ActivityBaseBinding

private lateinit var binding: ActivityBaseBinding

open class BaseActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val cart = menu?.findItem(R.id.action_cart)?.actionView
        val preferences = getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        val quantity = preferences.getInt("quantity", 0)
        cart?.findViewById<TextView>(R.id.cart_badge)?.text = quantity.toString()

        cart?.setOnClickListener{
            val intent = Intent(this, CartDetailActivity::class.java)
            startActivity(intent)
        }
        return super.onCreateOptionsMenu(menu)
    }

}