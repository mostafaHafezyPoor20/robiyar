package mostafa.hafezypoor.robiyar.ui.messenger
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mostafa.hafezypoor.robiyar.data.model.ModelSettingOrders
import mostafa.hafezypoor.robiyar.data.remote.RetrofitInit
import mostafa.hafezypoor.robiyar.data.repository.MessengerRepository
class MessengerViewModel(): ViewModel() {
    private val messengerRepository= MessengerRepository(RetrofitInit.api)

    private val stateSettingOrder= MutableStateFlow<SettingOrderState>(SettingOrderState.Idle)
    val state_SettingOrde : StateFlow<SettingOrderState> =stateSettingOrder.asStateFlow()

    private val stateAddOrder=MutableStateFlow<AddOrderState>(AddOrderState.Idle)
    val state_AddOrder: StateFlow<AddOrderState> = stateAddOrder.asStateFlow()
    //VIEW POST CHANNEL
    fun getSettingViewPostChannel(token: String){
        viewModelScope.launch {
            try {
                val response = messengerRepository.getSettingViewPostChannel(token)
                stateSettingOrder.value = SettingOrderState.Success(response)
            }catch (e: Exception){
                stateSettingOrder.value = SettingOrderState.Error(e.message ?: "Unknown Error")
            }
        }
    }
    fun addViewPostChannel(token: String,channelID: String, countOrder:String){
        viewModelScope.launch {
            try {
                val response = messengerRepository.addViewPostChannel(token,channelID,countOrder)
                stateAddOrder.value = AddOrderState.Success(response)
            }catch (e: Exception){
                stateAddOrder.value = AddOrderState.Error(e.message ?: "Unknown Error")
            }
        }
    }
    //MEMBER SHIP CHANNEL
    fun getSettingMemberShipChannel(token:String){
        viewModelScope.launch {
            try {
             val response =   messengerRepository.getSettingMemberShipChannel(token);
                stateSettingOrder.value = SettingOrderState.Success(response)
            }catch (e: Exception){
                stateSettingOrder.value = SettingOrderState.Error(e.message ?: "Unknown error")
            }
        }
    }
}


sealed class SettingOrderState{
    object Idle: SettingOrderState()
    object Loading: SettingOrderState()
    data class  Success(val response: ModelSettingOrders): SettingOrderState()
    data class Error(val message:String): SettingOrderState()

}
sealed class AddOrderState{
    object Idle: AddOrderState()
    object Loading: AddOrderState()
    data class Success(val response: String): AddOrderState()
    data class Error(val response:String): AddOrderState()
}