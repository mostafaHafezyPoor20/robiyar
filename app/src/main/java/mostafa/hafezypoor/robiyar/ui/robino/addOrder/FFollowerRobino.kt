package mostafa.hafezypoor.robiyar.ui.robino.addOrder

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import mostafa.hafezypoor.robiyar.MainActivity
import mostafa.hafezypoor.robiyar.R
import mostafa.hafezypoor.robiyar.ui.robino.AddOrderState
import mostafa.hafezypoor.robiyar.ui.robino.RobinoViewModel
import mostafa.hafezypoor.robiyar.ui.robino.SettingOrderState
import kotlin.random.Random

class FFollowerRobino(val iEvent: IEvent) : Fragment(R.layout.ffollower_robino){
    private val viewModel: RobinoViewModel by viewModels()
    private lateinit var viewFlipper: ViewFlipper
    private lateinit var btnV1_Understand : MaterialButton
    private lateinit var btnV2_back : MaterialButton
    private lateinit var btnV1_backToHome : MaterialButton
    private lateinit var btnAddOrder : MaterialButton
    private lateinit var btnGoListOrders : MaterialButton
    private lateinit var countOrder : TextInputEditText
    private lateinit var pageID : TextInputEditText
    private lateinit var titleOrder : TextView

    private lateinit var token:String
    private  var maximumOrder = 0
    private  var minimumOrder = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        token = context.getSharedPreferences("save",MODE_PRIVATE).getString("token","null") ?: "null"
        viewFlipper = view.findViewById<ViewFlipper>(R.id.viewFlipper)
        btnV1_Understand = view.findViewById<MaterialButton>(R.id.btnV1_Understand)
        btnV1_backToHome = view.findViewById<MaterialButton>(R.id.btnV1_backToHome)
        countOrder = view.findViewById<TextInputEditText>(R.id.countOrder)
        pageID = view.findViewById<TextInputEditText>(R.id.pageID)
        btnAddOrder = view.findViewById<MaterialButton>(R.id.btnAddOrder)
        titleOrder = view.findViewById<TextView>(R.id.titleOrder)
        btnV2_back = view.findViewById<MaterialButton>(R.id.btnV2_back)
        btnGoListOrders = view.findViewById<MaterialButton>(R.id.btnGoListOrders)
        viewModel.getSettingFollower(token)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state_SettingOrder.collect {state->
                    when(state){
                        SettingOrderState.Idle -> {}
                        SettingOrderState.Loading -> {}
                        is SettingOrderState.Success -> {
                            if (state.response.maximumOrderUserPrice.toInt() > state.response.maximumOrder.toInt()) {
                                maximumOrder = state.response.maximumOrder.toInt()
                            } else {
                                maximumOrder = state.response.maximumOrderUserPrice.toInt()
                            }
                            minimumOrder = state.response.minimumOrder.toInt()
                            if (state.response.maximumOrderUserPrice.equals("0")) {
                                // if inventory user = 0
                                btnV1_Understand.setOnClickListener {
                                    iEvent.inventoryNotEnough()
                                }
                            } else {
                                titleOrder.text =
                                    " حداقل تعداد سفارش ${minimumOrder} و حداکثر تعداد سفارش بر اساس موجودی شما ${maximumOrder}"
                                btnV1_Understand.setOnClickListener {
                                    viewFlipper.showNext()
                                }
                            }
                        }
                        is SettingOrderState.Error -> {}
                    }
                }
            }
        }
        btnV2_back.setOnClickListener {
            viewFlipper.showPrevious()
        }
        btnV1_backToHome.setOnClickListener {
            val i = Intent(activity, MainActivity::class.java)
            i.putExtra("DESTINATION","ROBINO")
            context.startActivity(i)
            activity.finish()
        }
        btnAddOrder.setOnClickListener {
            if (countOrder.text.toString().isEmpty()){
                countOrder.error = "تعداد سفارش نمتواند خالی باشد !"
            }else if (pageID.text.toString().isEmpty()){
                pageID.error = "آیدی پیج نمیتواند خالی باشد"
            }else if (countOrder.text.toString().trim().toInt() > maximumOrder){
                countOrder.error = " حداکثر تعداد سفارش ${maximumOrder} عضو است "
            }else if (countOrder.text.toString().trim().toInt() < minimumOrder){
                countOrder.error = " حداقل تعداد سفارش ${minimumOrder} عضو است "
            } else {
                viewModel.addFollower(token,pageID.text.toString().trim(),countOrder.text.toString())
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                        viewModel.state_AddOrder.collect {state ->
                            when(state){
                                AddOrderState.Idle -> {}

                                AddOrderState.Loading -> {}

                                is AddOrderState.Success -> {
                                    if (state.response.equals("200")){
                                        viewFlipper.showNext()
                                    }
                                }
                                is AddOrderState.Error -> {
                                }
                            }
                        }
                    }
                }
            }
        }
        btnGoListOrders.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("DESTINATION","ORDERS")
            startActivity(intent)
            activity.finish()
        }
    }
}