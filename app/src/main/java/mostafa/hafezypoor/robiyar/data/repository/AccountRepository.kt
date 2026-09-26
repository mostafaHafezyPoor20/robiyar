package mostafa.hafezypoor.robiyar.data.repository

import mostafa.hafezypoor.robiyar.data.model.ModelDetailAccount
import mostafa.hafezypoor.robiyar.data.remote.ApiService

class AccountRepository (private val apiService: ApiService) {
    suspend fun getDetailAccount(token:String): ModelDetailAccount{
        return apiService.getDetailAccount(token)
    }

    suspend fun updateDetailUser(token: String,password: String, name: String): String{
        return apiService.updateDetailUser(token,password,name)
    }
}