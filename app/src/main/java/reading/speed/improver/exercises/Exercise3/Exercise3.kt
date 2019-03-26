package com.example.trofi.course_work.exercises.Exercise3

import com.example.trofi.course_work.exercises.GeneralExercise
import com.example.trofi.course_work.utils.ExercisesDataHandler
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.contentView
import org.jetbrains.anko.ctx
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

class Exercise3 : GeneralExercise() {
    lateinit var timer: Timer
    val posibleSecs: IntRange = IntRange(1, 7)
    val defaultSec = posibleSecs.first
    var currentSec = defaultSec
    override fun startExercise() {
        words = ExercisesDataHandler.getWordsFromFile(application, "words.txt")
        initExerciseArea()
        initTimer()
    }

    fun stopTimer() {
        timer.purge()
        timer.cancel()
    }

    override fun displaySettingsDialog() {
        stopTimer()
        contentView?.let {
            SettingsDialog(AnkoContext.create(ctx, this))
        }
    }

    fun changeSecs(newSec: Int) {
        currentSec = newSec
        initTimer(newSec)
    }

    fun initTimer(secs: Int = defaultSec) {
        timer = Timer(true)
        timer.scheduleAtFixedRate(secs.toLong() * 1000, secs.toLong() * 1000) {
            showWord()
        }
    }
}