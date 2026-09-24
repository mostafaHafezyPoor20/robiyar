package mostafa.hafezypoor.robiyar.ui.order

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.LinearProgressIndicator
import mostafa.hafezypoor.robiyar.R
import mostafa.hafezypoor.robiyar.data.model.ModelOrders

class AdapterOrders(val context: Context,val list : List<ModelOrders>) : RecyclerView.Adapter<AdapterOrders.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_orders,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        when(list.get(position).service_name){
            "add_member_ship_channel" -> holder.titleOrder.text = "عضو کانال"
            "add_member_ship_group" -> holder.titleOrder.text = "عضو گروه"
            "add_poll" -> holder.titleOrder.text = "نظر سنجی"
            "add_view_post_channel" -> holder.titleOrder.text = "بازدید پست کانال"
            "add_follower" -> holder.titleOrder.text = "فالوور روبینو"
            "add_like" -> holder.titleOrder.text = "لایک پست روبینو"
            "add_view_post_robino" -> holder.titleOrder.text = "بازدید پست روبینو"
        }
        when(list.get(position).status_order){
            "pending" -> {
                holder.statusOrder.text = "درحال انجام"
            }
            "success" -> {
                holder.statusOrder.text = "انجام شده"
            }
        }
        holder.linkOrder.text = list.get(position).link
        holder.progress.max = list.get(position).count_order.toInt()
        holder.progress.progress = list.get(position).count_order_successed.toInt()

    }

    override fun getItemCount(): Int {
     return list.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val   titleOrder : TextView = itemView.findViewById<TextView>(R.id.titleOrder)
    val   statusOrder : TextView = itemView.findViewById<TextView>(R.id.statusOrder)
    val   linkOrder : TextView = itemView.findViewById<TextView>(R.id.linkOrder)
    val progress : LinearProgressIndicator = itemView.findViewById<LinearProgressIndicator>(R.id.progressOrder)
    }
}