package fr.isen.nguyen.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.nguyen.androiderestaurant.databinding.ActivityDisplayMenuBinding
import fr.isen.nguyen.androiderestaurant.model.DataResult
import fr.isen.nguyen.androiderestaurant.model.Dish
import org.json.JSONException
import org.json.JSONObject

private lateinit var binding: ActivityDisplayMenuBinding

class DisplayMenuActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDisplayMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val categoryTitle = intent.getStringExtra("category").toString()
        binding.category.text = categoryTitle

        loadDataFromCategory(categoryTitle?:"")
    }

    private fun displayCategories(categories: List<Dish>) {
        binding.dishList.isVisible = true

        binding.dishList.layoutManager = LinearLayoutManager(this)
        binding.dishList.adapter =
            CategoryListAdapter(
                categories
            ) {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("dishTitle", it)
                startActivity(intent)
            }
    }

    private fun loadDataFromCategory(category: String) {
        val postUrl = "http://test.api.catering.bluecodegames.com/menu"
        val requestQueue = Volley.newRequestQueue(this)
        val postData = JSONObject()
        try {
            postData.put("id_shop", "1")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            postUrl,
            postData,
            {
                val gson: DataResult = Gson().fromJson(it.toString(), DataResult::class.java)
                val categories = gson.data.firstOrNull { it.name == category }?.dishes
                if (categories != null) {
                    displayCategories(categories)
                }
            },
            { error -> error.printStackTrace() })
        requestQueue.add(jsonObjectRequest)
    }
}