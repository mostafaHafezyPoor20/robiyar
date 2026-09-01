package mostafa.hafezypoor.robiyar.utils

import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object AnimationCard {
    fun expandAnimation(card : FrameLayout,fragmentManager: FragmentManager,fragmentID:Int , fragment : Fragment?=null,tagFragmentName:String,viewTop: View,duration: Long = 600){
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
                    if (fragment != null) fragmentManager.beginTransaction().replace(fragmentID,fragment,tagFragmentName).commit()
                }
                .start()


        }
    }
    fun collapseAnimation(card: FrameLayout,thisFragment: Fragment?,nextFragment: Fragment? = null,nextTagFragmentName:String,fragmentManager: FragmentManager,viewTop:View,duration: Long,expand : Boolean,fragmentID:Int){
          thisFragment?.let {
              if (it != null) fragmentManager.beginTransaction().remove(it).commit()
      }

        card.post {
            val heightVewTop = viewTop.height
            val parent = card.parent as View
            val screenHeight = parent.height
            val availableHeight = screenHeight - heightVewTop
            card.layoutParams.height = availableHeight
            card.requestLayout()
            card.translationY = 0f
            card.animate().translationY(heightVewTop.toFloat())
                .translationY(card.height.toFloat())
                .setDuration(duration)
                .withEndAction {
                    if (expand)  expandAnimation(card,fragmentManager,fragmentID,nextFragment,nextTagFragmentName, viewTop,duration)
                }.start()
        }
    }
}