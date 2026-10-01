package mostafa.hafezypoor.robiyar.ui.inventory

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import mostafa.hafezypoor.robiyar.data.remote.RetrofitInit
import mostafa.hafezypoor.robiyar.data.repository.InventoryRepository
import java.lang.Exception

class InventoryViewModel(): ViewModel() {
    private val inventoryRepository = InventoryRepository(RetrofitInit.api)

    private val stateAddOrder = MutableStateFlow<AddOrderState>(AddOrderState.Idle)
     val state_AddOrder :  StateFlow<AddOrderState> = stateAddOrder.asStateFlow()

   suspend fun increaseInventory(token: String, result_is_success: String,result_message: String,result_response: String,info_sku: String,info_item_type: String,info_token: String,info_purchase_time: String,info_developer_payload: String,info_order_id: String,info_original_json: String,info_package_name: String,info_purchase_state: String,info_signature:String){
       try {
           val response = inventoryRepository.increaseInventory(token,result_is_success,result_message,result_response,info_sku,info_item_type,info_token,info_purchase_time,info_developer_payload,info_order_id,info_original_json,info_package_name,info_purchase_state,info_signature)
           stateAddOrder.value = AddOrderState.Success(response)
       }catch (e: Exception){
           stateAddOrder.value = AddOrderState.Error(e.message ?: "Unknown error")
       }
    }
}
sealed class AddOrderState{
    object Idle : AddOrderState()
    object Loading : AddOrderState()
    data class Success(val response:String) : AddOrderState()
    data class Error(val response:String) : AddOrderState()
}