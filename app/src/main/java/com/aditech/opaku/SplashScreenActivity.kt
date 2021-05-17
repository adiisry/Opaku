package com.aditech.opaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.aditech.opaku.onboarding.ObUserOneActivity

class SplashScreenActivity : AppCompatActivity() {

    var topAnim: Animation? = null
    var bottomAnim: Animation? = null
    var logoAnim: Animation? = null

    lateinit var iv_atas: ImageView
    lateinit var iv_bawah: ImageView
    lateinit var iv_logo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)
        logoAnim = AnimationUtils.loadAnimation(this, R.anim.logo_animation)

        iv_atas = findViewById(R.id.imageView2)
        iv_bawah = findViewById(R.id.imageView3)
        iv_logo = findViewById(R.id.imageView4)

        iv_atas.setAnimation(topAnim)
        iv_bawah.setAnimation(bottomAnim)
        iv_logo.setAnimation(logoAnim)

        var handler = Handler()
        handler.postDelayed({
            var intent = Intent(this@SplashScreenActivity, ObUserOneActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}