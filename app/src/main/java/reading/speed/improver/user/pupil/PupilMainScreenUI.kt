package reading.speed.improver.user.pupil

import android.view.Gravity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import reading.speed.improver.repository.ChitaloidRepository
import reading.speed.improver.user.CurrentUser


class PupilMainScreenUI : AnkoComponent<PupilMainScreen> {
    override fun createView(ui: AnkoContext<PupilMainScreen>) = with(ui) {
        verticalLayout{
            textView("Добро пожаловать, "+ ChitaloidRepository.getInstance().currentPupil.name +" !"){
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
        }
    }
}
