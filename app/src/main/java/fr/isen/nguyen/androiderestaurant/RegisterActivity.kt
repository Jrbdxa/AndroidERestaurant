package fr.isen.nguyen.androiderestaurant

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

        binding.register.setOnClickListener{
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
            },
            { error -> error.printStackTrace() })
        requestQueue.add(jsonObjectRequest)
    }
}