package mostafa.hafezypoor.robiyar.ui.messenger.addOrder

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
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
import mostafa.hafezypoor.robiyar.data.model.ModelSettingOrders
import mostafa.hafezypoor.robiyar.ui.messenger.AddOrderState
import mostafa.hafezypoor.robiyar.ui.messenger.MessengerViewModel
import mostafa.hafezypoor.robiyar.ui.messenger.SettingOrderState
import kotlin.getValue
import kotlin.random.Random

class FViewPostChannel() : Fragment(R.layout.fview_post_channel){
    private lateinit var viewFlipper: ViewFlipper
    private lateinit var btnV1_Understand : MaterialButton
    private lateinit var btnV2_back : MaterialButton
    private lateinit var btnV1_backToHome : MaterialButton
    private lateinit var btnAddOrder : MaterialButton
    private lateinit var countOrder : TextInputEditText
    private lateinit var channelID : TextInputEditText
    private lateinit var titleOrder: TextView
    private lateinit var token:String
    private val viewModel: MessengerViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewFlipper = view.findViewById<ViewFlipper>(R.id.viewFlipper)
        btnV1_Understand = view.findViewById<MaterialButton>(R.id.btnV1_Understand)
        btnV1_backToHome = view.findViewById<MaterialButton>(R.id.btnV1_backToHome)
        btnV2_back = view.findViewById<MaterialButton>(R.id.btnV2_back)
        btnAddOrder = view.findViewById<MaterialButton>(R.id.btnAddOrder)
        titleOrder = view.findViewById<TextView>(R.id.titleOrder)
        countOrder = view.findViewById<TextInputEditText>(R.id.countOrder)
        channelID = view.findViewById<TextInputEditText>(R.id.channelID)
        token = context.getSharedPreferences("save",MODE_PRIVATE).getString("token","null") ?: "null"
        btnV2_back.setOnClickListener {
            viewFlipper.showPrevious()
        }
        btnV1_backToHome.setOnClickListener {
            context.startActivity(Intent(activity, MainActivity::class.java))
            activity.finish()
        }
        viewModel.getSettingViewPostChannel(token)
        viewLifecycleOwner.lifecycleScope.launch {
          viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
              viewModel.state_SettingOrde.collect {state->
                  when(state){
                      SettingOrderState.Idle -> {}
                      SettingOrderState.Loading -> {}
                      is SettingOrderState.Success -> {
                          if(state.response.maximumOrderUserPrice.equals("0")){
                              // if inventory user = 0
                                btnV1_Understand.setOnClickListener {
                                    Toast.makeText(context,"inventory not enugh", Toast.LENGTH_LONG).show()
                                }
                          }else{
                              titleOrder.text = " حداقل تعداد سفارش ${state.response.minimumOrder} و حداکثر تعداد سفارش  ${state.response.maximumOrderUserPrice}"
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
        btnAddOrder.setOnClickListener {
            if (channelID.text.toString().isEmpty()){
                countOrder.error = "تعداد سفارش نمتواند 0 باشد !"
            }else if (countOrder.text.toString().isEmpty()){
                channelID.error = "آیدی کانال نمیتواند خالی باشد"
            }else {
                viewModel.addViewPostChannel(token,channelID.text.toString().trim(),countOrder.text.toString().trim())
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.state_AddOrder.collect {state ->
                        when(state){
                            AddOrderState.Idle ->{}
                            AddOrderState.Loading -> {}
                            is AddOrderState.Success -> {
                                if (state.response.equals("200")){
                                    Toast.makeText(context,"order is added ", Toast.LENGTH_LONG).show()
                                }else if (state.response.equals("1001")||state.response.equals("1002")){
                                    Toast.makeText(context,"inventory not enough", Toast.LENGTH_LONG).show()
                                }
                            }
                            is AddOrderState.Error -> { }
                        }
                    }
                }
            }
        }

    }
}