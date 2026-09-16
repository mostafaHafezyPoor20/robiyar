package mostafa.hafezypoor.robiyar.ui.messenger.addOrder

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import mostafa.hafezypoor.robiyar.MainActivity
import mostafa.hafezypoor.robiyar.R
import mostafa.hafezypoor.robiyar.ui.inventory.FInventory
import mostafa.hafezypoor.robiyar.ui.inventory.FShowDialogNotEnoughInventory
import mostafa.hafezypoor.robiyar.utils.AnimationCard

class AddOrderMessenger : AppCompatActivity() , IEvent ,mostafa.hafezypoor.robiyar.ui.inventory.IEvent{
    private lateinit var imageHead : ImageView
    private lateinit var imageHeadBack : ImageView
    private lateinit var textHead  : TextView
    private lateinit var card : FrameLayout
    private lateinit var thisFragment : Fragment
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
                thisFragment = FViewPostChannel(this)
                AnimationCard.expandAnimation(card,supportFragmentManager,R.id.add_order_messenger_frame_layout,
                   thisFragment ,"ViewPostChannel",actionBar,500)
            }else if (it == "MEMBER_CHANNEL"){
                imageHead.setImageResource(R.drawable.crowd_people)
                textHead.setText("عضو کانال")
                thisFragment = FMemberShipChannel(this)
                AnimationCard.expandAnimation(card,supportFragmentManager,R.id.add_order_messenger_frame_layout,
                    thisFragment,"FMemberChannel",actionBar,500)
            }else if (it == "MEMBER_GROUP"){
                imageHead.setImageResource(R.drawable.group_circle_discussing)
                textHead.setText("عضو گروه")
                thisFragment = FMemberGroup(this)
                AnimationCard.expandAnimation(card,supportFragmentManager,R.id.add_order_messenger_frame_layout,
                    thisFragment,"FMemberGroup",actionBar,500)
            }else if (it == "POLL"){
                imageHead.setImageResource(R.drawable.poll_follower_count)
                textHead.setText("نظر سنجی روبیکا")
                thisFragment = FPoll()
                AnimationCard.expandAnimation(card,supportFragmentManager,R.id.add_order_messenger_frame_layout,
                    thisFragment,"FPoll",actionBar,500)
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

    override fun inventoryNotEnough() {
        val fShowDialogNotEnoughInventory = FShowDialogNotEnoughInventory("موجودی شما کافی نیست !","افزایش موجودی",this)
        AnimationCard.collapseAnimation(card
            ,thisFragment,
            fShowDialogNotEnoughInventory,
            "fShowDialogNotEnoughInventory",supportFragmentManager,
            actionBar,200,true,R.id.add_order_messenger_frame_layout)
    }
    override fun onClickDissmissFShowDialogNotEnoughInventory() {
        AnimationCard.collapseAnimation(card
            ,thisFragment,
            FInventory(),
            "fShowDialogNotEnoughInventory",supportFragmentManager,
            actionBar,200,true,R.id.add_order_messenger_frame_layout)
    }
}
