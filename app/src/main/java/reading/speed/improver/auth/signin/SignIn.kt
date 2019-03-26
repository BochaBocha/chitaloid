package reading.exercises.improver.auth

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.trofi.course_work.pupil.PupilMainScreen
import com.example.trofi.course_work.teacher.UsersAdapter;

class SignIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var users = ArrayList(UsersDataHandler.getAllUsers())
        var adapter= UsersAdapter(users)
        var ui = SignInUI(adapter)
        ui.setContentView(this)

    }

    fun completeSignIn(name : String){
        CurrentUser.name = name.toString()
        val intent = Intent(this, PupilMainScreen::class.java)
        startActivity(intent)
        finish()
    }
}
