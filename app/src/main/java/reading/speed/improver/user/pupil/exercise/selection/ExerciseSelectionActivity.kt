package reading.speed.improver.user.pupil.exercise.selection

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import reading.speed.improver.R
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.repository.exercises.Exercises
import reading.speed.improver.service.ChitaloidService

class ExerciseSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scrollView = ScrollView(this)
        this.setContentView(scrollView)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        scrollView.addView(linearLayout)
        //TODO: get exercises through exercise service
        for ((exercise_title, value) in ChitaloidRepository.getInstance().exercises) {
            val exerciseButton = Button(this)
            exerciseButton.text = exercise_title
            exerciseButton.setOnClickListener{onSelectExercise(value)}
            linearLayout.addView(exerciseButton)
        }
    }

    private fun startExercise(exercise: Class<*>) {
        val intent = Intent(this, exercise)
        startActivity(intent)
    }

    private fun onSelectExercise(exercise: Exercises){
        val chitaloidService = ChitaloidService()
        chitaloidService.setCurrentExercise(exercise)
        startExercise(chitaloidService.exerciseActivity)
    }
}
