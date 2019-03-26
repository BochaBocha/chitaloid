package com.example.trofi.course_work.auth

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