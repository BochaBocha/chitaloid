package reading.speed.improver.auth.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.longToast
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.user.CurrentUser
import reading.speed.improver.user.pupil.PupilMainScreen
import reading.speed.improver.utils.UsersDataHandler
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

        UsersDataHandler.addUser(name)
        val chitaloidRepository : ChitaloidRepository = ChitaloidRepository.getInstance()
       chitaloidRepository.addPupil(name.toString())
        chitaloidRepository.currentPupil.name = name.toString()
        val intent = Intent(this, PupilMainScreen::class.java)
        startActivity(intent)
        finish()
    }


}
