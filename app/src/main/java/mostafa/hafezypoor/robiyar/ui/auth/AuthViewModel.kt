package mostafa.hafezypoor.robiyar.ui.auth
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mostafa.hafezypoor.robiyar.data.model.ModelLogin
import mostafa.hafezypoor.robiyar.data.model.ModelRegister
import mostafa.hafezypoor.robiyar.data.remote.RetrofitInit
import mostafa.hafezypoor.robiyar.data.repository.AuthRepository
class AuthViewModel(): ViewModel() {
    private val repository = AuthRepository(RetrofitInit.api)
    private val checkUpdateState = MutableStateFlow<CheckUpdateState>(CheckUpdateState.Idle)
    val state_checkUpdate : StateFlow<CheckUpdateState> = checkUpdateState.asStateFlow()
    private val state= MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = state.asStateFlow()
    private val state_register = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState : StateFlow<RegisterState> = state_register.asStateFlow()
    fun login(username: String, password:String){
        viewModelScope.launch {
            try {
                val response = repository.login(username,password)
                state.value = LoginState.Success(response)
            }catch (e: Exception){
                 state.value= LoginState.Error(e.message?:"unknown error")
            }
        }
    }
    fun checkUpdate(){
        viewModelScope.launch {
            try {
                val response = repository.checkUpdate()
                checkUpdateState.value = CheckUpdateState.Success(response)
            }catch (e: Exception){
                checkUpdateState.value = CheckUpdateState.Error(e.message ?: "Unknown error")

            }
        }
    }
    fun register(name: String, username: String, password:String){
        viewModelScope.launch {
            try {
                val response = repository.register(name,username,password)
                state_register.value = RegisterState.Success(response)
            }catch (e: Exception){
                state_register.value = RegisterState.Error(e.message?:"unknown message")
            }
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val response: ModelLogin): LoginState()
    data class Error(val message:String): LoginState()
}

sealed class CheckUpdateState{
    object Idle : CheckUpdateState()
    object Loading : CheckUpdateState()
    data class Success(val response : String): CheckUpdateState()
    data class Error(val response : String): CheckUpdateState()
}
sealed class RegisterState{
    object Idle : RegisterState()
    object Loading: RegisterState()
    data class Success(val response: ModelRegister) : RegisterState()
    data class Error(val message:String) : RegisterState()

}