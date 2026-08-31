package mostafa.hafezypoor.robiyar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mostafa.hafezypoor.robiyar.data.model.ModelDetailUser
import mostafa.hafezypoor.robiyar.data.remote.RetrofitInit
import mostafa.hafezypoor.robiyar.data.repository.AuthRepository
import mostafa.hafezypoor.robiyar.data.repository.MainActivityRepository

class MainActivityViewModel() : ViewModel() {
    private val repository = MainActivityRepository(RetrofitInit.api)
    private val state = MutableStateFlow<MainActivityState>(MainActivityState.Idle)
    val mainActivityState : StateFlow<MainActivityState> = state.asStateFlow()
    fun getDetailUser(token:String){
        viewModelScope.launch {
            try{
                val response = repository.getDetailUser(token)
                state.value = MainActivityState.Success(response)
            }catch (e: Exception){
                state.value = MainActivityState.Error(e.message ?: "Unknown error")
            }
        }
    }

}
sealed class MainActivityState{
    object Idle : MainActivityState()
    object Loading : MainActivityState()

    data class Success(val response : ModelDetailUser) : MainActivityState()
    data class Error(val message : String) : MainActivityState()

}