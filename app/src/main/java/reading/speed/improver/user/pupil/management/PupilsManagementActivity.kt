package reading.speed.improver.user.pupil.management

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast
import reading.speed.improver.R
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.service.ChitaloidService
import reading.speed.improver.user.pupil.Pupil
import reading.speed.improver.user.teacher.PupilStatisticsActivity

class PupilsManagementActivity : AppCompatActivity(), CreatePupilDialog.CreatePupilDialogListener,
    PupilOptionsDialog.PupilOptionsDialogListener {

    override fun onShowStatisticClick(dialog: DialogFragment?, pupil: Pupil) {
        startStatisticsScreen(pupil)
        dialog?.dismiss()
    }

    override fun onDeleteStatisticClick(dialog: DialogFragment?, pupil: Pupil) {
        deleteStatistics(pupil)
        dialog?.dismiss()
    }

    override fun onDeletePupilClick(dialog: DialogFragment?, pupil: Pupil) {
        deletePupil(pupil)
        dialog?.dismiss()
    }

    override fun onPupilCreatedClick(dialog: DialogFragment?, pupil: Pupil) {
        dialog?.dismiss()
        updatePupilsButtons()
    }

    override fun onCancelClick(dialog: DialogFragment?) {
        dialog?.dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pupil_management_layout)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val createPupilBtn: FloatingActionButton = findViewById(R.id.create_pupil_btn)
        createPupilBtn.setOnClickListener { showCreatePupilDialog() }
        updatePupilsButtons()

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun updatePupilsButtons() {
        val linearLayout : LinearLayout = findViewById(R.id.pupils_linearLayout)
        linearLayout.removeAllViewsInLayout()
        val pupils = ChitaloidRepository.getInstance().pupils
        if(pupils.size == 0){
            val noPupilTextView  = TextView(this)
            noPupilTextView.text = resources.getString(R.string.no_pupils_found)
            linearLayout.addView(noPupilTextView)
            return
        }
        for (pupil in pupils) {
            val pupilButton = Button(this)
            pupilButton.text = pupil.name
            pupilButton.background = resources.getDrawable(R.drawable.list_button)
            pupilButton.textColor = resources.getColor(R.color.colorFunkyPrimaryGray)
            pupilButton.setOnClickListener { showPupilOptionsDialog(pupil) }
            linearLayout.addView(pupilButton)
        }
    }

    private fun showCreatePupilDialog() {
        val createPupilDialog = CreatePupilDialog()
        createPupilDialog.show(supportFragmentManager, null)
    }

    private fun showPupilOptionsDialog(pupil: Pupil) {
        val bundle = Bundle()
        bundle.putSerializable("pupil", pupil)
        val pupilOptionsDialog = PupilOptionsDialog()
        pupilOptionsDialog.arguments = bundle
        pupilOptionsDialog.show(supportFragmentManager, null)
    }

    private fun startStatisticsScreen(pupil: Pupil) {
        val intent = Intent(this, PupilStatisticsActivity::class.java)
        intent.putExtra("PupilId", pupil._id)
        startActivity(intent)
    }

    private fun deleteStatistics(pupil: Pupil?) {
        val chitaloidService = ChitaloidService()
        chitaloidService.deleteStatistics(pupil)
        toast("Статистика удалена")
    }

    private fun deletePupil(pupil: Pupil) {
        ChitaloidRepository.getInstance().deletePupil(pupil)
        updatePupilsButtons()
        toast("Пользователь был удален")
    }
}