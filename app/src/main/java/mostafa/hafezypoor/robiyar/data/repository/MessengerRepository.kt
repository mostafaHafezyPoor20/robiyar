package mostafa.hafezypoor.robiyar.data.repository

import mostafa.hafezypoor.robiyar.data.model.ModelSettingOrders
import mostafa.hafezypoor.robiyar.data.remote.ApiService

class MessengerRepository(private val apiService: ApiService) {
    suspend fun getSettingViewPostChannel(token:String): ModelSettingOrders{
        return apiService.getSettingViewPostChannel(token)
    }
    suspend fun addViewPostChannel(token: String,channelID: String, countOrder:String):String{
        return apiService.addViewPostChannel(token,channelID,countOrder)
    }

    suspend fun getSettingMemberShipChannel(token:String): ModelSettingOrders{
        return apiService.getSettingMemberShipChannel(token)
    }

    suspend fun addMemberShipChannel(token: String,channelID: String,countOrder: String): String{
        return apiService.addMemberShipChannel(token,channelID,countOrder)
    }

    suspend fun getSettingMemberShipGroup(token:String): ModelSettingOrders{
        return apiService.getSettingMemberShipGroup(token)
    }

    suspend fun addMemberShipGroup(token: String,groupID : String , countOrder: String):String{
        return apiService.addMemberShipGroup(token,groupID,countOrder)
    }

    suspend fun getSettingPoll(token:String): ModelSettingOrders{
        return apiService.getSettingPoll(token)
    }

    suspend fun addPoll(token: String, postID: String, optionPosition:String,countOrder: String):String{
        return apiService.addPoll(token,postID,optionPosition,countOrder)
    }

}