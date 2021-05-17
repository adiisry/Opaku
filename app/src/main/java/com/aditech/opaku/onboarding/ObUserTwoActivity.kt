package com.aditech.opaku.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.aditech.opaku.R

class ObUserTwoActivity : AppCompatActivity() {

    lateinit var btnNext : TextView
    lateinit var btnBack : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ob_user_two)

        btnNext = findViewById(R.id.btn_lanjut)
        btnBack = findViewById(R.id.btn_kembali)

        btnNext.setOnClickListener{
            var intent = Intent(this@ObUserTwoActivity, ObUserThreeActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnBack.setOnClickListener{
            var intent = Intent(this@ObUserTwoActivity, ObUserOneActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}