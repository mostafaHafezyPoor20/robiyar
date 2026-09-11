package mostafa.hafezypoor.robiyar.ui.robino

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import mostafa.hafezypoor.robiyar.R
import mostafa.hafezypoor.robiyar.ui.robino.addOrder.AddOrderRobino

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
        list.setOnItemClickListener{parent,view,position,id ->
            val intent = Intent(activity, AddOrderRobino::class.java)
          when(position){
              0 -> intent.putExtra("ItemClicked","FOLLOWER_ROBINO")
              1 -> intent.putExtra("ItemClicked","LIKE_POST_ROBINO")
              2 -> intent.putExtra("ItemClicked","VIEW_POST_ROBINO")
          }
            startActivity(intent)
        }
    }
}