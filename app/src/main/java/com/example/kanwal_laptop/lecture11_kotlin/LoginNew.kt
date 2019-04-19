package com.example.kanwal_laptop.lecture11_kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login_new.*

class LoginNew : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_new)
    }

    fun onSubmitPressed(view: View) {
        // Store values at the time of the login attempt.
        val emailStr = userEmailET.text.toString()
        val passwordStr = userPasswordET.text.toString()

        val fb = FirebaseDatabase.getInstance().reference
        val students = fb.child("StudentRecord/students")
//        val query = students.orderByChild("name")
        val query = students.orderByChild("email").equalTo(emailStr)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(data: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(data: DataSnapshot) {
                processData(data, emailStr, passwordStr)
            }
        })
    }

    fun processData(data: DataSnapshot, user_email: String, user_password: String) {
//        for (student_data in data.children) {
//            if ((student_data.child("email").value!!.equals(user_email)) &&
//                (student_data.child("password").value!!.equals(user_password))
//            ) {
//                val intent = Intent(this, GradesActivity::class.java)
//                val id = student_data.child("id").getValue().toString()
//                val name = student_data.child("name").getValue().toString()
//                intent.putExtra("id_to_pass", id)
//                intent.putExtra("name_to_pass", name)
//                startActivity(intent)
//            }
//        }
        if (!data.hasChildren()) {
            Toast.makeText(this, "No such user $user_email", Toast.LENGTH_SHORT).show()
            return
        }
        for (child in data.children) {
            val student_data = child.getValue(Students::class.java)!!
            if (student_data.email.equals(user_email)) {
                if (student_data.password.equals(user_password)) {
                    val intent = Intent(this, GradesActivity::class.java)
                    val id = student_data.id
                    val name = student_data.name
                    intent.putExtra("id_to_pass", id)
                    intent.putExtra("name_to_pass", name)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
