package mostafa.hafezypoor.robiyar.ui.auth
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mostafa.hafezypoor.robiyar.data.model.ModelLogin
import mostafa.hafezypoor.robiyar.data.remote.RetrofitInit
import mostafa.hafezypoor.robiyar.data.repository.AuthRepository
class LoginViewModel(): ViewModel() {
    private val repository = AuthRepository(RetrofitInit.api)
    private val state= MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = state.asStateFlow()
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
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val response: ModelLogin): LoginState()
    data class Error(val message:String): LoginState()
}