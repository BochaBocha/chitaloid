package reading.speed.improver.user.pupil.exercise.selection

import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.style.DefaultStyle

class ExerciseSelectionScreenUI : AnkoComponent<ExerciseSelectionScreen> {
    override fun createView(ui: AnkoContext<ExerciseSelectionScreen>) = with(ui) {
        scrollView {
            verticalLayout {
                for ((exercise_title, value) in ChitaloidRepository.getInstance().exercises) {
                    button(exercise_title) {
                        onClick {
                            owner.onSelectExercise(value)
                        }
                    }
                }
            }.applyRecursively(DefaultStyle)
        }
    }
}