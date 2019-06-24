package reading.speed.improver.user.pupil.management

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.jetbrains.anko.toast
import reading.speed.improver.R
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.service.ChitaloidService
import reading.speed.improver.user.pupil.Pupil
import reading.speed.improver.user.pupil.PupilsAdapter
import reading.speed.improver.user.teacher.PupilStatisticsActivity


class PupilsManagementActivity : AppCompatActivity(), CreatePupilDialog.CreatePupilDialogListener,
    PupilOptionsDialog.PupilOptionsDialogListener, PupilsAdapter.PupilsAdapterListener {

    override fun onPupilClick(pupil: Pupil?) {
        showPupilOptionsDialog(pupil!!)
    }

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
        pupilsAdapter.add(pupil)
        updatePupilsList()
        dialog?.dismiss()
    }

    override fun onCancelClick(dialog: DialogFragment?) {
        dialog?.dismiss()
    }

    private lateinit var pupilsAdapter: PupilsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pupil_management_layout)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val createPupilBtn: FloatingActionButton = findViewById(R.id.create_pupil_btn)
        createPupilBtn.setOnClickListener { showCreatePupilDialog() }

        val pupils: MutableList<Pupil> = ChitaloidRepository.getInstance().pupils
        pupilsAdapter = PupilsAdapter(this, R.layout.list_pupil, pupils)
        val listView: ListView = findViewById(R.id.pupils_listView)
        listView.adapter = pupilsAdapter
        pupilsAdapter.subscribeListener(this)
        updatePupilsList()
    }

    private fun updatePupilsList() {
        val pupils = ChitaloidRepository.getInstance().pupils
        if (pupils.size == 0) {
            showNoPupilsFoundTextView()
        } else {
            hideNoPupilsFoundTextView()
        }
    }

    private fun showNoPupilsFoundTextView() {
        val ltInflater = layoutInflater
        val linearLayout: LinearLayout = findViewById(R.id.pupils_linearLayout)
        val view = ltInflater.inflate(R.layout.no_pupils_found_view, linearLayout, true)
    }

    private fun hideNoPupilsFoundTextView() {
        val linearLayout: LinearLayout = findViewById(R.id.pupils_linearLayout)
        linearLayout.removeView(findViewById(R.id.no_pupils_found_textView))
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
        pupilsAdapter.remove(pupil)
        updatePupilsList()
        toast("Пользователь " + pupil.name + " был удален")
    }
}