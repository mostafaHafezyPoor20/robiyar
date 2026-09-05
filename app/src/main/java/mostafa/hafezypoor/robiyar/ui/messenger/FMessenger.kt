package mostafa.hafezypoor.robiyar.ui.messenger
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.GridView
import mostafa.hafezypoor.robiyar.R
import androidx.fragment.app.Fragment
import mostafa.hafezypoor.robiyar.ui.messenger.addOrder.AddOrderMessenger

class FMessenger : Fragment(R.layout.fmessenger){
    private lateinit var list: GridView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val modelList = ArrayList<ModelGridViewAdapterFMessage>()
        list = view.findViewById<GridView>(R.id.list)
        modelList.add(ModelGridViewAdapterFMessage("ویو پست کانال", R.drawable.solid_view))
        modelList.add(ModelGridViewAdapterFMessage("عضو کانال", R.drawable.crowd_people))
        modelList.add(ModelGridViewAdapterFMessage("عضو گروه",R.drawable.group_circle_discussing))
        modelList.add(ModelGridViewAdapterFMessage("نظرسنجی روبیکا",R.drawable.poll_follower_count))
        val adapter = GridViewAdapterFMessenger(context, modelList)
        list.adapter = adapter
        list.setOnItemClickListener{parent , view , position , id ->
            val intent  = Intent(activity, AddOrderMessenger::class.java)
            if (position == 0 ){
                intent.putExtra("ItemClicked","VIEW_POST_CHANNEL")
            }else if (position == 1){
                intent.putExtra("ItemClicked","MEMBER_CHANNEL")
            }else if (position == 2){
                intent.putExtra("ItemClicked","MEMBER_GROUP")
            }else if (position == 3){
                intent.putExtra("ItemClicked","POLL")
            }

         startActivity(intent)
        }

        }




    }
