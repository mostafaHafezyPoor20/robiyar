package mostafa.hafezypoor.robiyar.ui.messenger.addOrder

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
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

class FPoll(val iEvent: IEvent) : Fragment(R.layout.fpoll){
    private lateinit var viewFlipper: ViewFlipper
    private lateinit var btnV1_Understand : MaterialButton
    private lateinit var btnAddOrder : MaterialButton
    private lateinit var btnV2_back : MaterialButton
    private lateinit var btnV1_backToHome : MaterialButton
    private lateinit var btnGoListOrders : MaterialButton
    private lateinit var postID : TextInputEditText
    private lateinit var titleOrder : TextView
    private lateinit var countOrder : TextInputEditText
    private lateinit var spinnerListItems: Spinner
    private lateinit var token:String
    private  var maximumOrder = 0
    private  var minimumOrder = 0
    private var spinnerItemSelectedPosition = 0
    private val viewModel: MessengerViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        token = context.getSharedPreferences("save",MODE_PRIVATE).getString("token","null") ?: "null"
        viewFlipper = view.findViewById<ViewFlipper>(R.id.viewFlipper)
        btnV1_Understand = view.findViewById<MaterialButton>(R.id.btnV1_Understand)
        btnV1_backToHome = view.findViewById<MaterialButton>(R.id.btnV1_backToHome)
        btnV2_back = view.findViewById<MaterialButton>(R.id.btnV2_back)
        btnAddOrder = view.findViewById<MaterialButton>(R.id.btnAddOrder)
        btnGoListOrders = view.findViewById<MaterialButton>(R.id.btnGoListOrders)
        postID = view.findViewById<TextInputEditText>(R.id.postID)
        titleOrder = view.findViewById<TextView>(R.id.titleOrder)
        countOrder = view.findViewById<TextInputEditText>(R.id.countOrder)
        spinnerListItems = view.findViewById<Spinner>(R.id.spinnerListItems)

        val items = listOf(
            "گزینه شماره 1",
            "گزینه شماره 2",
            "زینه شماره 3",
            "گزینه شماره 4",
            "گزینه شماره 5",
            "گزینه شماره 6",
            "گزینه شماره 7",
            "گزینه شماره 8",
            "گزینه شماره 9",
            "گزینه شماره 10",
            "گزینه شماره 11",
            "گزینه شماره 12",
            "گزینه شماره 13",
            "گزینه شماره 14",
            "گزینه شماره 15"
        )
        val adapter = ArrayAdapter(context,android.R.layout.simple_spinner_item,items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerListItems.adapter = adapter

        spinnerListItems.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
               spinnerItemSelectedPosition = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
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
        viewModel.getSettingPoll(token)
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
        btnAddOrder.setOnClickListener {
            if (countOrder.text.toString().isEmpty()){
                 countOrder.error = "تعداد سفارش نمتواند خالی باشد !"
            }else if (postID.text.toString().isEmpty()){
                postID.error = "آیدی پست نمیتواند خالی باشد"
            }else if (countOrder.text.toString().trim().toInt() > maximumOrder){
                countOrder.error = " حداکثر تعداد سفارش ${maximumOrder} عضو است "
            }else if (countOrder.text.toString().trim().toInt() < minimumOrder){
                countOrder.error = " حداقل تعداد سفارش ${minimumOrder} عضو است "
            } else {
                viewModel.addPoll(token,postID.text.toString().trim(), spinnerListItems.toString(),countOrder.text.toString())
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                        viewModel.state_AddOrder.collect {state->
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