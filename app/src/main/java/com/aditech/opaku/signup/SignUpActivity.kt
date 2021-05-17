package com.aditech.opaku.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.aditech.opaku.R
import com.aditech.opaku.login.User
import com.aditech.opaku.utils.Preferences
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.analytics.ktx.ParametersBuilder
import com.google.firebase.analytics.ktx.logEvent

class SignUpActivity : AppCompatActivity() {

    lateinit var sNama: String
    lateinit var sEmail: String
    lateinit var sUsername: String
    lateinit var sPassword: String
    lateinit var sAddress: String

    lateinit var btnNext: Button
    lateinit var btnBackLogin: Button
    lateinit var etNama: TextInputLayout
    lateinit var etEmail: TextInputLayout
    lateinit var etUsername: TextInputLayout
    lateinit var etPassword: TextInputLayout
    lateinit var etAddress: TextInputLayout

    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference
    private lateinit var mAnalytics: FirebaseAnalytics
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")
        mAnalytics = Firebase.analytics

        preferences = Preferences(this)

        etNama = findViewById(R.id.name_up)
        etEmail = findViewById(R.id.email_up)
        etUsername = findViewById(R.id.username_up)
        etPassword = findViewById(R.id.password_up)
        etAddress = findViewById(R.id.address_up)
        btnNext = findViewById(R.id.btn_next)
        btnBackLogin = findViewById(R.id.btn_login_su)

        btnNext.setOnClickListener {

            sNama = etNama.editText?.text.toString()
            sEmail = etEmail.editText?.text.toString()
            sUsername = etUsername.editText?.text.toString()
            sPassword = etPassword.editText?.text.toString()
            sAddress = etAddress.editText?.text.toString()

            if (sNama.equals("")) {
                etNama.error = "Please fill your name"
                etNama.requestFocus()
            } else if (sEmail.equals("")) {
                etEmail.error = "Please fill your e-mail"
                etEmail.requestFocus()
            } else if (sUsername.equals("")) {
                etUsername.error = "Please fill your username"
                etUsername.requestFocus()
            } else if (sPassword.equals("")) {
                etPassword.error = "Please fill your password"
                etPassword.requestFocus()
            } else if (sAddress.equals("")) {
                etAddress.error = "Please fill your address"
                etAddress.requestFocus()
            } else {
                saveUsername(sNama, sEmail, sUsername, sPassword, sAddress)
            }



        }

    }

    private fun saveUsername(sNama: String, sEmail: String, sUsername: String, sPassword: String, sAddress: String) {
        val user = User()
        user.nama = sNama
        user.email = sEmail
        user.username = sUsername
        user.password = sPassword
        user.address = sAddress

        checkingUsername(sUsername, user)
    }

    private fun checkingUsername(iUsername: String, data: User) {
        mDatabaseReference.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@SignUpActivity,
                    "" + databaseError.message,
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    mDatabaseReference.child(iUsername).setValue(data)

                    preferences.setValues("nama", data.nama.toString())
                    preferences.setValues("email", data.email.toString())
                    preferences.setValues("username", data.username.toString())
                    preferences.setValues("password", data.password.toString())
                    preferences.setValues("address", data.address.toString())
                    preferences.setValues("url", "")
                    preferences.setValues("status", "1")



                    val intent = Intent(this@SignUpActivity, PhotoProfileActivity::class.java)
                        .putExtra("data", data)
                    startActivity(intent)



                } else {
                    Toast.makeText(this@SignUpActivity, "", Toast.LENGTH_LONG).show()
                }
            }

        })
    }
}