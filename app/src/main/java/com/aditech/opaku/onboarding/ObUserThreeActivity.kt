package com.aditech.opaku.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.aditech.opaku.R
import com.aditech.opaku.login.LoginScreenActivity

class ObUserThreeActivity : AppCompatActivity() {

    lateinit var btnNext : TextView
    lateinit var btnBack : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ob_user_three)

        btnNext = findViewById(R.id.btn_lanjut3)
        btnBack = findViewById(R.id.btn_kembali2)

        btnNext.setOnClickListener{
            var intent = Intent(this@ObUserThreeActivity, LoginScreenActivity::class.java)
            startActivity(intent)
            finish()
        }


        btnBack.setOnClickListener{
            var intent = Intent(this@ObUserThreeActivity, ObUserTwoActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}