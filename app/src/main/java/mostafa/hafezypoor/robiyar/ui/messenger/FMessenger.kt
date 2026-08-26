package mostafa.hafezypoor.robiyar.ui.messenger
import android.os.Bundle
import android.view.View
import android.widget.GridView
import mostafa.hafezypoor.robiyar.R
import androidx.fragment.app.Fragment

class FMessenger : Fragment(R.layout.fmessenger){
    private lateinit var list: GridView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val modelList = ArrayList<ModelGridViewAdapterFMessage>()
        list = view.findViewById<GridView>(R.id.list)
        modelList.add(ModelGridViewAdapterFMessage("ویو پست کانال", R.raw.view_animation))
        modelList.add(ModelGridViewAdapterFMessage("عضو کانال", R.raw.channel_animation))
        modelList.add(ModelGridViewAdapterFMessage("عضو گروه",R.raw.group_animation))
        modelList.add(ModelGridViewAdapterFMessage("نظرسنجی روبیکا",R.raw.poll_animation))
        val adapter = GridViewAdapterFMessenger(context, modelList)
        list.adapter = adapter


    }
}