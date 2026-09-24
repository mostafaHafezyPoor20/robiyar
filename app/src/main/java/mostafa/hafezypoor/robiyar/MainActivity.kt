package mostafa.hafezypoor.robiyar

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import mostafa.hafezypoor.robiyar.ui.account.FAccount
import mostafa.hafezypoor.robiyar.ui.messenger.FMessenger
import mostafa.hafezypoor.robiyar.ui.order.FOrders
import mostafa.hafezypoor.robiyar.ui.robino.FRobino
import mostafa.hafezypoor.robiyar.utils.AnimationCard
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem
import np.com.susanthapa.curved_bottom_navigation.CurvedBottomNavigationView

class MainActivity : AppCompatActivity() {
  private lateinit var bottomNavigation : CurvedBottomNavigationView
  private lateinit var card: FrameLayout
  private lateinit var actionBar: RelativeLayout
  private lateinit var name : TextView
  private lateinit var inventory : TextView
  private val viewModel: MainActivityViewModel by  viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation = findViewById<CurvedBottomNavigationView>(R.id.bottomNavigation)
        card = findViewById<FrameLayout>(R.id.frameLayout_main_activity)
        actionBar = findViewById<RelativeLayout>(R.id.actionBar)
        name = findViewById<TextView>(R.id.name)
        inventory = findViewById<TextView>(R.id.inventory)
        viewModel.getDetailUser(getSharedPreferences("save",MODE_PRIVATE).getString("token","null") ?: "null")
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
              viewModel.mainActivityState.collect {state->
                  when(state){
                      MainActivityState.Idle -> {}
                      MainActivityState.Loading -> {}
                      is MainActivityState.Success -> {
                     //  state.response  = this object data exists
                          name.text = state.response.name
                          inventory.text = state.response.inventory + " تومان "
                      }
                      is MainActivityState.Error -> {

                      }
                  }
              }
            }
        }


        val menuItems = arrayOf(
            CbnMenuItem(R.drawable.drafts_24px,R.drawable.avd_messenger,R.id.messenger,"پیام رسان"),
            CbnMenuItem(R.drawable.alternate_email_24px,R.drawable.avd_robino,R.id.robino,"روبینو"),
            CbnMenuItem(R.drawable.shopping_cart_24px,R.drawable.avd_shop,R.id.orders,"سفارش ها"),
            CbnMenuItem(R.drawable.account_circle_24px,R.drawable.avd_account,R.id.account,"حساب کاربری")
        )
        bottomNavigation.setMenuItems(menuItems)
        AnimationCard.expandAnimation(card,supportFragmentManager,R.id.frameLayout_main_activity,
            FMessenger(),"fmessenger",actionBar,350)
        intent.getStringExtra("DESTINATION")?.let {
            when(it){
                "ROBINO" ->{  AnimationCard.collapseAnimation(card,supportFragmentManager.findFragmentById(R.id.frameLayout_main_activity),
                    FRobino(),"FRobino",supportFragmentManager,actionBar,300,true,R.id.frameLayout_main_activity)
                    bottomNavigation.setMenuItems(menuItems,1)
                }
               "ORDERS"  ->{
                   AnimationCard.collapseAnimation(card,supportFragmentManager.findFragmentById(R.id.frameLayout_main_activity),
                       FOrders(),"FOrder",supportFragmentManager,actionBar,300,true,R.id.frameLayout_main_activity)
                   bottomNavigation.setMenuItems(menuItems,2)
               }
                else ->    AnimationCard.expandAnimation(card,supportFragmentManager,R.id.frameLayout_main_activity,
                    FMessenger(),"fmessenger",actionBar,350)
            }
        }
        bottomNavigation.setOnMenuItemClickListener{ cbn,index ->
            val thisFragment : Fragment? =supportFragmentManager.findFragmentById(R.id.frameLayout_main_activity)
                when(index){
                    0 -> AnimationCard.collapseAnimation(card,thisFragment,
                        FMessenger(),"FMessenger",supportFragmentManager,actionBar,300,true,R.id.frameLayout_main_activity)

                    1 -> AnimationCard.collapseAnimation(card,supportFragmentManager.findFragmentById(R.id.frameLayout_main_activity),
                        FRobino(),"FRobino",supportFragmentManager,actionBar,300,true,R.id.frameLayout_main_activity)

                    2 -> AnimationCard.collapseAnimation(card,supportFragmentManager.findFragmentById(R.id.frameLayout_main_activity),
                        FOrders(),"FOrder",supportFragmentManager,actionBar,300,true,R.id.frameLayout_main_activity)

                    3 -> AnimationCard.collapseAnimation(card,supportFragmentManager.findFragmentById(R.id.frameLayout_main_activity),
                        FAccount(),"faccount",supportFragmentManager,actionBar,200,true,R.id.frameLayout_main_activity)
                }
        }
        }
    }
