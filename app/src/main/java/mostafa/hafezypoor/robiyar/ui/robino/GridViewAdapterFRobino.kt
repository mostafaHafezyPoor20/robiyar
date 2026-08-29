package mostafa.hafezypoor.robiyar.ui.robino

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import mostafa.hafezypoor.robiyar.R

class GridViewAdapterFRobino(context: Context,list : List<ModelGridViewAdapterFRobino>):
    ArrayAdapter<ModelGridViewAdapterFRobino?>(context,0,list as List<ModelGridViewAdapterFRobino?>){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
       var itemView = convertView
        if (itemView == null){
            itemView = LayoutInflater.from(context).inflate(R.layout.grid_view_adapter_frobino,parent,false)
        }
        val model : ModelGridViewAdapterFRobino? = getItem(position)
        val icon : ImageView = itemView.findViewById<ImageView>(R.id.icon)
        val title : TextView = itemView.findViewById<TextView>(R.id.title)
        title.setText(model?.title)
       icon.setImageResource(model?.iconResource ?: 0)
        return itemView
    }
}