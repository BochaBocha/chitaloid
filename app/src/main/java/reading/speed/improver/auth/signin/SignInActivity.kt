package reading.speed.improver.auth.signin

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import reading.speed.improver.R
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.user.pupil.Pupil
import reading.speed.improver.user.pupil.PupilMainActivity
import reading.speed.improver.user.pupil.PupilsAdapter

class SignInActivity : AppCompatActivity(), SignInConfirmationDialog.SignInConfirmationDialogListener,
    PupilsAdapter.PupilsAdapterListener {
    override fun onPupilClick(pupil: Pupil?) {
        showConfirmationDialog(pupil!!)
    }

    private lateinit var pupils: List<Pupil>
    private lateinit var pupilsAdapter: PupilsAdapter
    override fun onOkClick(dialog: DialogFragment?, pupil: Pupil) {
        dialog?.dismiss()
        completeSignIn(pupil)
    }

    override fun onCancelClick(dialog: DialogFragment?) {
        dialog?.dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.sign_in_layout)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val listView: ListView = findViewById(R.id.pupils_listView)

        //TODO: get pupils through pupils service
        pupils = ChitaloidRepository.getInstance().pupils
        if (pupils.size == 0) {
            showNoPupilsFoundTextView()
            return
        }
        pupilsAdapter = PupilsAdapter(this, R.layout.list_pupil, pupils)
        listView.adapter = pupilsAdapter
        pupilsAdapter.subscribeListener(this)

    }

    private fun showNoPupilsFoundTextView() {
        val ltInflater = layoutInflater
        val linearLayout: LinearLayout = findViewById(R.id.pupils_linearLayout)
        val view = ltInflater.inflate(R.layout.no_pupils_found_view, linearLayout, true)
    }

    private fun showConfirmationDialog(pupil: Pupil) {
        val bundle = Bundle()
        bundle.putSerializable("pupil", pupil)
        val signInConfirmationDialog = SignInConfirmationDialog()
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

