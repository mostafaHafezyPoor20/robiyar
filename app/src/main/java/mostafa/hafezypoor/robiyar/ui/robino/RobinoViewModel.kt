package mostafa.hafezypoor.robiyar.ui.robino

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mostafa.hafezypoor.robiyar.data.model.ModelSettingOrders
import mostafa.hafezypoor.robiyar.data.remote.RetrofitInit
import mostafa.hafezypoor.robiyar.data.repository.RobinoRepository

class RobinoViewModel(): ViewModel() {
    private val robinoRepository = RobinoRepository(RetrofitInit.api)

    private val stateSettingOrder = MutableStateFlow<SettingOrderState>(SettingOrderState.Idle)
     val state_SettingOrder : StateFlow<SettingOrderState> = stateSettingOrder.asStateFlow()

    private val stateAddOrder = MutableStateFlow<AddOrderState>(AddOrderState.Idle)
    val state_AddOrder : StateFlow<AddOrderState> = stateAddOrder.asStateFlow()
    //FOLLOWER
    fun getSettingFollower(token:String){
        viewModelScope.launch {
            try {
                val response = robinoRepository.getSettingFollower(token)
                stateSettingOrder.value = SettingOrderState.Success(response)
            }catch (e: Exception){
                stateSettingOrder.value = SettingOrderState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun addFollower(token: String,pageID: String, countOrder:String){
        viewModelScope.launch {
            try {
                val response = robinoRepository.addFollower(token,pageID,countOrder)
                stateAddOrder.value = AddOrderState.Success(response)
            }catch (e: Exception){
                stateAddOrder.value = AddOrderState.Error(e.message ?: "Unknown error")
            }
        }
    }
    //LIKE
    fun getSettingLike(token:String){
        viewModelScope.launch {
            try {
                val response = robinoRepository.getSettingLike(token)
                stateSettingOrder.value = SettingOrderState.Success(response)
            }catch (e: Exception){
                stateSettingOrder.value = SettingOrderState.Error(e.message ?: "Unknown error")
            }
        }
    }
    fun addLike(token: String, pageID: String,countOrder: String){
        viewModelScope.launch {
            try {
                val response = robinoRepository.addLike(token,pageID,countOrder)
                stateAddOrder.value = AddOrderState.Success(response)
            }catch (e: Exception){
                stateAddOrder.value = AddOrderState.Error(e.message ?: "Unknown error")
            }
        }
    }

    //VIEW POST ROBINO
    fun getSettingViewPostRobino(token:String){
        viewModelScope.launch {
            try {
                val response = robinoRepository.getSettingViewPostRobino(token)
                stateSettingOrder.value = SettingOrderState.Success(response)
            }catch (e: Exception){
                stateSettingOrder.value = SettingOrderState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun addViewPostRobino(token: String,postID: String,countOrder: String){
        viewModelScope.launch {
            try {
                val response = robinoRepository.addViewPostRobino(token,postID,countOrder)
                stateAddOrder.value = AddOrderState.Success(response)
            }catch (e: Exception){
                stateAddOrder.value = AddOrderState.Error(e.message ?: "Unknown error")
            }
        }
    }
}


sealed class SettingOrderState{
    object Idle: SettingOrderState()
    object Loading: SettingOrderState()
    data class Success(val response: ModelSettingOrders): SettingOrderState()
    data class Error(val message:String): SettingOrderState()
}

sealed class AddOrderState{
    object Idle: AddOrderState()
    object Loading: AddOrderState()
    data class Success(val response:String): AddOrderState()
    data class Error(val message:String): AddOrderState()
}