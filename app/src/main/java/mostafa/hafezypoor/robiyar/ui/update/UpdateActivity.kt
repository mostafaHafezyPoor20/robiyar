package mostafa.hafezypoor.robiyar.ui.update

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import mostafa.hafezypoor.robiyar.R

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_activity)
        findViewById<MaterialButton>(R.id.btnUpdate).setOnClickListener {
            val i  = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse("myket://check-update?id=mostafa.hafezypoor.robiyar"))
            startActivity(i)
        }
    }
}