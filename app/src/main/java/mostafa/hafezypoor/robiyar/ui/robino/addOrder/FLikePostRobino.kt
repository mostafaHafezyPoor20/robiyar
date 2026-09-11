package mostafa.hafezypoor.robiyar.ui.robino.addOrder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import mostafa.hafezypoor.robiyar.MainActivity
import mostafa.hafezypoor.robiyar.R
import kotlin.random.Random

class FLikePostRobino : Fragment(R.layout.flike_post_robino){
    private lateinit var viewFlipper: ViewFlipper
    private lateinit var btnV1_Understand : MaterialButton
    private lateinit var btnV2_back : MaterialButton
    private lateinit var btnV1_backToHome : MaterialButton
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewFlipper = view.findViewById<ViewFlipper>(R.id.viewFlipper)
        btnV1_Understand = view.findViewById<MaterialButton>(R.id.btnV1_Understand)
        btnV1_backToHome = view.findViewById<MaterialButton>(R.id.btnV1_backToHome)
        btnV2_back = view.findViewById<MaterialButton>(R.id.btnV2_back)
        btnV1_Understand.setOnClickListener {
            viewFlipper.showNext()
        }
        btnV2_back.setOnClickListener {
            viewFlipper.showPrevious()
        }
        btnV1_backToHome.setOnClickListener {
            val i = Intent(activity, MainActivity::class.java)
            i.putExtra("DESTINATION","ROBINO")
            context.startActivity(i)
            activity.finish()
        }
    }
}