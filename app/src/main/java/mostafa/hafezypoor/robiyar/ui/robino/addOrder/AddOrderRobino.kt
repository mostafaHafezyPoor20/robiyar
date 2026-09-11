package mostafa.hafezypoor.robiyar.ui.robino.addOrder

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
import mostafa.hafezypoor.robiyar.ui.messenger.addOrder.FMemberChannel
import mostafa.hafezypoor.robiyar.ui.messenger.addOrder.FMemberGroup
import mostafa.hafezypoor.robiyar.ui.messenger.addOrder.FPoll
import mostafa.hafezypoor.robiyar.ui.messenger.addOrder.FViewPostChannel
import mostafa.hafezypoor.robiyar.utils.AnimationCard

class AddOrderRobino : AppCompatActivity() {
    private lateinit var imageHead : ImageView
    private lateinit var imageHeadBack : ImageView
    private lateinit var textHead  : TextView
    private lateinit var card : FrameLayout
    private lateinit var actionBar: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_order_robino)
        imageHead = findViewById<ImageView>(R.id.imageHead)
        imageHeadBack = findViewById<ImageView>(R.id.imageHeadBack)
        textHead = findViewById<TextView>(R.id.textHead)
        card = findViewById<FrameLayout>(R.id.add_order_messenger_frame_layout)
        actionBar = findViewById<LinearLayout>(R.id.actionBar)
        intent.getStringExtra("ItemClicked")?.let {
            if (it == "FOLLOWER_ROBINO"){
            imageHead.setImageResource(R.drawable.follow_button)
            textHead.setText("فالور وربینو")
                AnimationCard.expandAnimation(card,supportFragmentManager,R.id.add_order_messenger_frame_layout,
                    FFollowerRobino(),"FFollowerRobino",actionBar,500)
            }else if (it == "LIKE_POST_ROBINO"){
                imageHead.setImageResource(R.drawable.post_reaction)
                textHead.setText("لایک پست روبینو")
                AnimationCard.expandAnimation(card,supportFragmentManager,R.id.add_order_messenger_frame_layout,
                    FLikePostRobino(),"FLikePostRobino",actionBar,500)
            }else if (it == "VIEW_POST_ROBINO"){
                imageHead.setImageResource(R.drawable.cartoon_post_photo)
                textHead.setText("ویو پست روبینو")
                AnimationCard.expandAnimation(card,supportFragmentManager,R.id.add_order_messenger_frame_layout,
                    FMemberGroup(),"FMemberGroup",actionBar,500)
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
