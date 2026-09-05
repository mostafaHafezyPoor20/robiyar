package mostafa.hafezypoor.robiyar.ui.messenger.addOrder

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import mostafa.hafezypoor.robiyar.R

class AddOrderMessenger : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_order)
        intent.getStringExtra("ItemClicked")?.let {

            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

    }
}