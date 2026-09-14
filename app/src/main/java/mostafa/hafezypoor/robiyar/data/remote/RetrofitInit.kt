package mostafa.hafezypoor.robiyar.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInit {
    private const val BASE_URL= "http://192.168.1.2/robiyar/"

    val api: ApiService by lazy {

        val gson = GsonBuilder()
            .serializeNulls()
            .setLenient()
            .create()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService::class.java)
    }
}