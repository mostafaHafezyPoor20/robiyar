package mostafa.hafezypoor.robiyar.utils

import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object AnimationCard {
    fun expandAnimation(card : FrameLayout,fragmentManager: FragmentManager,fragmentID:Int , fragment : Fragment,tagFragmentName:String,viewTop: View,duration: Long = 600){
        // this function expand card with animation sexy (:
        card.post {
            val heightViewTop=viewTop.bottom
            val parent = card.parent as View
            val screenHeight = parent.height
            val availableHeight = screenHeight - heightViewTop
            card.layoutParams.height = availableHeight
            card.requestLayout()
            card.translationY = card.height.toFloat()
            card.animate().translationY(heightViewTop.toFloat())
                .translationY(0f)
                .setDuration(duration)
                .withEndAction {
                    fragmentManager.beginTransaction().replace(fragmentID,fragment,tagFragmentName).commit()
                }
                .start()


        }
    }
}