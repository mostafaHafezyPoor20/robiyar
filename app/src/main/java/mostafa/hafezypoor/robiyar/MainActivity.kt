package mostafa.hafezypoor.robiyar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem
import np.com.susanthapa.curved_bottom_navigation.CurvedBottomNavigationView

class MainActivity : AppCompatActivity() {
  private lateinit var bottomNavigation : CurvedBottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
          bottomNavigation = findViewById<CurvedBottomNavigationView>(R.id.bottomNavigation)

        val menuItems = arrayOf(
            CbnMenuItem(R.drawable.drafts_24px,R.drawable.avd_messenger,R.id.messenger,"پیام رسان"),
            CbnMenuItem(R.drawable.alternate_email_24px,R.drawable.avd_robino,R.id.robino,"روبینو")
        )
        bottomNavigation.setMenuItems(menuItems)
        }
    }
