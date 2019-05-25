package reading.speed.improver.user.pupil

import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.button
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.verticalLayout
import reading.speed.improver.repository.ChitaloidRepository

class ExerciseSelectionScreenUI : AnkoComponent<ExerciseSelectionScreen> {
    override fun createView(ui: AnkoContext<ExerciseSelectionScreen>) = with(ui) {
        verticalLayout {

            for ((exercise_title, value) in ChitaloidRepository.getInstance().exercises) {
                button(exercise_title) { onClick {
                    owner.onSelectExercise(value)
                } }
            }
        }
    }
}