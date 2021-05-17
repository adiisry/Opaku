package com.aditech.opaku.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditech.opaku.R
import com.aditech.opaku.model.Produk
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.NumberFormat
import java.util.*

class CheckoutActivity : AppCompatActivity() {


    private lateinit var mAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        mAnalytics = Firebase.analytics

        val data = intent.getParcelableExtra<Produk>("data")

        val localID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)

        tv_produk_co.text = data!!.judul
        tv_harga_co.setText(format.format(data.harga!!.toDouble()))
        tv_total_co.setText(format.format(data.harga!!.toDouble()))

        btn_buy.setOnClickListener {

            mAnalytics.logEvent("buy") {
                param("product_sale", data.judul.toString())
                param("product_price", format.format(data.harga!!.toDouble()))
            }

            var intent = Intent(this@CheckoutActivity, SuccessActivity::class.java)
            startActivity(intent)
        }

        iv_back_co.setOnClickListener{
            var intent = Intent(this@CheckoutActivity, DetailActivity::class.java).
            putExtra("data", data)
            startActivity(intent)
        }

    }
}