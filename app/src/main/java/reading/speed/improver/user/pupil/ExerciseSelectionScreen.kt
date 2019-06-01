package reading.speed.improver.user.pupil

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.setContentView
import reading.speed.improver.repository.exercises.Exercises
import reading.speed.improver.service.ChitaloidService

class ExerciseSelectionScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ExerciseSelectionScreenUI().setContentView(this)
    }

    fun startExercise(exercise: Class<*>) {
        val intent = Intent(this, exercise)
        startActivity(intent)
    }

    fun onSelectExercise(exercise: Exercises){
        var chitaloidService = ChitaloidService()
        chitaloidService.setCurrentExercise(exercise)
        startExercise(chitaloidService.exerciseActivity)
    }
}
