package mostafa.hafezypoor.robiyar.data.repository

import mostafa.hafezypoor.robiyar.data.model.ModelOrders
import mostafa.hafezypoor.robiyar.data.remote.ApiService

class OrderRepository(private val apiService: ApiService) {
    suspend fun getOrders(token: String):List<ModelOrders>{
        return apiService.getOrders(token)
    }
}