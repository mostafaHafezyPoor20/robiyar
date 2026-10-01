package mostafa.hafezypoor.robiyar.data.repository

import mostafa.hafezypoor.robiyar.data.remote.ApiService

class InventoryRepository(private val apiService: ApiService) {
    suspend fun increaseInventory(token: String, result_is_success: String,result_message: String,result_response: String,info_sku: String,info_item_type: String,info_token: String,info_purchase_time: String,info_developer_payload: String,info_order_id: String,info_original_json: String,info_package_name: String,info_purchase_state: String,info_signature:String): String{
        return apiService.increaseInventory(token,result_is_success,result_message,result_response,info_sku,info_item_type,info_token,info_purchase_time,info_developer_payload,info_order_id,info_original_json,info_package_name,info_purchase_state,info_signature)

    }
}