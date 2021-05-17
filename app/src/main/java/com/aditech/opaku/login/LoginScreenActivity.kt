package com.aditech.opaku.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aditech.opaku.R
import com.aditech.opaku.signup.SignUpActivity
import com.aditech.opaku.home.HomeActivity
import com.aditech.opaku.utils.Preferences
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase


class LoginScreenActivity : AppCompatActivity() {

    lateinit var iUsername: String
    lateinit var tUsername: TextInputLayout
    lateinit var iPassword: String
    lateinit var tPassword: TextInputLayout
    lateinit var nDatabase: DatabaseReference
    lateinit var preference: Preferences
    private lateinit var mAnalytics: FirebaseAnalytics

    lateinit var btnLogin : Button
    lateinit var btnSignup : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        nDatabase = FirebaseDatabase.getInstance().getReference("User")
        preference = Preferences(this)
        mAnalytics = Firebase.analytics

        preference.setValues("onboarding", "1")
        if (preference.getValues("status").equals("1")) {
            finishAffinity()

            val intent = Intent(this@LoginScreenActivity,
                HomeActivity::class.java)
            startActivity(intent)
        }

        btnLogin = findViewById(R.id.btn_login)
        btnSignup = findViewById(R.id.btn_signup_lgn)

        btnLogin.setOnClickListener {
            tUsername = findViewById(R.id.username)
            tPassword = findViewById(R.id.password)

            iUsername = tUsername.editText?.text.toString()
            iPassword = tPassword.editText?.text.toString()

            if (iUsername.equals("")) {
                tUsername.error = "Please fill your Username"
                tUsername.requestFocus()
            } else if (iPassword.equals("")) {
                tPassword.error = "Please fill your Password"
                tPassword.requestFocus()
            } else {

                var statusUsername = iUsername.indexOf(".")
                if (statusUsername >= 0) {
                    tUsername.error = "Please fill your Username without ."
                    tUsername.requestFocus()

                } else {
                    pushLogin(iUsername, iPassword)
                }
            }
        }

        btnSignup.setOnClickListener {
            var goSignup = Intent(this@LoginScreenActivity, SignUpActivity::class.java)
            startActivity(goSignup)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        nDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(
                        this@LoginScreenActivity, "Wrong Username", Toast.LENGTH_LONG).show()
                } else {
                    if (user.password.equals(iPassword)) {
                        Toast.makeText(this@LoginScreenActivity, "Welcome", Toast.LENGTH_LONG).show()

                        preference.setValues("nama", user.nama.toString())
                        preference.setValues("email", user.email.toString())
                        preference.setValues("username", user.username.toString())
                        preference.setValues("password", user.password.toString())
                        preference.setValues("role", user.address.toString())
                        preference.setValues("url", user.url.toString())
                        preference.setValues("status", "1")

                        finishAffinity()

                        mAnalytics.logEvent("Login") {
                            param("email", user.email.toString())
                        }

                        val intent = Intent(this@LoginScreenActivity, HomeActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(
                            this@LoginScreenActivity, "Wrong Password",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@LoginScreenActivity, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }

        })

//        btnLogin.setOnClickListener{
//            // Write a message to the database
//            // Write a message to the database
//            val database = FirebaseDatabase.getInstance()
//            val myRef = database.getReference("message")
//
//            myRef.setValue("Hello, World!")
//        }

    }
}