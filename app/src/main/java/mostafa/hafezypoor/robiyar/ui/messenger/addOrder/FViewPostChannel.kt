package mostafa.hafezypoor.robiyar.ui.messenger.addOrder

import android.os.Bundle
import android.view.View
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import mostafa.hafezypoor.robiyar.R
import kotlin.random.Random

class FViewPostChannel : Fragment(R.layout.fview_post_channel){
    private lateinit var viewFlipper: ViewFlipper
    private lateinit var btnV1_Understand : MaterialButton
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewFlipper = view.findViewById<ViewFlipper>(R.id.viewFlipper)
        btnV1_Understand = view.findViewById<MaterialButton>(R.id.btnV1_Understand)
        btnV1_Understand.setOnClickListener {
            viewFlipper.showNext()
        }
    }
}