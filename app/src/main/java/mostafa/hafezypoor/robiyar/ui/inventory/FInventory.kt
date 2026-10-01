package mostafa.hafezypoor.robiyar.ui.inventory

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.button.MaterialButton
import ir.myket.billingclient.IabHelper
import ir.myket.billingclient.util.IabResult
import ir.myket.billingclient.util.Purchase
import kotlinx.coroutines.launch
import mostafa.hafezypoor.robiyar.BuildConfig
import mostafa.hafezypoor.robiyar.R
import android.content.Context.MODE_PRIVATE
import mostafa.hafezypoor.robiyar.ui.dialog.LoadingDialog


class FInventory : Fragment(R.layout.finventory) , View.OnClickListener {
    private lateinit var token: String
    private lateinit var loadingDialog: LoadingDialog
    private val viewModel: InventoryViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = LoadingDialog(context,"درحال انجام فرایند پرداخت")
        token = context.getSharedPreferences("save",MODE_PRIVATE).getString("token","null") ?: "null"
        view.findViewById<MaterialButton>(R.id.five_inventory).setOnClickListener(this)
        view.findViewById<MaterialButton>(R.id.ten_inventory).setOnClickListener(this)
        view.findViewById<MaterialButton>(R.id.one_hundered_inventory).setOnClickListener(this)
        view.findViewById<MaterialButton>(R.id.tow_hundered_inventory).setOnClickListener(this)
        view.findViewById<MaterialButton>(R.id.five_hundered_inventory).setOnClickListener(this)

    }
    fun inventory(sku:String){
        val mHepper = IabHelper(context, BuildConfig.IAB_PUBLIC_KEY)
        mHepper.enableDebugLogging(true)
        mHepper.startSetup {result ->
            loadingDialog.setTitleTextView("درحال اتصال به درگاه مایکت")
            if (result.isSuccess){
                mHepper.launchPurchaseFlow(activity,sku,object : IabHelper.OnIabPurchaseFinishedListener{
                    override fun onIabPurchaseFinished(result: IabResult?, info: Purchase?) {
                        loadingDialog.setTitleTextView("منتظر پاسخ از سمت مایکت")
                       // Log.i("TAG12345", "onIabPurchaseFinished:  sku = "+info!!.sku+" * token = "+info.token+" * result message"+result!!.message+ " * result response "+result.response)
                        if (result != null)
                        if (result.isSuccess){
                            loadingDialog.setTitleTextView("تایید پرداخت")
                            viewLifecycleOwner.lifecycleScope.launch {
                                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                                    if (result!=null && info != null){
                                        viewModel.increaseInventory(token,result.isSuccess.toString(),result.message,result.response.toString(),info.sku,info.itemType,info.token,
                                            info.purchaseTime.toString(),info.developerPayload,info.orderId,info.originalJson,
                                            info.packageName.toString(),info.purchaseState.toString(),info.signature)
                                        viewModel.state_AddOrder.collect {state ->
                                            when(state){
                                                AddOrderState.Idle -> {}
                                                AddOrderState.Loading -> {}
                                                is AddOrderState.Success -> {
                                                    loadingDialog.setTitleTextView(state.response)
                                                    if (state.response.equals("200")){
                                                        loadingDialog.dismiss()
                                                    }
                                                }
                                                is AddOrderState.Error -> {
                                                    loadingDialog.setTitleTextView(state.response)
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                            mHepper.consumeAsync(info,object : IabHelper.OnConsumeFinishedListener{
                                override fun onConsumeFinished(
                                    purchase: Purchase?,
                                    result: IabResult?
                                ) {
                              //      Log.i("TAG12345", "onConsumeFinished: and order in again is ready")

                                }
                            })
                        }else if (result.isFailure){
                            loadingDialog.dismiss()
                        }
                    }
                })
            }

        }
    }
    override fun onClick(p0: View?) {
        loadingDialog.show()
     when(p0?.id){
         R.id.five_inventory -> inventory("five_inventory")
         R.id.ten_inventory -> inventory("ten_inventory")
         R.id.one_hundered_inventory -> inventory("one_hundered_inventory")
         R.id.tow_hundered_inventory -> inventory("tow_hundered_inventory")
         R.id.five_hundered_inventory -> inventory("five_hundered_inventory")
     }
    }



}

