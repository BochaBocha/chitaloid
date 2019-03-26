package com.example.trofi.course_work.pupil

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.trofi.course_work.CurrentUser
import org.jetbrains.anko.setContentView

class PupilMainScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                PupilMainScreenUI().setContentView(this)
    }

    fun startExerciseSelection(){
        val intent = Intent(this, ExerciseSelectionScreen::class.java)
        startActivity(intent)
    }

    override fun finish(){
        super.finish()
        CurrentUser.name = ""
    }
}
