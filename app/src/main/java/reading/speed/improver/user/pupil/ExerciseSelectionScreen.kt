package reading.speed.improver.pupil

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class ExerciseSelectionScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ExerciseSelectionScreenUI().setContentView(this)

    }

    fun startExercise(exercise: Class<*>) {
        val intent = Intent(this, exercise)
        startActivity(intent)
    }
}
