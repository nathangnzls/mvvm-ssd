package ph.com.southstardrug.www.southstardrug.module
import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.BounceInterpolator
import kotlinx.android.synthetic.main.splash_layout.*
import ph.com.southstardrug.www.southstardrug.R
import ph.com.southstardrug.www.southstardrug.util.SessionManager

class SplashActivity : AppCompatActivity() {
    val ANIMATION_DURATION: Long = 1000
    lateinit var session: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)
        startAnimation()
    }
    private fun startAnimation() {
        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            splashTitle.scaleX = value
            splashTitle.scaleY = value
        }
        valueAnimator.interpolator = BounceInterpolator()
        valueAnimator.duration = ANIMATION_DURATION
        val intent = Intent(this, LogInActivity::class.java)
        val intent2 = Intent(this, HomeActivity::class.java)
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}
            override fun onAnimationEnd(p0: Animator?) {
                session = SessionManager(applicationContext)
                if (session.isLoggedIn()) {
                    startActivity(intent2)
                    finish()
                }else{
                    startActivity(intent)
                    finish()
                }

            }
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationStart(p0: Animator?) {}
        })
        valueAnimator.start()
    }
}


