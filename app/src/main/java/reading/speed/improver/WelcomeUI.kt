package reading.speed.improver

import android.view.Gravity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import reading.speed.improver.style.DefaultStyle

class WelcomeUI : AnkoComponent<Chitaloid> {
    override fun createView(ui: AnkoContext<Chitaloid>) = with(ui) {
        verticalLayout {
            button("Войти") {
                onClick { owner.startSignIn() }
            }.lparams{
                topMargin = dip(20)
                gravity = Gravity.CENTER_HORIZONTAL
                padding = dip(20)
                width = dip(wrapContent)
            }
            button("Создать новый аккаунт") {
                onClick { owner.startSignUp() }
            }.lparams{
                gravity = Gravity.CENTER_HORIZONTAL
                padding = dip(20)
                width = dip(wrapContent)
            }
            button("Я учитель") {
                onClick { owner.signInAsTeacher() }
            }.lparams{
                gravity = Gravity.CENTER_HORIZONTAL
                padding = dip(20)
                width = dip(wrapContent)}
        }.applyRecursively(DefaultStyle)
    }
}


