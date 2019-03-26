package com.example.trofi.course_work.exercises.Exercise3

import android.content.DialogInterface
import android.widget.ArrayAdapter
import com.example.trofi.course_work.R
import org.jetbrains.anko.*

class SettingsDialog(ui: AnkoContext<Exercise3>) {

    lateinit var dialog: DialogInterface

    init {
        with(ui) {
            dialog = alert {
                val adapterCoefs = ArrayAdapter(
                        owner,
                        R.layout.support_simple_spinner_dropdown_item,
                        owner.textSizeCoefs.toList()
                )
                val adapterSecs = ArrayAdapter(
                        owner,
                        R.layout.support_simple_spinner_dropdown_item,
                        owner.posibleSecs.toList()
                )
                customView {
                    verticalLayout {

                        owner.settingsLayout = verticalLayout {
                            textView("Размер текста")
                            val coefs = spinner {
                                adapter = adapterCoefs
                                padding = dip(20)
                                setSelection(adapterCoefs.getPosition(owner.textSizeCoef))
                            }
                            textView("Время показа, сек")
                            val secs = spinner {
                                adapter = adapterSecs
                                padding = dip(20)
                                setSelection(adapterCoefs.getPosition(owner.currentSec))
                            }

                            okButton {
                                var selectedCoef = coefs.selectedItemPosition
                                owner.changeTextSize(adapterCoefs.getItem(selectedCoef))
                                owner.changeSecs(adapterSecs.getItem(secs.selectedItemPosition))
                            }

                        }.lparams { padding = dip(10) }
                        padding = dip(10)

                    }
                }
            }.show()
        }
    }
}