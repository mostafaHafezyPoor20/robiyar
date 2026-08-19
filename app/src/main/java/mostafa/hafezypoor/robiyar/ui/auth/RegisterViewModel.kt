package mostafa.hafezypoor.robiyar.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mostafa.hafezypoor.robiyar.data.model.ModelRegister
import mostafa.hafezypoor.robiyar.data.remote.ApiService
import mostafa.hafezypoor.robiyar.data.remote.RetrofitInit
import mostafa.hafezypoor.robiyar.data.repository.AuthRepository

class RegisterViewModel() :  ViewModel() {
    private val repository=AuthRepository(RetrofitInit.api)
    private val state = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState : StateFlow<RegisterState> = state.asStateFlow()
    fun register(name: String, username: String, password:String){
        viewModelScope.launch {
            try {
              val response = repository.register(name,username,password)
                state.value = RegisterState.Success(response)
            }catch (e: Exception){
                state.value = RegisterState.Error(e.message?:"unknown message")
            }
        }
    }
}
sealed class RegisterState{
    object Idle : RegisterState()
    object Loading: RegisterState()
    data class Success(val response: ModelRegister) : RegisterState()
    data class Error(val message:String) : RegisterState()

}