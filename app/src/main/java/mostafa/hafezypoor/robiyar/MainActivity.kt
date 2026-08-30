package mostafa.hafezypoor.robiyar

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import mostafa.hafezypoor.robiyar.ui.messenger.FMessenger
import mostafa.hafezypoor.robiyar.ui.robino.FRobino
import mostafa.hafezypoor.robiyar.utils.AnimationCard
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem
import np.com.susanthapa.curved_bottom_navigation.CurvedBottomNavigationView

class MainActivity : AppCompatActivity() {
  private lateinit var bottomNavigation : CurvedBottomNavigationView
  private lateinit var card: FrameLayout
  private lateinit var actionBar: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
          bottomNavigation = findViewById<CurvedBottomNavigationView>(R.id.bottomNavigation)
        card = findViewById<FrameLayout>(R.id.frameLayout_main_activity)
        actionBar = findViewById<RelativeLayout>(R.id.actionBar)


        val menuItems = arrayOf(
            CbnMenuItem(R.drawable.drafts_24px,R.drawable.avd_messenger,R.id.messenger,"پیام رسان"),
            CbnMenuItem(R.drawable.alternate_email_24px,R.drawable.avd_robino,R.id.robino,"روبینو"),
            CbnMenuItem(R.drawable.shopping_cart_24px,R.drawable.avd_shop,R.id.orders,"سفارش ها"),
            CbnMenuItem(R.drawable.paid_24px,R.drawable.avd_paid,R.id.paid,"موجودی")

        )
        bottomNavigation.setMenuItems(menuItems)
        AnimationCard.expandAnimation(card,supportFragmentManager,R.id.frameLayout_main_activity,
            FMessenger(),"fmessenger",actionBar,350)
        bottomNavigation.setOnMenuItemClickListener{ cbn,index ->
                when(index){
                    0 -> AnimationCard.collapseAnimation(card,supportFragmentManager.findFragmentById(R.id.frameLayout_main_activity),
                        FMessenger(),"FMessenger",supportFragmentManager,actionBar,300,true,R.id.frameLayout_main_activity)

                    1 -> AnimationCard.collapseAnimation(card,supportFragmentManager.findFragmentById(R.id.frameLayout_main_activity),
                        FRobino(),"FRobino",supportFragmentManager,actionBar,300,true,R.id.frameLayout_main_activity)
                }
        }
        }
    }
