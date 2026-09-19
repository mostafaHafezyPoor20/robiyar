package mostafa.hafezypoor.robiyar.data.repository

import mostafa.hafezypoor.robiyar.data.model.ModelSettingOrders
import mostafa.hafezypoor.robiyar.data.remote.ApiService

class RobinoRepository(private val apiService: ApiService) {
    suspend fun getSettingFollower(token:String): ModelSettingOrders{
        return apiService.getSettingFollower(token)
    }

    suspend fun addFollower(token: String,pageID : String,countOrder:String): String{
        return apiService.addFollower(token,pageID,countOrder)
    }
}