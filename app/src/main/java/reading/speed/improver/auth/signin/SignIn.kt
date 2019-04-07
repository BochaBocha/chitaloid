package reading.speed.improver.auth.signin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.setContentView
import reading.speed.improver.user.CurrentUser
import reading.speed.improver.user.pupil.PupilMainScreen
import reading.speed.improver.user.teacher.UsersAdapter
import reading.speed.improver.utils.UsersDataHandler

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
