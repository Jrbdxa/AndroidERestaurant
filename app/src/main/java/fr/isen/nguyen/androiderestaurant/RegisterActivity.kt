package fr.isen.nguyen.androiderestaurant

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.nguyen.androiderestaurant.databinding.ActivityRegisterBinding
import fr.isen.nguyen.androiderestaurant.model.JsonRegisterResponse
import org.json.JSONException
import org.json.JSONObject

private lateinit var binding: ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        var id = sharedPreferences.getString("id", "")

        if(id != "") {
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }

        binding.register.setOnClickListener{
            if(id == "")
                registerUser()
        }
    }

    private fun registerUser() {
        var name = binding.name.text
        var firstName = binding.firstName.text
        var address = binding.address.text
        var mail = binding.mail.text
        var password = binding.password.text


            val postUrl = "http://test.api.catering.bluecodegames.com/user/register"
            val requestQueue = Volley.newRequestQueue(this)
            val postData = JSONObject()
            try {
                postData.put("id_shop", "1")
                postData.put("firstname", firstName)
                postData.put("lastname", name)
                postData.put("address", address)
                postData.put("email", mail)
                postData.put("password", password)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.POST,
                    postUrl,
                    postData,
                    {
                        val result = Gson().fromJson(it.toString(), JsonRegisterResponse::class.java)
                        println(result.data.id)
                        val sharedPreferences = getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
                        sharedPreferences.edit().putString("id", result.data.id).apply()
                        val intent = Intent(this, OrderActivity::class.java)
                        startActivity(intent)
                    },
                    { error -> error.printStackTrace() })
            requestQueue.add(jsonObjectRequest)

    }
}