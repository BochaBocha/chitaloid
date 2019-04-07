package reading.speed.improver.user.teacher

import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick


class TeacherUI : AnkoComponent<TeacherMainScreen> {
    override fun createView(ui: AnkoContext<TeacherMainScreen>) = with(ui) {
        frameLayout {
            button("Управление аккаунтами"){
                onClick { owner.startUsersManagement() }
            }.lparams(){
                width = dip(matchParent)
                height = dip(50)
            }
        }
    }
}