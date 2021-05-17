package com.aditech.opaku.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.aditech.opaku.R


class HomeActivity : AppCompatActivity() {

    lateinit var menuHome: ImageView
    lateinit var menuCart: ImageView
    lateinit var menuProfile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentHome = HomeFragment()
        val fragmentCart = CartFragment()
        val fragmentProfile = ProfileFragment()

        menuHome = findViewById(R.id.menu_home)
        menuCart = findViewById(R.id.menu_cart)
        menuProfile = findViewById(R.id.menu_profile)
        setFragment(fragmentHome)

        menuHome.setOnClickListener{
            setFragment(fragmentHome)

            changeIcon(menuHome, R.drawable.ic_baseline_home_24_putih)
            changeIcon(menuCart, R.drawable.ic_baseline_shopping_cart_24)
            changeIcon(menuProfile, R.drawable.ic_baseline_person_24)
        }
        menuCart.setOnClickListener{
            setFragment(fragmentCart)

            changeIcon(menuHome, R.drawable.ic_baseline_home_24)
            changeIcon(menuCart, R.drawable.ic_baseline_shopping_cart_24_putih)
            changeIcon(menuProfile, R.drawable.ic_baseline_person_24)
        }

        menuProfile.setOnClickListener{
            setFragment(fragmentProfile)

            changeIcon(menuHome, R.drawable.ic_baseline_home_24)
            changeIcon(menuCart, R.drawable.ic_baseline_shopping_cart_24)
            changeIcon(menuProfile, R.drawable.ic_baseline_person_24_profile)
        }

    }

    private fun setFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int: Int){
        imageView.setImageResource(int)
    }

}