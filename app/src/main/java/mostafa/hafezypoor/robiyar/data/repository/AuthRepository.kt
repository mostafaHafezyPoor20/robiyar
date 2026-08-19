package mostafa.hafezypoor.robiyar.data.repository

import mostafa.hafezypoor.robiyar.data.model.ModelLogin
import mostafa.hafezypoor.robiyar.data.model.ModelRegister
import mostafa.hafezypoor.robiyar.data.remote.ApiService

class AuthRepository(private val apiService: ApiService) {
     suspend fun login(username: String, password:String): ModelLogin{
      return apiService.login(username,password)
    }
    suspend fun register(name: String, username: String,password: String): ModelRegister{
        return apiService.register(name,username,password)
    }
}