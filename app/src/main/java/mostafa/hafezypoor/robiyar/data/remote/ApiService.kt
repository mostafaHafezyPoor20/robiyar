package mostafa.hafezypoor.robiyar.data.remote

import mostafa.hafezypoor.robiyar.data.model.ModelDetailUser
import mostafa.hafezypoor.robiyar.data.model.ModelLogin
import mostafa.hafezypoor.robiyar.data.model.ModelRegister
import mostafa.hafezypoor.robiyar.data.model.ModelSettingOrders
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("auth/login.php")
    suspend fun login(@Field("username")username: String,@Field("password")password:String): ModelLogin

    @FormUrlEncoded
    @POST("auth/register.php")
    suspend fun register(@Field("name")name: String, @Field("username")username: String,@Field("password")password: String): ModelRegister

    @FormUrlEncoded
    @POST("account/getDetailUser.php")
    suspend fun getDetailUser(@Field("token")token:String): ModelDetailUser

    @FormUrlEncoded
    @POST("messenger/viewPostChannel/getSettingOrder.php")
    suspend fun getSettingViewPostChannel(@Field("token")token:String): ModelSettingOrders
}