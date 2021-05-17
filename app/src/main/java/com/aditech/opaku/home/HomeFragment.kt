package com.aditech.opaku.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditech.opaku.BaseFragmentBinding
import com.aditech.opaku.DetailActivity
import com.aditech.opaku.R
import com.aditech.opaku.adapter.BannerAdapter
import com.aditech.opaku.adapter.GridAdapter
import com.aditech.opaku.databinding.FragmentHomeBinding
import com.aditech.opaku.model.Banner
import com.aditech.opaku.model.Produk
import com.aditech.opaku.utils.Preferences
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.NumberFormat
import java.util.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private lateinit var preferences: Preferences
    private lateinit var mDatabase: DatabaseReference
    private lateinit var pDatabase: DatabaseReference
    private lateinit var mAnalytics: FirebaseAnalytics
    private var dataList = ArrayList<Banner>()
    private var produkList = ArrayList<Produk>()

    lateinit var tvNama: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity!!.applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Banner")
        pDatabase = FirebaseDatabase.getInstance().getReference("Produk")
        mAnalytics = FirebaseAnalytics.getInstance(context!!)

        tv_user.setText(preferences.getValues("nama"))

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile_home)

        rv_banner.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_grid.layoutManager = GridLayoutManager(context, 2)

        getData()
        getProduk()
    }

    private fun getData(){
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataList.clear()
                for(getdataSnapshot in dataSnapshot.children){
                    var banner = getdataSnapshot.getValue(Banner::class.java)
                    dataList.add(banner!!)
                }

                rv_banner.adapter = BannerAdapter(dataList){

                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun getProduk(){
        pDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                produkList.clear()
                for(getdataSnapshot in dataSnapshot.children){
                    var produk = getdataSnapshot.getValue(Produk::class.java)
                    produkList.add(produk!!)
                }

                rv_grid.adapter = GridAdapter(produkList){
                    var intent = Intent(context, DetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }

        })
    }

//    private fun currency(harga: Double, textView: TextView){
//        val localID = Locale("in", "ID")
//        val format = NumberFormat.getCurrencyInstance(localID)
//        textView.setText(format.format(harga))
//    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//
//        return inflater.inflate(R.layout.fragment_home, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        preferences = Preferences(activity!!.applicationContext)
//        mDatabase = FirebaseDatabase.getInstance().getReference("Produk")
//
//        tvNama = v.findViewById()
//
//    }

}