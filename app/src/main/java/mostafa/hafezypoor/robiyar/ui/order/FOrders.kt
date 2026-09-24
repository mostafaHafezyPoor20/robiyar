package mostafa.hafezypoor.robiyar.ui.order

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import mostafa.hafezypoor.robiyar.R

class FOrders : Fragment(R.layout.forders) {
    private lateinit var token: String
     private lateinit var list: RecyclerView
     private lateinit var cardEmptyOrders: LinearLayout
    private val viewModel: OrdersViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        token = context.getSharedPreferences("save",MODE_PRIVATE).getString("token","null") ?: "null"
       list = view.findViewById<RecyclerView>(R.id.list)
       cardEmptyOrders = view.findViewById<LinearLayout>(R.id.cardEmptyOrders)
       viewLifecycleOwner.lifecycleScope.launch {
           viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
               viewModel.getOrders(token)
               viewModel.orders_state.collect {state ->
                   when(state){
                       OrdersState.Idle -> {}
                       OrdersState.Loading -> {}
                       is OrdersState.Success -> {
                           if (state.list.isEmpty()){
                               cardEmptyOrders.visibility = View.VISIBLE
                           }else{
                               cardEmptyOrders.visibility = View.GONE
                               list.layoutManager = LinearLayoutManager(context)
                               list.adapter = AdapterOrders(context,state.list)
                           }
                       }
                       is OrdersState.Error -> {}
                   }
               }
           }
       }
    }
}