package reading.speed.improver.auth.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.longToast
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast
import reading.speed.improver.service.ChitaloidService
import reading.speed.improver.user.pupil.Pupil
import reading.speed.improver.user.pupil.PupilMainScreen
import reading.speed.improver.utils.PupilsDataHandler
import reading.speed.improver.utils.ValidationResult
import reading.speed.improver.utils.isUserNameValid


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

        val chitaloidService : ChitaloidService = ChitaloidService()
        var pupil: Pupil = chitaloidService.createPupil(name.toString())
        chitaloidService.addPupil(pupil)
        chitaloidService.currentPupil = pupil

        val intent = Intent(this, PupilMainScreen::class.java)
        startActivity(intent)
        finish()
    }


}
