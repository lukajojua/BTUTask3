package com.example.btutask3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.btutask3.data.Student
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Info : AppCompatActivity() {
    private lateinit var firstNameTextView : TextView
    private lateinit var lastNameTextView : TextView
    private lateinit var emailTextView : TextView
    private lateinit var userIDTextView : TextView
    private lateinit var imageView : ImageView
    val database= Firebase.database("https://btutask3-default-rtdb.europe-west1.firebasedatabase.app/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        init()
    }

    private fun init() {
        val id :String
        id = intent.getStringExtra("ID").toString()
        firstNameTextView = findViewById(R.id.firstNameTextView)
        lastNameTextView = findViewById(R.id.lastNameTextView)
        emailTextView = findViewById(R.id.emailTextView)
        userIDTextView = findViewById(R.id.userIDTextView)
        imageView = findViewById(R.id.imageView2)
            database.getReference(id).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val Student = snapshot.getValue(Student::class.java) ?: return
                    firstNameTextView.text = Student.firstName
                    lastNameTextView.text = Student.lastName
                    emailTextView.text = Student.email
                    userIDTextView.text = Student.personalNumber
                    val link = Student.profileImage
                    Glide.with(this@Info).load(link).into(imageView)
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@Info, error.message, Toast.LENGTH_SHORT).show()
                }
            })

    }
    }
