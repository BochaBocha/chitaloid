package com.example.trofi.course_work.exercises.Exercise4

import com.example.trofi.course_work.exercises.GeneralExercise
import com.example.trofi.course_work.utils.ExercisesDataHandler
import java.util.*

class Exercise4 : GeneralExercise() {
    val degrees = listOf(180, 90, -90)

    override fun startExercise() {
        words = ExercisesDataHandler.getWordsFromFile(application, "collocations.txt")
        initExerciseArea()
    }

    fun getRandomDegree(): Int {
        return degrees[(Random().nextInt(degrees.size))]
    }

    override fun showWord() {
        textView.rotation = getRandomDegree().toFloat()
        super.showWord()
    }
}