package mostafa.hafezypoor.robiyar.ui.robino

import android.os.Bundle
import android.view.View
import android.widget.GridView
import androidx.constraintlayout.helper.widget.Grid
import androidx.fragment.app.Fragment
import mostafa.hafezypoor.robiyar.R

class FRobino : Fragment(R.layout.frobino) {
    private lateinit var list : GridView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = view.findViewById<GridView>(R.id.list)
        val model = ArrayList<ModelGridViewAdapterFRobino>()
        model.add(ModelGridViewAdapterFRobino("فالوور روبینو",R.raw.follow_animation))
        model.add(ModelGridViewAdapterFRobino("لایک پست روبینو",R.raw.like_animation))
        model.add(ModelGridViewAdapterFRobino("ویو پست روبینو",R.raw.view_animation))
        list.adapter = GridViewAdapterFRobino(context,model)
    }
}