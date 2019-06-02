package reading.speed.improver.user.pupil

import android.app.ActionBar
import android.os.Build
import android.view.Gravity
import android.view.View
import androidx.annotation.RequiresApi
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.style.DefaultStyle


class PupilMainScreenUI : AnkoComponent<PupilMainScreen> {
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun createView(ui: AnkoContext<PupilMainScreen>) = with(ui) {
        verticalLayout {
            textView("Добро пожаловать, " + ChitaloidRepository.getInstance().currentPupil.name + " !") {
                textSize = 20f
                padding = dip(10)
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }.lparams {
                gravity = Gravity.CENTER_HORIZONTAL and Gravity.TOP
            }
            button("Упражнения") {
                onClick { owner.startExerciseSelection() }
            }.lparams{
                topMargin = dip(20)
                gravity = Gravity.CENTER_HORIZONTAL
                padding = dip(20)
                width = dip(wrapContent)
            }
            button("Статистика") {
                onClick { owner.startStatisticsScreen() }
            }.lparams{
                gravity = Gravity.CENTER_HORIZONTAL
                padding = dip(20)
                width = dip(wrapContent)
            }
            button("Выйти из учетной записи") {
                onClick { owner.finish() }
            }.lparams{
                gravity = Gravity.CENTER_HORIZONTAL
                padding = dip(20)
                width = dip(wrapContent)
            }
        }.applyRecursively(DefaultStyle)
    }
}

