package za.co.varsitycollege.st10034334.awsapi_icetask3

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import za.co.varsitycollege.st10034334.awsapi_icetask3.RetrofitClient.getClient
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MainActivity : AppCompatActivity() {
    private lateinit var txtAPICall : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Binding.
        txtAPICall = findViewById(R.id.txtAPICall);

        fetchData();

    }


    fun fetchData() {
        val apiService: APIService = getClient("http://13.60.66.91:3100/")!!.create(
            APIService::class.java
        )

        val call: Call<String>? = apiService.message
        if (call != null) {
            call.enqueue(object : Callback<String?> {
                override fun onResponse(call: Call<String?>?, response: Response<String?>) {
                    if (response.isSuccessful()) {
                        //Handles the successful response.
                        val data: String = response.toString()
                        txtAPICall.text = data;
                    } else {
                        //Handle errors.
                        Log.e("API Error", "Response code: " + response.code());
                    }
                }

                override fun onFailure(call: Call<String?>?, t: Throwable?) {
                    //Handle failure.
                    if (t != null) {
                        Log.e("API Failure", t.message.toString())
                    };
                }
            })
        }
    }


}