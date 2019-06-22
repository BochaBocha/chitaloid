package reading.speed.improver.auth.signin

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.Orientation
import org.jetbrains.anko.custom.style
import org.jetbrains.anko.textColor
import reading.speed.improver.R
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.user.pupil.Pupil
import reading.speed.improver.user.pupil.PupilMainActivity

class SignInActivity : AppCompatActivity(), SignInConfirmationDialog.SignInConfirmationDialogListener {
    private lateinit var pupils: List<Pupil>
    override fun onOkClick(dialog: DialogFragment?, pupil: Pupil) {
        dialog?.dismiss()
        completeSignIn(pupil)
    }

    override fun onCancelClick(dialog: DialogFragment?) {
        dialog?.dismiss()
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.sign_in_layout)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val linearLayout : LinearLayout = findViewById(R.id.pupils_linearLayout)
        //TODO: get pupils through pupils service
        pupils = ChitaloidRepository.getInstance().pupils
        if(pupils.isEmpty()){
            val noPupilTextView  = TextView(this)
            noPupilTextView.text = resources.getString(R.string.no_pupils_found)
            linearLayout.addView(noPupilTextView)
            return
        }
        for (pupil in pupils) {
            val exerciseButton = Button(this)
            exerciseButton.background = resources.getDrawable(R.drawable.list_button)
            exerciseButton.textColor = resources.getColor(R.color.colorFunkyPrimaryGray)
            exerciseButton.text = pupil.name
            exerciseButton.setOnClickListener { showConfirmationDialog(pupil) }
            linearLayout.addView(exerciseButton)
        }

    }

    private fun showConfirmationDialog(pupil: Pupil) {
        var bundle = Bundle()
        bundle.putSerializable("pupil", pupil)
        var signInConfirmationDialog = SignInConfirmationDialog()
        signInConfirmationDialog.arguments = bundle
        signInConfirmationDialog.show(supportFragmentManager, null)
    }

    private fun completeSignIn(pupil: Pupil) {
        ChitaloidRepository.getInstance().currentPupil = pupil
        val intent = Intent(this, PupilMainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

