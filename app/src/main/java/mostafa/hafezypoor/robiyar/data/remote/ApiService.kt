package mostafa.hafezypoor.robiyar.data.remote

import mostafa.hafezypoor.robiyar.data.model.ModelDetailUser
import mostafa.hafezypoor.robiyar.data.model.ModelLogin
import mostafa.hafezypoor.robiyar.data.model.ModelOrders
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

    @FormUrlEncoded
    @POST("messenger/viewPostChannel/addViewPostChannel.php")
    suspend fun addViewPostChannel(@Field("token")token: String, @Field("channelID")channelID: String, @Field("countOrder")countOrder:String): String

    @FormUrlEncoded
    @POST("messenger/channelMemberShip/getSettingOrder.php")
    suspend fun getSettingMemberShipChannel(@Field("token")token: String): ModelSettingOrders

    @FormUrlEncoded
    @POST("messenger/channelMemberShip/addMemberShipChannel.php")
    suspend fun addMemberShipChannel(@Field("token")token: String,@Field("channelID")channelID: String,@Field("countOrder")countOrder: String): String

    @FormUrlEncoded
    @POST("messenger/groupMemberShip/getSettingOrder.php")
    suspend fun getSettingMemberShipGroup(@Field("token")token: String): ModelSettingOrders

    @FormUrlEncoded
    @POST("messenger/groupMemberShip/addMemberShipGroup.php")
    suspend fun addMemberShipGroup(@Field("token")token: String,@Field("groupID")groupID: String, @Field("countOrder")countOrder: String):String

    @FormUrlEncoded
    @POST("messenger/poll/getSettingOrder.php")
    suspend fun getSettingPoll(@Field("token")token: String): ModelSettingOrders

    @FormUrlEncoded
    @POST("messenger/poll/addPoll.php")
    suspend fun addPoll(@Field("token")token: String, @Field("postID")postID: String, @Field("optionPosition")optionPosition: String,@Field("countOrder")countOrder:String): String

    @FormUrlEncoded
    @POST("robino/follower/getSettingOrder.php")
    suspend fun getSettingFollower(@Field("token")token:String): ModelSettingOrders

    @FormUrlEncoded
    @POST("robino/follower/addFollower.php")
    suspend fun addFollower(@Field("token")token: String, @Field("pageID")pageID: String,@Field("countOrder")countOrder: String): String

    @FormUrlEncoded
    @POST("robino/like/getSettingOrder.php")
    suspend fun getSettingLike(@Field("token")token: String): ModelSettingOrders

    @FormUrlEncoded
    @POST("robino/like/addLike.php")
    suspend fun addLike(@Field("token")token: String,@Field("postID")postID: String,@Field("countOrder")countOrder: String):String

    @FormUrlEncoded
    @POST("robino/view/getSettingOrder.php")
    suspend fun getSettingViewPostRobino(@Field("token")token: String): ModelSettingOrders

    @FormUrlEncoded
    @POST("robino/view/addViewPostRobino.php")
    suspend fun addViewPostRobino(@Field("token")token: String,@Field("postID")postID: String ,@Field("countOrder")countOrder: String): String

    @FormUrlEncoded
    @POST("orders/orders.php")
    suspend fun getOrders(@Field("token")token: String):List<ModelOrders>
}