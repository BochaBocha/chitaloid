package com.example.trofi.course_work.exercises

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Gravity
import android.widget.TextView
import com.example.trofi.course_work.utils.ExercisesDataHandler
import java.util.*


abstract class GeneralExercise : ExerciseScreen() {
    var words = emptyList<String?>().toMutableList()
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (words.size == 0) {
            startExercise()
        }
    }

    open fun showWord() {
        runOnUiThread {
            if (words.size > 0) {
                textView.text = words.removeAt(Random().nextInt(words.size))
            } else {
                loadWords()
            }
        }
    }

    override fun changeTextSize(coef: Int) {
        super.changeTextSize(coef)
        textView.textSize = (defaultTextSize * textSizeCoef).toFloat()
    }

    open fun initExerciseArea() {
        textView = TextView(this)
        with(textView) {
            gravity = Gravity.CENTER
            textSize = defaultTextSize.toFloat()
        }
        exerciseArea.setOnClickListener {
            showWord()
        }
        textView.movementMethod = ScrollingMovementMethod()
        exerciseArea.addView(textView)
        showWord()
    }

    fun loadWords() {
        words = ExercisesDataHandler.getWordsFromFile(application, "words.txt")
    }

    override fun startExercise() {
        loadWords()
        initExerciseArea()
    }
}

