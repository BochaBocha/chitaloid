package reading.speed.improver.user.teacher

import android.view.Gravity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import reading.speed.improver.style.DefaultStyle


class TeacherUI : AnkoComponent<TeacherMainScreen> {
    override fun createView(ui: AnkoContext<TeacherMainScreen>) = with(ui) {
        verticalLayout {
            button("Управление аккаунтами"){
                onClick { owner.startUsersManagement() }
            }.lparams{
                gravity = Gravity.CENTER_HORIZONTAL
                padding = dip(20)
                width = dip(wrapContent)
            }
        }.applyRecursively(DefaultStyle)
    }
}