package mostafa.hafezypoor.robiyar.ui.robino.addOrder

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

class AddOrderRobino : AppCompatActivity(), IEvent,mostafa.hafezypoor.robiyar.ui.inventory.IEvent{
    private lateinit var thisFragment : Fragment
    private lateinit var imageHead : ImageView
    private lateinit var imageHeadBack : ImageView
    private lateinit var textHead  : TextView
    private lateinit var card : FrameLayout
    private lateinit var actionBar: LinearLayout
    private lateinit var intentToActivity : Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_order_robino)
        imageHead = findViewById<ImageView>(R.id.imageHead)
        imageHeadBack = findViewById<ImageView>(R.id.imageHeadBack)
        textHead = findViewById<TextView>(R.id.textHead)
        card = findViewById<FrameLayout>(R.id.add_order_messenger_frame_layout)
        actionBar = findViewById<LinearLayout>(R.id.actionBar)
        intentToActivity = Intent(this, MainActivity::class.java)
        intentToActivity.putExtra("DESTINATION","ROBINO")
        intent.getStringExtra("ItemClicked")?.let {
            if (it == "FOLLOWER_ROBINO"){
                thisFragment = FFollowerRobino(this)
            imageHead.setImageResource(R.drawable.follow_button)
            textHead.setText("فالور وربینو")
                AnimationCard.expandAnimation(card,supportFragmentManager,R.id.add_order_messenger_frame_layout,
                    thisFragment,"FFollowerRobino",actionBar,500)
            }else if (it == "LIKE_POST_ROBINO"){
                thisFragment = FLikePostRobino(this)
                imageHead.setImageResource(R.drawable.post_reaction)
                textHead.setText("لایک پست روبینو")
                AnimationCard.expandAnimation(card,supportFragmentManager,R.id.add_order_messenger_frame_layout,
                    thisFragment,"FLikePostRobino",actionBar,500)
            }else if (it == "VIEW_POST_ROBINO"){
                thisFragment = FViewPostRobino(this)
                imageHead.setImageResource(R.drawable.cartoon_post_photo)
                textHead.setText("ویو پست روبینو")
                AnimationCard.expandAnimation(card,supportFragmentManager,R.id.add_order_messenger_frame_layout,
                    thisFragment,"FViewPostRobino",actionBar,500)
            }
        }
        imageHeadBack.setOnClickListener {
            startActivity(intentToActivity)
            finish()
        }

    }

    override fun onBackPressed() {
     startActivity(intentToActivity)
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
