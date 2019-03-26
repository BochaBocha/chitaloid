package com.example.trofi.course_work.pupil

import android.view.Gravity
import com.example.trofi.course_work.CommonStyle
import com.example.trofi.course_work.CurrentUser
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class PupilMainScreenUI : AnkoComponent<PupilMainScreen> {
    override fun createView(ui: AnkoContext<PupilMainScreen>) = with(ui) {
        verticalLayout{
            textView("Добро пожаловать, "+ CurrentUser.name +" !"){
                textSize = 20f
                padding = dip(10)
            }.lparams{
                gravity = Gravity.CENTER_HORIZONTAL and Gravity.TOP
            }
            button("Упражнения") {
                onClick { owner.startExerciseSelection() }
            }
            button("Статистика") {
                onClick { longToast("Данная функция находится в разработке") }
            }
            button("Выйти из учетной записи") {
                onClick { owner.finish() }
            }
        }.applyRecursively(CommonStyle)
    }
}
