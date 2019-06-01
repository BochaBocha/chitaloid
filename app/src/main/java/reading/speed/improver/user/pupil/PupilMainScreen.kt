package reading.speed.improver.user.pupil

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.setContentView
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.user.pupil.exercise.selection.ExerciseSelectionScreen
import reading.speed.improver.user.pupil.statistic.PupilStatisticsActivity

class PupilMainScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PupilMainScreenUI().setContentView(this)
    }

    fun startExerciseSelection() {
        val intent = Intent(this, ExerciseSelectionScreen::class.java)
        startActivity(intent)
    }

    fun startStatisticsScreen() {
        val intent = Intent(this, PupilStatisticsActivity::class.java)
        startActivity(intent)
    }

    override fun finish() {
        super.finish()
        ChitaloidRepository.getInstance().currentPupil = null
    }
}
