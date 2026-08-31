package mostafa.hafezypoor.robiyar.data.repository

import mostafa.hafezypoor.robiyar.data.model.ModelDetailUser
import mostafa.hafezypoor.robiyar.data.remote.ApiService

class MainActivityRepository(private val apiService: ApiService) {
    suspend fun getDetailUser(token:String): ModelDetailUser{
        return apiService.getDetailUser(token)
    }
}