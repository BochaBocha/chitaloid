package reading.speed.improver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity
import reading.speed.improver.auth.signin.SignIn
import reading.speed.improver.auth.signup.SignUp
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.user.teacher.TeacherMainScreen

class Chitaloid : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = ChitaloidRepository.getInstance()
            WelcomeUI().setContentView(this)
        }
        fun startSignIn(){
            startActivity<SignIn>()
        }
        fun startSignUp(){
            startActivity<SignUp>()
        }
        fun signInAsTeacher(){
            startActivity<TeacherMainScreen>()

    }
}
