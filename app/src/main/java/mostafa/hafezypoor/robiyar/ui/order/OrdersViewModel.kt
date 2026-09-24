package mostafa.hafezypoor.robiyar.ui.order

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import mostafa.hafezypoor.robiyar.data.model.ModelOrders
import mostafa.hafezypoor.robiyar.data.remote.RetrofitInit
import mostafa.hafezypoor.robiyar.data.repository.OrderRepository

class OrdersViewModel(): ViewModel() {
    private val orderRepository =  OrderRepository(RetrofitInit.api)

    private val ordersState = MutableStateFlow<OrdersState>(OrdersState.Idle)
    val orders_state : StateFlow<OrdersState> = ordersState.asStateFlow()

    suspend fun getOrders(token: String){
        try {
            val response = orderRepository.getOrders(token)
            ordersState.value = OrdersState.Success(response)
        }catch (e: Exception){
            ordersState.value = OrdersState.Error(e.message ?: "Unknown error")
        }
    }
}



sealed class OrdersState{
    object Idle: OrdersState()
    object Loading: OrdersState()
    data class Success(val list:List<ModelOrders>): OrdersState()
    data class Error(val response: String): OrdersState()
}