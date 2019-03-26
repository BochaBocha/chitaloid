package com.example.trofi.course_work.auth

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.trofi.course_work.pupil.PupilMainScreen


class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SignUpUI().setContentView(this)
    }

    fun trySignUp(name: CharSequence) {
        when (isUserNameValid(name.toString())) {
            ValidationResult.TO_SHORT -> {
                toast("Имя должно быть не менее трех символов!")
                return
            }
            ValidationResult.ALREADY_TAKEN -> {
                longToast("Пользователь с таким именем уже существует!")
                return
            }
        }

        UsersDataHandler.addUser(name)
        CurrentUser.name = name.toString()
        val intent = Intent(this, PupilMainScreen::class.java)
        startActivity(intent)
        finish()
    }


}
