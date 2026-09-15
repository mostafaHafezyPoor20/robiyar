package mostafa.hafezypoor.robiyar.ui.inventory

import android.os.Bundle
import android.view.View
import android.widget.TextView
import mostafa.hafezypoor.robiyar.R
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class FShowDialogNotEnoughInventory(val title: String, val textBtnDissmis: String,val iEvent: IEvent) : Fragment(R.layout.fshow_dialog_not_enough_inventory){
    private lateinit var titleTextView : TextView
    private lateinit var btnDissmiss : MaterialButton
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      titleTextView = view.findViewById<TextView>(R.id.title)
      btnDissmiss = view.findViewById<MaterialButton>(R.id.btnDissmiss)
        titleTextView.text = title
        btnDissmiss.text = textBtnDissmis
        btnDissmiss.setOnClickListener {
            iEvent.onClickDissmiss()
        }
    }
}
interface IEvent{
    fun onClickDissmiss()
}