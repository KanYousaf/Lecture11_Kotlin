package com.example.kanwal_laptop.lecture11_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private var fb : DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //1. Import google-services.json file in this project
        FirebaseApp.initializeApp(this)

        //get database access
        fb= FirebaseDatabase.getInstance().reference

        //create database tables
        studentsTable()
        teachersTable()
        gradesTable()
        coursesTable()
    }

    fun studentsTable(){
        val student_table= fb!!.child("StudentRecord/students")
        //add student's record
        val bart= student_table.child("123")
        bart.child("id").setValue(123)
        bart.child("name").setValue("Bart")
        bart.child("email").setValue("bart@fox.com")
        bart.child("password").setValue("bartman123")

        val milhouse= student_table.child("456")
        milhouse.child("id").setValue(456)
        milhouse.child("name").setValue("Milhouse")
        milhouse.child("email").setValue("milhouse@fox.com")
        milhouse.child("password").setValue("milhouse123")

        val lisa= student_table.child("888")
        lisa.child("id").setValue(888)
        lisa.child("name").setValue("Lisa")
        lisa.child("email").setValue("lisa@fox.com")
        lisa.child("password").setValue("lisa123")

        val raph= student_table.child("404")
        raph.child("id").setValue(404)
        raph.child("name").setValue("Raph")
        raph.child("email").setValue("raph@fox.com")
        raph.child("password").setValue("raph123")
    }

    fun teachersTable(){
        val teacher_table= fb!!.child("StudentRecord/teachers")

        val krabel= teacher_table.child("1234")
        krabel.child("id").setValue(1234)
        krabel.child("name").setValue("Krabappel")

        val hoover= teacher_table.child("5678")
        hoover.child("id").setValue(5678)
        hoover.child("name").setValue("Hoover")

        val stepp= teacher_table.child("9012")
        stepp.child("id").setValue(1234)
        stepp.child("name").setValue("Stepp")
    }

    fun gradesTable(){
        val grades_table = fb!!.child("StudentRecord/grades")

        val row1= grades_table.push()
        row1.child("student_id").setValue(123)
        row1.child("course_id").setValue(10001)
        row1.child("grade").setValue("B-")

        val row2= grades_table.push()
        row2.child("student_id").setValue(123)
        row2.child("course_id").setValue(10002)
        row2.child("grade").setValue("C")

        val row3= grades_table.push()
        row3.child("student_id").setValue(456)
        row3.child("course_id").setValue(10001)
        row3.child("grade").setValue("B+")

        val row4= grades_table.push()
        row4.child("student_id").setValue(888)
        row4.child("course_id").setValue(10002)
        row4.child("grade").setValue("A+")

        val row5= grades_table.push()
        row5.child("student_id").setValue(888)
        row5.child("course_id").setValue(10003)
        row5.child("grade").setValue("A+")

        val row6= grades_table.push()
        row6.child("student_id").setValue(404)
        row6.child("course_id").setValue(10004)
        row6.child("grade").setValue("D")
    }

    fun coursesTable(){
        val course_table= fb!!.child("StudentRecord/courses")

        val course1= course_table.child("10001")
        course1.child("course_id").setValue(10001)
        course1.child("course_name").setValue("ITC")
        course1.child("teacher_id").setValue(1234)

        val course2= course_table.child("10002")
        course2.child("course_id").setValue(10002)
        course2.child("course_name").setValue("MAD")
        course2.child("teacher_id").setValue(5678)

        val course3= course_table.child("10003")
        course3.child("course_id").setValue(10003)
        course3.child("course_name").setValue("MGES")
        course3.child("teacher_id").setValue(9012)

        val course4= course_table.child("10004")
        course4.child("course_id").setValue(10004)
        course4.child("course_name").setValue("OOP")
        course4.child("teacher_id").setValue(1234)

    }
}
