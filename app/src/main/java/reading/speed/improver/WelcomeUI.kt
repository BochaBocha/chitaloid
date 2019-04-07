package reading.speed.improver

import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class WelcomeUI : AnkoComponent<Chitaloid> {
    override fun createView(ui: AnkoContext<Chitaloid>) = with(ui) {
        verticalLayout {
            button("Войти") {
                onClick { owner.startSignIn() }
            }
            button("Создать новый аккаунт") {
                onClick { owner.startSignUp() }
            }
            button("Я учитель") {
                onClick { owner.signInAsTeacher() }
            }
        }
    }
}
