package mostafa.hafezypoor.robiyar.ui.messenger.addOrder

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
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
import kotlinx.coroutines.launch
import mostafa.hafezypoor.robiyar.MainActivity
import mostafa.hafezypoor.robiyar.R
import mostafa.hafezypoor.robiyar.data.model.ModelSettingOrders
import mostafa.hafezypoor.robiyar.ui.messenger.MessengerViewModel
import mostafa.hafezypoor.robiyar.ui.messenger.SettingOrderState
import kotlin.getValue
import kotlin.random.Random

class FViewPostChannel() : Fragment(R.layout.fview_post_channel){
    private lateinit var viewFlipper: ViewFlipper
    private lateinit var btnV1_Understand : MaterialButton
    private lateinit var btnV2_back : MaterialButton
    private lateinit var btnV1_backToHome : MaterialButton
    private lateinit var titleOrder: TextView
    private val viewModel: MessengerViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewFlipper = view.findViewById<ViewFlipper>(R.id.viewFlipper)
        btnV1_Understand = view.findViewById<MaterialButton>(R.id.btnV1_Understand)
        btnV1_backToHome = view.findViewById<MaterialButton>(R.id.btnV1_backToHome)
        btnV2_back = view.findViewById<MaterialButton>(R.id.btnV2_back)
        titleOrder = view.findViewById<TextView>(R.id.titleOrder)

        btnV2_back.setOnClickListener {
            viewFlipper.showPrevious()
        }
        btnV1_backToHome.setOnClickListener {
            context.startActivity(Intent(activity, MainActivity::class.java))
            activity.finish()
        }
        viewModel.getSettingViewPostChannel(context.getSharedPreferences("save",MODE_PRIVATE).getString("token","null") ?: "null")
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
    }
}