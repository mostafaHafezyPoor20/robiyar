package mostafa.hafezypoor.robiyar.data.repository

import mostafa.hafezypoor.robiyar.data.model.ModelDetailAccount
import mostafa.hafezypoor.robiyar.data.remote.ApiService

class MainActivityRepository(private val apiService: ApiService) {
    suspend fun getDetailUser(token:String): ModelDetailAccount{
        return apiService.getDetailUser(token)
    }
}