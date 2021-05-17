package com.aditech.opaku.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditech.opaku.R
import kotlinx.android.synthetic.main.activity_success.*

class SuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        btn_finish.setOnClickListener {
            var intent = Intent(this@SuccessActivity, HomeActivity::class.java)
            startActivity(intent)
        }

    }
}