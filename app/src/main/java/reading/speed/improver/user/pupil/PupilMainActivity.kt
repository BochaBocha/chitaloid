package reading.speed.improver.user.pupil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import reading.speed.improver.R
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.user.pupil.exercise.selection.ExerciseSelectionScreen
import reading.speed.improver.user.pupil.statistic.PupilStatisticsActivity

class PupilMainScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pupil_main_layout)
        val welcomeTextView = findViewById<TextView>(R.id.pupil_welcome_textView)
        welcomeTextView.text = """Добро пожаловать, ${ChitaloidRepository.getInstance().currentPupil.name} !"""
        val exerciseSelectionButton = findViewById<Button>(R.id.exercise_selection_btn)
        val statisticButton = findViewById<Button>(R.id.statistic_btn)
        val quitButton = findViewById<Button>(R.id.quit_btn)
        exerciseSelectionButton.setOnClickListener { startExerciseSelection() }
        statisticButton.setOnClickListener { startStatisticsScreen() }
        quitButton.setOnClickListener { finish() }
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
