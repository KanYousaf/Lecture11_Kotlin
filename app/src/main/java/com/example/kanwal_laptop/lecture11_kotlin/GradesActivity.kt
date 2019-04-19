package com.example.kanwal_laptop.lecture11_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_grades.*

class GradesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grades)

        val student_id = intent.getIntExtra("id_to_pass", 0)
        val student_name = intent.getStringExtra("name_to_pass")

        userNameToDisplay.text = "++++ ID : $student_id ++++ : Name $student_name ++++"

        val fb = FirebaseDatabase.getInstance().reference
        val grades= fb.child("StudentRecord/grades")

        val student_grades = grades.orderByChild("student_id").equalTo(student_id.toDouble())
        student_grades.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(data: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(data: DataSnapshot) {
                processData(data)
            }
        })
    }

    fun processData(data : DataSnapshot){
        val list = ArrayList<String>()
        for(child in data.children ){
            val grades = child.getValue(Grades::class.java)!!
            list.add(grades.grade + " in " + grades.course_id)
        }
        gradeListView.adapter = ArrayAdapter<String>(this,
                                android.R.layout.simple_list_item_1,
                                list)
    }
}
