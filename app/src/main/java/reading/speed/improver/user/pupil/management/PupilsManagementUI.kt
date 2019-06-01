package reading.speed.improver.user.pupil.management

import android.os.Build
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onItemLongClick
import org.jetbrains.anko.design.floatingActionButton
import reading.speed.improver.user.pupil.Pupil
import reading.speed.improver.utils.ValidationResult
import reading.speed.improver.utils.isUserNameValid

class PupilsManagementUI(val pupilsAdapter: PupilsAdapter) : AnkoComponent<PupilsManagement> {
    lateinit var hintListView: TextView
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun createView(ui: AnkoContext<PupilsManagement>): View = with(ui) {

        return relativeLayout {
            var pupilsList: ListView? = null
            hintListView = textView("Нет зарегистрированных пользователей") {
                textSize = 20f
                visibility = View.GONE
            }.lparams {
                gravity = Gravity.CENTER
            }

            verticalLayout {
                pupilsList = listView {
                    adapter = pupilsAdapter
                    onItemLongClick { adapterView, view, i, l ->
                        val options = listOf("Посмотреть статистику", "Очистить статистику", "Удалить")
                        selector("Опции", options) { DialogInterface, j ->
                            if(j==0){
                                val pupil = adapter.getItem(i)
                                owner.startStatisticsScreen(pupil as Pupil)
                            }
                            if (j == 1)  {
                                val pupil = adapter.getItem(i)
                                owner.deleteStatistics(pupil as Pupil)
                                longToast("Статистика удалена")
                            }
                            if (j == 2) {
                                val pupil = adapter.getItem(i)
                                pupilsAdapter?.delete(i)
                                showHideHintListView(this@listView)
                                longToast("Пользователь был удален")
                            }
                        }
                        true
                    }
                }
            }.lparams {
                margin = dip(5)
            }
            floatingActionButton {
                imageResource = android.R.drawable.ic_input_add
                onClick {
                    val adapter = pupilsList?.adapter as PupilsAdapter
                    alert {
                        title = "Напишите имя нового пользователя"
                        customView {
                            verticalLayout {
                                toolbar {
                                    lparams(width = matchParent, height = wrapContent)
                                }
                                val task = editText {
                                    hint = "Имя пользователя"
                                    padding = dip(20)
                                }
                                positiveButton("Добавить") {
                                    when (isUserNameValid(task.text.toString())) {
                                        ValidationResult.TO_SHORT -> {
                                            ui.toast("Имя должно быть не менее трех символов!")
                                        }
                                        ValidationResult.ALREADY_TAKEN -> {
                                            ui.longToast("Пользователь с таким именем уже существует!")
                                        }
                                        ValidationResult.SUCCESS -> {
                                            adapter.add(task.text.toString())
                                            showHideHintListView(pupilsList!!)
                                        }
                                    }
                                }
                            }
                        }
                    }.show()
                }
            }.lparams {
                margin = dip(10)
                alignParentBottom()
                alignParentEnd()
                alignParentRight()
                gravity = Gravity.BOTTOM or Gravity.END
            }
            showHideHintListView(pupilsList!!)
        }.apply {
            layoutParams = FrameLayout.LayoutParams(matchParent, matchParent)
                .apply {
                    leftMargin = dip(5)
                    rightMargin = dip(5)
                }
        }

    }

    fun getTotalListItems(list: ListView?) = list?.adapter?.count ?: 0

    fun showHideHintListView(listView: ListView) {
        if (getTotalListItems(listView) > 0) {
            hintListView.visibility = View.GONE
        } else {
            hintListView.visibility = View.VISIBLE
        }
    }

}