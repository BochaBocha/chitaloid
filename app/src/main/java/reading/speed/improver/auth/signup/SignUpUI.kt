package reading.speed.improver.auth.signup

import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class SignUpUI : AnkoComponent<SignUp> {
    override fun createView(ui: AnkoContext<SignUp>) = with(ui) {
        verticalLayout {
            val name = editText {
                hint = "Фамилия Имя"
            }
            button("Готово"){
                onClick{
                    owner.trySignUp(name.text)
                }
            }
        }
    }
}