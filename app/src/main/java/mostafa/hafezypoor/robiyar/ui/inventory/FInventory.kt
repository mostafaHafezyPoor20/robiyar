package mostafa.hafezypoor.robiyar.ui.inventory

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.button.MaterialButton
import ir.myket.billingclient.IabHelper
import ir.myket.billingclient.util.IabResult
import ir.myket.billingclient.util.Inventory
import ir.myket.billingclient.util.Purchase
import mostafa.hafezypoor.robiyar.BuildConfig
import mostafa.hafezypoor.robiyar.R

class FInventory : Fragment(R.layout.finventory) , View.OnClickListener {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            if (result.isSuccess){
                mHepper.launchPurchaseFlow(activity,sku,object : IabHelper.OnIabPurchaseFinishedListener{
                    override fun onIabPurchaseFinished(result: IabResult?, info: Purchase?) {

                    }
                })
            }

        }
    }
    override fun onClick(p0: View?) {
     when(p0?.id){
         R.id.five_inventory -> inventory("five_inventory")
         R.id.ten_inventory -> inventory("ten_inventory")
         R.id.one_hundered_inventory -> inventory("one_hundered_inventory")
         R.id.tow_hundered_inventory -> inventory("tow_hundered_inventory")
         R.id.five_hundered_inventory -> inventory("five_hundered_inventory")
     }
    }



}

