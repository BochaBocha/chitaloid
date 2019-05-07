package reading.speed.improver.auth.signin

import android.view.View
import android.widget.ListView
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onItemClick
import reading.speed.improver.user.pupil.Pupil
import reading.speed.improver.user.teacher.PupilsAdapter

class SignInUI(val pupilsAdapter: PupilsAdapter) : AnkoComponent<SignIn> {
    lateinit var hintListView: TextView

    override fun createView(ui: AnkoContext<SignIn>): View = with(ui) {

        return relativeLayout {
            var pupilsList: ListView? = null

             hintListView = textView("Нет зарегистрированных пользователей") {
                textSize = 20f
                 visibility = View.GONE
            }.lparams {
                centerInParent()
            }

            verticalLayout {
                pupilsList = listView {
                    adapter = pupilsAdapter
                    onItemClick { _, view, i, l ->
                        var pupil = adapter.getItem(i)
                        alert("Выбрать пользователя ${(pupil as Pupil).name}?", "Подтверждение"){
                            yesButton{
                                var pupil : Any = adapter.getItem(i)
                                owner.completeSignIn(pupil as Pupil)}
                            noButton{}
                        }.show()
                    }
                }
            }.lparams {
                margin = dip(5)
            }
            showHideHintListView(pupilsList!!)
        }

    }

    fun showHideHintListView(listView: ListView) {
        if (getTotalListItems(listView) > 0) {
            hintListView.visibility = View.GONE
        } else {
            hintListView.visibility = View.VISIBLE
        }
    }
    fun getTotalListItems(list: ListView?) = list?.adapter?.count ?: 0
}