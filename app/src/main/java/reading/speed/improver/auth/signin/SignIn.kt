package reading.speed.improver.auth.signin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.setContentView
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.user.pupil.Pupil
import reading.speed.improver.user.pupil.PupilMainScreen
import reading.speed.improver.user.teacher.PupilsAdapter

class SignIn : AppCompatActivity() {
    var pupils: List<Pupil> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pupils = ChitaloidRepository.getInstance().pupils

        var adapter= PupilsAdapter(ArrayList(pupils))
        var ui = SignInUI(adapter)
        ui.setContentView(this)
    }

    fun completeSignIn(pupil : Pupil){
        ChitaloidRepository.getInstance().currentPupil = pupil
        val intent = Intent(this, PupilMainScreen::class.java)
        startActivity(intent)
        finish()
    }
}
