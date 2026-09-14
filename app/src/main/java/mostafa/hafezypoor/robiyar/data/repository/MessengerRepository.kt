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
}