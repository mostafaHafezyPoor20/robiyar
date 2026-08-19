package mostafa.hafezypoor.robiyar.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import mostafa.hafezypoor.robiyar.R
import android.view.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import mostafa.hafezypoor.robiyar.MainActivity
import mostafa.hafezypoor.robiyar.data.model.ModelLogin
import mostafa.hafezypoor.robiyar.data.model.ModelRegister

class MainAuth : AppCompatActivity() , IEventLogin, IEventRegister{
   private lateinit var cardFrameLayout: FrameLayout
   private lateinit var animationView : LottieAnimationView
   private lateinit var titleError : TextView
   private lateinit var linearDialog : LinearLayout
   private lateinit var btnError: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getSharedPreferences("save",MODE_PRIVATE).getString("token",null)!=null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        setContentView(R.layout.main_auth)
         titleError = findViewById<TextView>(R.id.titleError)
         btnError = findViewById<MaterialButton>(R.id.btnError)
         linearDialog = findViewById<LinearLayout>(R.id.linearDialog)
         cardFrameLayout = findViewById<FrameLayout>(R.id.cardFrameLayout)
         animationView = findViewById<LottieAnimationView>(R.id.animation)
       expandAnimationCard(cardFrameLayout, FLogin(this))
        btnError.setOnClickListener {
            animationView.setAnimation(R.raw.login_require_animation)
            linearDialog.visibility = View.GONE
            cardFrameLayout.visibility = View.VISIBLE
            expandAnimationCard(cardFrameLayout, FLogin(this))
        }
    }
    fun expandAnimationCard(card: FrameLayout,fragment: Fragment){
        // this function expand card with animation sexy (:
        card.post {
            val animationBottom=animationView.bottom
            val parent = card.parent as View
            val screenHeight = parent.height
            val availableHeight = screenHeight - animationBottom
            card.layoutParams.height = availableHeight
            card.requestLayout()
            card.translationY = card.height.toFloat()
            card.animate().translationY(animationBottom.toFloat())
                .translationY(0f)
                .setDuration(600)
                .withEndAction {
                    supportFragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out).replace(R.id.cardFrameLayout,fragment,"fragment").commit()
                }
                .start()
        }
    }
    fun collapseAnimationCard(card : FrameLayout, fragmentAfterExpanded: Fragment= FLogin(this),duration : Long = 300,expand : Boolean =true){
        val fg=supportFragmentManager.findFragmentByTag("fragment")
        fg?.let {
            supportFragmentManager.beginTransaction()
                .remove(it)
                .commit()
        }
        card.post {
            val animationBottom=animationView.bottom
            val parent = card.parent as View
            val screenHeight = parent.height
            val availableHeight = screenHeight - animationBottom
            card.layoutParams.height = availableHeight
            card.requestLayout()
            card.translationY = 0f
            card.animate().translationY(animationBottom.toFloat())
                .translationY(card.height.toFloat())
                .setDuration(duration)
                .withEndAction {
                    if (expand){
                        expandAnimationCard(cardFrameLayout,fragmentAfterExpanded)
                    }else{
                        cardFrameLayout.visibility = View.GONE
                        linearDialog.visibility  = View.VISIBLE
                    }
                }
                .start()
        }
    }

    override fun onRegisterClick() {
    // on register clicked in login page
    collapseAnimationCard(cardFrameLayout, FRegister(this))
        animationView.setAnimation(R.raw.register_animation)

    }

    override fun onLoginClick() {
      collapseAnimationCard(cardFrameLayout, FLogin(this))
        animationView.setAnimation(R.raw.login_require_animation)
    }

    override fun onLogin(status: ModelLogin) {
        if (status.status.equals("success")){
           startActivity(Intent(this, MainActivity::class.java))
            getSharedPreferences("save",MODE_PRIVATE).edit().putString("token",status.token).apply()
        }else if (status.status.equals("userNotExist")){
         collapseAnimationCard(cardFrameLayout, duration = 200, expand = false)
         animationView.setAnimation(R.raw.animation_error)
         titleError.setText("نام کاربری یا کلمه عبور اشتباه است!")
        }
    }

    override fun onRegister(status: ModelRegister) {
       if (status.status.equals("success")){
           getSharedPreferences("save",MODE_PRIVATE).edit().putString("token",status.token).apply()
          startActivity(Intent(this, MainActivity::class.java))
       }
    }

}