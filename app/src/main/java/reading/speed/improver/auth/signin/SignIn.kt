package reading.speed.improver.auth.signin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.setContentView
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.service.ChitaloidService
import reading.speed.improver.user.pupil.Pupil
import reading.speed.improver.user.pupil.PupilMainScreen
import reading.speed.improver.user.teacher.UsersAdapter

class SignIn : AppCompatActivity() {
    var pupils: List<Pupil> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //var users = ArrayList(UsersDataHandler.getAllUsers())
        pupils = ChitaloidRepository.getInstance().pupils

        var adapter= UsersAdapter(ArrayList(pupils))
        var ui = SignInUI(adapter)
        ui.setContentView(this)

    }

    fun completeSignIn(name : String){
        var chitaloidService: ChitaloidService = ChitaloidService()
        ChitaloidRepository.getInstance().currentPupil.name = name
        val intent = Intent(this, PupilMainScreen::class.java)
        startActivity(intent)
        finish()
    }
}
