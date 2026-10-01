package mostafa.hafezypoor.robiyar.ui.dialog

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import mostafa.hafezypoor.robiyar.R

class LoadingDialog(context: Context,val title: CharSequence) : BottomSheetDialog(context) {
    private lateinit var titleTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(false)
        setContentView(R.layout.loading_dialog)
        titleTextView   = findViewById<TextView>(R.id.title)!!
        titleTextView.text = title
    }
    fun setTitleTextView(text:String){
        titleTextView.text = text
    }
}