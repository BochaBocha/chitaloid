package com.example.trofi.course_work.exercises.Exercise2

import com.example.trofi.course_work.exercises.GeneralExercise
import com.example.trofi.course_work.utils.ExercisesDataHandler

class Exercise2 : GeneralExercise() {
    override fun startExercise() {
        words = ExercisesDataHandler.getWordsFromFile(application, "collocations.txt")
        initExerciseArea()
    }
}