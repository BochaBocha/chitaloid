package reading.speed.improver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button

import org.jetbrains.anko.startActivity
import reading.speed.improver.auth.signin.SignInActivity
import reading.speed.improver.auth.signup.SignUpActivity
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.user.teacher.TeacherMainActivity

class Chitaloid : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = ChitaloidRepository.getInstance()
        repository.init(this)
        setContentView(R.layout.welcome_layout)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar!!.hide()
        val signInButton = findViewById<Button>(R.id.sign_in_btn)
        val signUpButton = findViewById<Button>(R.id.sign_up_btn)
        val iamATeacherButton = findViewById<Button>(R.id.i_am_a_teacher_btn)
        signInButton.setOnClickListener { startSignIn() }
        signUpButton.setOnClickListener { startSignUp() }
        iamATeacherButton.setOnClickListener { signInAsTeacher() }
    }

    private fun startSignIn() {
        startActivity<SignInActivity>()
    }

    private fun startSignUp() {
        startActivity<SignUpActivity>()
    }

    private fun signInAsTeacher() {
        startActivity<TeacherMainActivity>()

    }
}
