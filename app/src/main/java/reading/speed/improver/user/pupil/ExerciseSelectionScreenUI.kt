package reading.speed.improver.user.pupil

import reading.speed.improver.utils.UsersDataHandler
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.button
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.verticalLayout

class ExerciseSelectionScreenUI : AnkoComponent<ExerciseSelectionScreen> {
    override fun createView(ui: AnkoContext<ExerciseSelectionScreen>) = with(ui) {
        verticalLayout {
            for ((exercise_title, value) in UsersDataHandler.exercises) {
                button(exercise_title) { onClick { owner.startExercise(value) } }
            }
        }
    }
}