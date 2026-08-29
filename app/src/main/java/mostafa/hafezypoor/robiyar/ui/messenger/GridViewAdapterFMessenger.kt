package mostafa.hafezypoor.robiyar.ui.messenger

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import mostafa.hafezypoor.robiyar.R

class GridViewAdapterFMessenger(context : Context,list: List<ModelGridViewAdapterFMessage>) : ArrayAdapter<ModelGridViewAdapterFMessage?>(context,0,list as List<ModelGridViewAdapterFMessage?>) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null){
            itemView = LayoutInflater.from(context).inflate(R.layout.grid_view_adapter_fmessenger,parent,false)
        }

        val model : ModelGridViewAdapterFMessage? =getItem(position)
       // val animation : LottieAnimationView = itemView.findViewById<LottieAnimationView>(R.id.animation)
        val icon : ImageView = itemView.findViewById<ImageView>(R.id.icon)
        val title : TextView = itemView.findViewById<TextView>(R.id.title)
       // animation.setAnimation(model?.animationResource ?: 0)
        icon.setImageResource(model?.iconResource ?: 0)
        title.setText(model?.title)
        return itemView
    }
}