package com.example.trofi.course_work.teacher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.trofi.course_work.utils.UsersDataHandler
import org.jetbrains.anko.setContentView
import java.util.*

class UsersManagement : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var users = ArrayList(UsersDataHandler.getAllUsers())
        var adapter= UsersAdapter(users)
        var ui = UsersManagementUI(adapter)
        ui.setContentView(this)
    }
}