package com.example.trofi.course_work.teacher

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.setContentView

class TeacherMainScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TeacherUI().setContentView(this)
    }
    fun startUsersManagement() {
        val intent = Intent(this, UsersManagement::class.java)
        startActivity(intent)
    }
}
