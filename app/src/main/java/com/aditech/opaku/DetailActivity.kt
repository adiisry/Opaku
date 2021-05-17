package com.aditech.opaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditech.opaku.home.HomeActivity
import com.aditech.opaku.model.Produk
import com.bumptech.glide.Glide
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.NumberFormat
import java.util.*

class DetailActivity : AppCompatActivity() {

    private lateinit var mAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mAnalytics = Firebase.analytics

        val data = intent.getParcelableExtra<Produk>("data")

        val localID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)

        tv_judul_detail.text = data!!.judul
        tv_harga_detail.setText(format.format(data.harga!!.toDouble()))
        tv_desc_detail.text = data.desc

        Glide.with(this)
            .load(data.foto)
            .into(iv_produk_detail)

        btn_buy_now.setOnClickListener {

            mAnalytics.logEvent("checkout") {
                param("product_name", data.judul.toString())
            }

            var intent = Intent(this@DetailActivity, CheckoutActivity::class.java).
            putExtra("data", data)
            startActivity(intent)
        }

        iv_back_detail.setOnClickListener{
            var intent = Intent(this@DetailActivity, HomeActivity::class.java)
            startActivity(intent)
        }

    }
}