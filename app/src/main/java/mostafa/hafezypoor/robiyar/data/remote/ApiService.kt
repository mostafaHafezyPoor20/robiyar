package mostafa.hafezypoor.robiyar.data.remote

import mostafa.hafezypoor.robiyar.data.model.ModelDetailAccount
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
    @POST("account/getDetailAccount.php")
    suspend fun getDetailUser(@Field("token")token:String): ModelDetailAccount

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

    @FormUrlEncoded
    @POST("account/getDetailAccount.php")
    suspend fun getDetailAccount(@Field("token")token: String) : ModelDetailAccount

    @FormUrlEncoded
    @POST("account/updateDetailUser.php")
    suspend fun updateDetailUser(@Field("token")token: String, @Field("password")password: String,@Field("name")name: String): String

    @FormUrlEncoded
    @POST("inventory/increaseInventory.php")
    suspend fun increaseInventory(@Field("token")token: String,@Field("result_is_success")result_is_success: String,
                                  @Field("result_message")result_message: String,@Field("result_response")result_response: String, @Field("info_sku")info_sku: String,
                                  @Field("info_item_type")info_item_type: String, @Field("info_token")info_token: String,@Field("info_purchase_time")info_purchase_time: String,
                                  @Field("info_developer_payload")info_developer_payload: String,@Field("info_order_id")info_order_id: String, @Field("info_original_json")info_original_json: String,
                                  @Field("info_package_name")info_package_name: String, @Field("info_purchase_state")info_purchase_state: String, @Field("info_signature")info_signature:String) : String
}