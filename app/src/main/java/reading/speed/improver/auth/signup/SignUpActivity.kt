package reading.speed.improver.auth.signup

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import reading.speed.improver.R
import reading.speed.improver.service.ChitaloidService
import reading.speed.improver.user.pupil.Pupil
import reading.speed.improver.user.pupil.PupilMainActivity
import reading.speed.improver.utils.InputValidator

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_layout)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val pupilNameInput: EditText = findViewById(R.id.pupil_name_input)
        val signUpButton: Button = findViewById(R.id.sign_up_btn)
        signUpButton.setOnClickListener{tryToSignUp(pupilNameInput.text)}
    }

    private fun tryToSignUp(name: CharSequence) {
        val inputValidator = InputValidator()
        //TODO: replace with switch
        when (inputValidator.isUserNameValid(name.toString())) {
            InputValidator.ValidationResult.TO_SHORT -> {
                toast("Имя должно быть не менее трех символов!")
                return
            }
            InputValidator.ValidationResult.ALREADY_TAKEN -> {
                longToast("Пользователь с таким именем уже существует!")
                return
            }
        }

        val chitaloidService = ChitaloidService()
        var pupil: Pupil = chitaloidService.createPupil(name.toString())
        chitaloidService.addPupil(pupil)
        chitaloidService.currentPupil = chitaloidService.getPupilByName(pupil.name)

        val intent = Intent(this, PupilMainActivity::class.java)
        startActivity(intent)
        finish()
    }


}
