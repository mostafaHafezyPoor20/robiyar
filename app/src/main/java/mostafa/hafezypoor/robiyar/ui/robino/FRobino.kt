package mostafa.hafezypoor.robiyar.ui.robino

import android.os.Bundle
import android.view.View
import android.widget.GridView
import androidx.constraintlayout.helper.widget.Grid
import androidx.fragment.app.Fragment
import mostafa.hafezypoor.robiyar.R
import kotlin.random.Random

class FRobino : Fragment(R.layout.frobino) {
    private lateinit var list : GridView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = view.findViewById<GridView>(R.id.list)
        val model = ArrayList<ModelGridViewAdapterFRobino>()
        model.add(ModelGridViewAdapterFRobino("فالوور روبینو", R.drawable.follow_button))
        model.add(ModelGridViewAdapterFRobino("لایک پست روبینو",R.drawable.post_reaction))
        model.add(ModelGridViewAdapterFRobino("ویو پست روبینو",R.drawable.cartoon_post_photo))
        list.adapter = GridViewAdapterFRobino(context,model)
    }
}