package fr.isen.nguyen.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.nguyen.androiderestaurant.databinding.ActivityDisplayMenuBinding
import org.json.JSONException
import org.json.JSONObject

private lateinit var binding: ActivityDisplayMenuBinding
private lateinit var linearLayoutManager: LinearLayoutManager
private lateinit var adapter: CategoryListAdapter

class DisplayMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_menu)

        val binding = ActivityDisplayMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.category.text = intent.getStringExtra("category").toString()

        linearLayoutManager = LinearLayoutManager(this)
        binding.dishList.layoutManager = linearLayoutManager
        binding.dishList.adapter = CategoryListAdapter(listOf(getString(R.string.pasta), getString(R.string.soup), getString(R.string.meat), getString(R.string.heresy)))

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
            { response -> println(response) },
            { error -> error.printStackTrace() })
        requestQueue.add(jsonObjectRequest)
    }
}