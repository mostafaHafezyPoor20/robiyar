package mostafa.hafezypoor.robiyar.ui.messenger.addOrder

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import mostafa.hafezypoor.robiyar.R

class AddOrderMessenger : AppCompatActivity() {
    private lateinit var imageHead : ImageView
    private lateinit var textHead  : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_order_messenger)
        imageHead = findViewById<ImageView>(R.id.imageHead)
        textHead = findViewById<TextView>(R.id.textHead)
        intent.getStringExtra("ItemClicked")?.let {
            if (it == "VIEW_POST_CHANNEL"){
            imageHead.setImageResource(R.drawable.solid_view)
            textHead.setText("ویو پست کانال")
            }else if (it == "MEMBER_CHANNEL"){
                imageHead.setImageResource(R.drawable.crowd_people)
                textHead.setText("عضو کانال")
            }else if (it == "MEMBER_GROUP"){
                imageHead.setImageResource(R.drawable.group_circle_discussing)
                textHead.setText("عضو گروه")
            }else if (it == "POLL"){
                imageHead.setImageResource(R.drawable.poll_follower_count)
                textHead.setText("نظر سنجی روبیکا")
            }
        }

    }
}