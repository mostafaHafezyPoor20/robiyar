package mostafa.hafezypoor.robiyar.ui.messenger.addOrder

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import mostafa.hafezypoor.robiyar.MainActivity
import mostafa.hafezypoor.robiyar.R
import mostafa.hafezypoor.robiyar.utils.AnimationCard

class AddOrderMessenger : AppCompatActivity() {
    private lateinit var imageHead : ImageView
    private lateinit var imageHeadBack : ImageView
    private lateinit var textHead  : TextView
    private lateinit var card : FrameLayout
    private lateinit var actionBar: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_order_messenger)
        imageHead = findViewById<ImageView>(R.id.imageHead)
        imageHeadBack = findViewById<ImageView>(R.id.imageHeadBack)
        textHead = findViewById<TextView>(R.id.textHead)
        card = findViewById<FrameLayout>(R.id.add_order_messenger_frame_layout)
        actionBar = findViewById<LinearLayout>(R.id.actionBar)
        intent.getStringExtra("ItemClicked")?.let {
            if (it == "VIEW_POST_CHANNEL"){
            imageHead.setImageResource(R.drawable.solid_view)
            textHead.setText("ویو پست کانال")
                AnimationCard.expandAnimation(card,supportFragmentManager,R.id.add_order_messenger_frame_layout,
                    FViewPostChannel(),"ViewPostChannel",actionBar,500)
            }else if (it == "MEMBER_CHANNEL"){
                imageHead.setImageResource(R.drawable.crowd_people)
                textHead.setText("عضو کانال")
                AnimationCard.expandAnimation(card,supportFragmentManager,R.id.add_order_messenger_frame_layout,
                    FMemberChannel(),"FMemberChannel",actionBar,500)
            }else if (it == "MEMBER_GROUP"){
                imageHead.setImageResource(R.drawable.group_circle_discussing)
                textHead.setText("عضو گروه")
            }else if (it == "POLL"){
                imageHead.setImageResource(R.drawable.poll_follower_count)
                textHead.setText("نظر سنجی روبیکا")
            }
        }
        imageHeadBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    override fun onBackPressed() {
     startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
