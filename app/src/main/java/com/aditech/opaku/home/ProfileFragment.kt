package com.aditech.opaku.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aditech.opaku.R
import com.aditech.opaku.login.LoginScreenActivity
import com.aditech.opaku.utils.Preferences
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    private lateinit var preferences: Preferences
    private lateinit var mAnalytics: FirebaseAnalytics

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mAnalytics = Firebase.analytics

        preferences = Preferences(activity!!.applicationContext)

        tv_nama.setText(preferences.getValues("nama"))
        tv_email.setText(preferences.getValues("email"))
        tv_username.setText(preferences.getValues("username"))
        tv_address_profile.setText(preferences.getValues("address"))

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profil2)

        btn_keluar.setOnClickListener {

            preferences.clear()
            Toast.makeText(context, "Logout", Toast.LENGTH_LONG).show()

            mAnalytics.logEvent("Logout") {
                param("email", preferences.getValues(("nama")).toString())
            }

            val intent = Intent(context, LoginScreenActivity::class.java)
            startActivity(intent)
        }
    }

}