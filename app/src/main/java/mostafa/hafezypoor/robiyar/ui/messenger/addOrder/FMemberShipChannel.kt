package mostafa.hafezypoor.robiyar.ui.messenger.addOrder

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
import mostafa.hafezypoor.robiyar.ui.messenger.AddOrderState
import mostafa.hafezypoor.robiyar.ui.messenger.MessengerViewModel
import mostafa.hafezypoor.robiyar.ui.messenger.SettingOrderState

class FMemberShipChannel(val iEvent: IEvent) : Fragment(R.layout.fmember_ship_channel){
    private lateinit var viewFlipper: ViewFlipper
    private lateinit var btnV1_Understand : MaterialButton
    private lateinit var btnV2_back : MaterialButton
    private lateinit var btnV1_backToHome : MaterialButton
    private lateinit var btnGoListOreders : MaterialButton
    private lateinit var btnAddOrder : MaterialButton
    private lateinit var channelID : TextInputEditText
    private lateinit var countOrder : TextInputEditText
    private lateinit var titleOrder : TextView
    private lateinit var token:String
    private val viewModel: MessengerViewModel by viewModels()
    private  var maximumOrder = 0
    private  var minimumOrder = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewFlipper = view.findViewById<ViewFlipper>(R.id.viewFlipper)
        btnV1_Understand = view.findViewById<MaterialButton>(R.id.btnV1_Understand)
        btnV1_backToHome = view.findViewById<MaterialButton>(R.id.btnV1_backToHome)
        btnV2_back = view.findViewById<MaterialButton>(R.id.btnV2_back)
        btnAddOrder = view.findViewById<MaterialButton>(R.id.btnAddOrder)
        btnGoListOreders = view.findViewById<MaterialButton>(R.id.btnGoListOreders)
        titleOrder = view.findViewById<TextView>(R.id.titleOrder)
        countOrder = view.findViewById<TextInputEditText>(R.id.countOrder)
        channelID = view.findViewById<TextInputEditText>(R.id.channelID)

        token = context.getSharedPreferences("save",MODE_PRIVATE).getString("token","null") ?: "null"
        btnV1_Understand.setOnClickListener {
            viewFlipper.showNext()
        }
        btnV2_back.setOnClickListener {
            viewFlipper.showPrevious()
        }
        btnV1_backToHome.setOnClickListener {
            context.startActivity(Intent(activity, MainActivity::class.java))
            activity.finish()
        }
        btnAddOrder.setOnClickListener {
            if (channelID.text.toString().isEmpty()){
                channelID.error = "تعداد سفارش نمتواند خالی باشد !"
            }else if (countOrder.text.toString().isEmpty()){
                countOrder.error = "آیدی کانال نمیتواند خالی باشد"
            }else if (countOrder.text.toString().trim().toInt() > maximumOrder){
                countOrder.error = " حداکثر تعداد سفارش ${maximumOrder} عضو است "
            }else if (countOrder.text.toString().trim().toInt() < minimumOrder){
                countOrder.error = " حداقل تعداد سفارش ${minimumOrder} عضو است "
            } else {
               viewModel.addMemberShipChannel(token,channelID.text.toString().trim(),countOrder.text.toString().trim())
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                        viewModel.state_AddOrder.collect {state ->
                            when(state){
                                AddOrderState.Idle -> {}
                                AddOrderState.Loading -> {}
                                is AddOrderState.Success -> {
                                    Log.i("TAG12345", "onViewCreated: "+state.response.toString())
                                        if (state.response.equals("200")){
                                            viewFlipper.showNext()
                                        }
                                }
                                is AddOrderState.Error -> {
                                    Log.i("TAG12345", "Error: "+state.response)
                                }
                            }
                        }
                    }
                }
            }
        }

       viewModel.getSettingMemberShipChannel(token)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state_SettingOrder.collect { state->
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
        btnGoListOreders.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("DESTINATION","ORDERS")
            startActivity(intent)
            activity.finish()
        }
    }
}