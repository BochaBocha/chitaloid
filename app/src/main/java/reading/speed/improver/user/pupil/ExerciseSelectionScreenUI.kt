package com.example.trofi.course_work.pupil

import com.example.trofi.course_work.utils.UsersDataHandler
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.button
import org.jetbrains.anko.sdk25.coroutines.onClick
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