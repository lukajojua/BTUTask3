package com.example.btutask3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.btutask3.data.Student
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.security.AuthProvider

class MainActivity : AppCompatActivity() {
    val database=Firebase.database("https://btutask3-default-rtdb.europe-west1.firebasedatabase.app/")
    private val auth = FirebaseAuth.getInstance()
    private lateinit var tbImageLink:EditText
    private lateinit var tbName:EditText
    private lateinit var tbLastName:EditText
    private lateinit var tbID:EditText
    private lateinit var tbEmail: EditText
    private lateinit var submitButton:Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        listeners()
    }

    private fun init(){
        tbImageLink=findViewById(R.id.imageLinkEditText)
        tbName=findViewById(R.id.nameEditText)
        tbLastName=findViewById(R.id.lastNameEditText)
        tbID=findViewById(R.id.idEditText)
        tbEmail=findViewById(R.id.emailEditText)
        submitButton=findViewById(R.id.submitButton)

    }

    private fun listeners(){
        submitButton.setOnClickListener{
            val firstname=tbName.text.toString()
            val lastName=tbLastName.text.toString()
            val id=tbID.text.toString()
            val email=tbEmail.text.toString()
            val imageLink=tbImageLink.text.toString()
            if(email.contains("@")&&id.length==13){
                val Student = Student(firstname, lastName, id, imageLink, email)
                val ref = database.getReference(id)
                ref.setValue(Student)
                val intent = Intent(this, Info::class.java)
                intent.putExtra("ID", id)
                startActivity(intent)
            }
            else
                return@setOnClickListener




        }

    }
}