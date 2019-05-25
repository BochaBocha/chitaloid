package reading.speed.improver.user.pupil

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.setContentView
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.repository.Exercises

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
        var chitaloidRepository = ChitaloidRepository.getInstance()
        val exerciseModel = chitaloidRepository.createExerciseModel(exercise)
        startExercise(chitaloidRepository.getExerciseActivity(exercise))
    }
}
