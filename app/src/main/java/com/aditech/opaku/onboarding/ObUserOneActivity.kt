package com.aditech.opaku.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.aditech.opaku.R
import com.aditech.opaku.login.LoginScreenActivity
import com.aditech.opaku.utils.Preferences

class ObUserOneActivity : AppCompatActivity() {

    lateinit var preference: Preferences
    lateinit var btnNext : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ob_user_one)

        preference = Preferences(this)
        if(preference.getValues("onboarding").equals("1")){
            finishAffinity()

            var intent = Intent(this@ObUserOneActivity, LoginScreenActivity::class.java)
            startActivity(intent)
        }

        btnNext = findViewById(R.id.textView6)

        btnNext.setOnClickListener{
            var intent = Intent(this@ObUserOneActivity, ObUserTwoActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}