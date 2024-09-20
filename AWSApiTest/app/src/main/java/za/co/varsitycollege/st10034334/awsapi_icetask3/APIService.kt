package za.co.varsitycollege.st10034334.awsapi_icetask3

import retrofit2.Call
import retrofit2.http.GET


interface APIService {
    @get:GET("/")
    val message: Call<String>?
}
