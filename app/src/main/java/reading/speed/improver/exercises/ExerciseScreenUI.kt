package reading.speed.improver.exercises

import android.content.DialogInterface
import android.view.Gravity
import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.sdk27.coroutines.onClick

class ExerciseScreenUI : AnkoComponent<ExerciseScreen> {

    override fun createView(ui: AnkoContext<ExerciseScreen>): View {
        ui.owner.fullscreenContent = with(ui) {
            verticalLayout {
                lateinit var dialog: DialogInterface
                floatingActionButton {
                    imageResource = android.R.drawable.ic_menu_revert
                    backgroundColor = 0x0099cc.opaque

                    onClick {
                        dialog = alert {
                            customView {
                                verticalLayout {
                                    button("Продолжить") {
                                        onClick {
                                            dialog.dismiss()
                                            owner.hide()
                                        }
                                    }
                                    button("Настройки") {
                                        onClick {
                                            owner.displaySettingsDialog()
                                        }
                                    }
                                    button("Закончить упражнение") {
                                        onClick {
                                            dialog.dismiss()
                                            owner.finish()
                                        }
                                    }
                                }
                                padding = dip(5)
                            }
                        }.show()

                    }
                }.lparams {
                    margin = dip(20)
                    gravity = Gravity.TOP or Gravity.RIGHT
                }
                owner.exerciseArea = verticalLayout {
                }.lparams(matchParent, matchParent)
            }
        }

        return ui.owner.fullscreenContent
    }
}
