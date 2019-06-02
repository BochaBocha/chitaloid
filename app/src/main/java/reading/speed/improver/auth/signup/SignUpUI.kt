package reading.speed.improver.auth.signup

import android.view.Gravity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import reading.speed.improver.style.DefaultStyle

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
            }.lparams{
                topMargin = dip(20)
                gravity = Gravity.CENTER_HORIZONTAL
                padding = dip(20)
                width = dip(wrapContent)
            }
        }.applyRecursively(DefaultStyle)
    }
}